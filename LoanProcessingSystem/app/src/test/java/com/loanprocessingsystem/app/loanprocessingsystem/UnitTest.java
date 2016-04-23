package com.loanprocessingsystem.app.loanprocessingsystem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Domain.Branch;
import Domain.Customer;
import Domain.Loan;
import Domain.Report;
import Domain.SecurityCheck;
import Domain.User;
import services.BranchService;
import services.ReportService;
import services.SecurityService;
import services.UserService;
import services.factories.BranchServiceFactory;
import services.factories.CustomerServicesFactory;
import services.factories.LoanServicesFactory;
import services.CustomerService;
import services.LoanService;
import services.factories.ReportServiceFactory;
import services.factories.SecurityServiceFactory;
import services.factories.UserServiceFactory;

/**
 * Created by Riaan on 4/14/2016.
 */
public class UnitTest {
    LoanService loanService;
    CustomerService customerService;
    SecurityService securityService;
    UserService userService;
    BranchService branchService;
    ReportService reportService;
    SecurityCheck securityCheck;
    Loan loan;
    Loan carLoan;
    Loan houseLoan;
    Loan instantLoan;
    Customer nonMember;
    Customer member;
    User consultant;
    User manager;
    Branch branch;
    Branch capeTown;
    Branch goodwood;
    Branch headOffice;
    Report report;

    public void setCarLoan(Customer customer, String paymentOption, int numberOfPayments, User user){
        loanService = LoanServicesFactory.getService("Car Loan");
        carLoan = new Loan
                .Builder()
                .copy(loanService.process(customer,paymentOption,numberOfPayments))
                .user(user)
                .loanReferenceNo(1)
                .build();
    }
    public void setHouseLoan(Customer customer, String paymentOption, int numberOfPayments, User user){
        loanService = LoanServicesFactory.getService("House Loan");
        houseLoan = new Loan
                .Builder()
                .copy(loanService.process(customer,paymentOption,numberOfPayments))
                .user(user)
                .loanReferenceNo(2)
                .build();
    }
    public void setInstantLoan(Customer customer, String paymentOption, int numberOfPayments, User user){
        loanService = LoanServicesFactory.getService("Instant Loan");
        instantLoan = new Loan
                .Builder()
                .copy(loanService.process(customer,paymentOption,numberOfPayments))
                .user(user)
                .loanReferenceNo(3)
                .build();
    }
    public void setHeadOffice(){
        branchService = BranchServiceFactory.getService("Head Office");
        headOffice = new Branch
                .Builder()
                .copy(branchService.getBranch())
                .build();
    }
    public void setCapeTown(){
        branchService = BranchServiceFactory.getService("Cape Town");
        capeTown = new Branch
                .Builder()
                .copy(branchService.getBranch())
                .build();
    }
    public void setGoodwood(){
        branchService = BranchServiceFactory.getService("Goodwood");
        goodwood = new Branch
                .Builder()
                .copy(branchService.getBranch())
                .build();
    }
    public void setConsultant(){
        userService = UserServiceFactory.getService("Consultant");
        consultant = new User
                .Builder()
                .copy(userService.getUser())
                .fullName("Alan", "John")
                .address(12, "Barow", "Monti Vista", 7460)
                .userNo(1)
                .branch(goodwood)
                .build();
    }
    public void setManager(){
        userService = UserServiceFactory.getService("Manager");
        manager = new User
                .Builder()
                .copy(userService.getUser())
                .branch(capeTown)
                .fullName("Ngozi")
                .userNo(2)
                .address(14, "Main rd", "Section C", "Khalitsa", 7660)
                .build();
    }
    public void setMember(){
        customerService = CustomerServicesFactory.getService("Member");
        member = new Customer
                .Builder()
                .copy(customerService.getCustomer())
                .monthlyIncome(4000)
                .customerId(1)
                .address(1,"Paarl str", "Goodwood",7460)
                .fullName("Michale Smith")
                .requestAmount(25000)
                .build();
    }
    public void setNonMember(){
        customerService = CustomerServicesFactory.getService("Non Member");
        nonMember = new Customer
                .Builder()
                .copy(customerService.getCustomer())
                .fullName("Michael", "Alan", "Hendricks")
                .address(91, "Grosvernor", "Avondale", "Atlantis", 7349)
                .customerId(2)
                .monthlyIncome(20000)
                .requestAmount(250000)
                .build();
    }


    @Before
    public void setUp() throws Exception {
        setCapeTown();
        setHeadOffice();
        setGoodwood();
        setMember();
        setNonMember();
        setManager();
        setConsultant();

    }

    @Test
    public void testCarLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Car Loan");
        securityCheck = securityService.process(member);
        member = securityCheck.getCustomer();
        setCarLoan(member, "Years", 5, manager);
        Assert.assertEquals("Car Loan",carLoan.getType());
        Assert.assertEquals("Manager", carLoan.getUser().getType());
        if(carLoan.isApproved())
            Assert.assertEquals(29349.22,carLoan.getTotalAmount(),0.000000000000007); //Apporved Loans
        else
            Assert.assertFalse(loan.isApproved());  //Rejected Loans
    }

    @Test
    public void testHouseLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check House Loan");
        securityCheck = securityService.process(nonMember);
        nonMember = securityCheck.getCustomer();
        setHouseLoan(nonMember,"Years",5,consultant);
        Assert.assertEquals("House Loan",houseLoan.getType());
        Assert.assertEquals("Consultant", houseLoan.getUser().getType());
        if(houseLoan.isApproved())
            Assert.assertEquals(333666.72,houseLoan.getTotalAmount(),0.000000000000007);
        else
            Assert.assertFalse(houseLoan.isApproved());
    }

    @Test
    public void testInstantLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Instant Loan");
        securityCheck = securityService.process(nonMember);
        nonMember = securityCheck.getCustomer();
        setInstantLoan(nonMember,"Years",5,consultant);
        Assert.assertEquals("Instant Loan",instantLoan.getType());
        Assert.assertEquals("Consultant",instantLoan.getUser().getType());
        if(instantLoan.isApproved())
            Assert.assertEquals(389108.27,instantLoan.getTotalAmount(),0.000000000000007);
        else
            Assert.assertFalse(instantLoan.isApproved());
    }

    @Test
    public void testNonMember() throws Exception {
        Assert.assertEquals(2, nonMember.getCustomerId());
        Assert.assertEquals("Michael, Alan, Hendricks", nonMember.getFullName().toString());
        Assert.assertEquals("91, Grosvernor, Avondale, Atlantis, 7349", nonMember.getAddress().toString());
        Assert.assertEquals("Non Member", nonMember.getType());
        Assert.assertEquals(20000,nonMember.getMonthlyIncome(),0.00000000007);

    }

    @Test
    public void testMember() throws Exception {
        Assert.assertEquals(1, member.getCustomerId());
        Assert.assertEquals("Michale Smith", member.getFullName().toString());
        Assert.assertEquals("1, Paarl str, Goodwood, 7460", member.getAddress().toString());
        Assert.assertEquals("Member", member.getType());
        Assert.assertEquals(4000,member.getMonthlyIncome(),0.0000007);

    }

    @Test
    public void testConsultant() throws Exception {
        Assert.assertEquals("Goodwood", consultant.getBranch().getName());
        Assert.assertEquals("Alan, John", consultant.getFullName().toString());
        Assert.assertEquals(1, consultant.getUserNo());
        Assert.assertEquals(3, consultant.getBranch().getBranchNo());
        Assert.assertEquals("12, Barow, Monti Vista, 7460", consultant.getAddress().toString());
        Assert.assertEquals("Consultant", consultant.getType());
    }

    @Test
    public void testManager() throws Exception {
        Assert.assertEquals("Cape Town", manager.getBranch().getName());
        Assert.assertEquals("Ngozi", manager.getFullName().toString());
        Assert.assertEquals(2,manager.getBranch().getBranchNo());
        Assert.assertEquals(2, manager.getUserNo());
        Assert.assertEquals("14, Main rd, Section C, Khalitsa, 7660", manager.getAddress().toString());
        Assert.assertEquals("Manager", manager.getType());
    }

    @Test
    public void testHeadOffice() throws Exception {
        Assert.assertEquals("Head Office", headOffice.getName());
        Assert.assertEquals(1,headOffice.getBranchNo());
        Assert.assertEquals("7, Main rd, JHB, 8650",headOffice.getAddress().toString());
    }

    @Test
    public void testCapeTown() throws Exception {
        Assert.assertEquals("Cape Town", capeTown.getName());
        Assert.assertEquals(2,capeTown.getBranchNo());
        Assert.assertEquals("7, Main rd, Cape Town, 7650",capeTown.getAddress().toString());
    }

    @Test
    public void testGoodwood() throws Exception {
        Assert.assertEquals("Goodwood", goodwood.getName());
        Assert.assertEquals(3,goodwood.getBranchNo());
        Assert.assertEquals("7, Alice str, Goodwood, 7640",goodwood.getAddress().toString());
    }

    @Test
    public void testInvoice() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Instant Loan");
        securityCheck = securityService.process(member);
        member = securityCheck.getCustomer();
        setInstantLoan(member,"Years",5,manager);
        reportService = ReportServiceFactory.getService(instantLoan, "Invoice");
        report = reportService.getReport();
        System.out.println(report.display());
        Assert.assertEquals(instantLoan,report.getLoan());
    }
}
