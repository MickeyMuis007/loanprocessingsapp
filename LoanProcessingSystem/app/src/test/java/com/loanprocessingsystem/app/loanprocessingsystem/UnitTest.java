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
    Customer nonMember;
    Customer member;
    User consultant;
    User manager;
    Branch branch;
    Report report;

    public void setConsultant(){
        userService = UserServiceFactory.getService("Consultant");
        consultant = userService.getUser();
        branchService = BranchServiceFactory.getService("Goodwood");
        branch = branchService.getBranch();
        consultant.setBranch(branch);
        consultant.setFullName("Alan", "John");
        consultant.setUserNo(1);
        consultant.setAddress(12, "Barow", "Monti Vista", "South Africa", 7460);
    }
    public void setManager(){
        userService = UserServiceFactory.getService("Manager");
        manager = userService.getUser();
        branchService = BranchServiceFactory.getService("Cape Town");
        branch = branchService.getBranch();
        manager.setBranch(branch);
        manager.setFullName("Ngozi");
        manager.setUserNo(1);
        manager.setAddress(14, "Main rd", "Section C", "Khalitsa", "South Africa", 7660);
    }
    public void setMember(){
        customerService = CustomerServicesFactory.getService("Member");
        member = customerService.getCustomer();
        member.setMonthlyIncome(4000);
        member.setCustomerId(1);
        member.setAddress(1,"Paarl str", "Goodwood","South Africa",7460);
        member.setFullName("Michale Smith");
        member.setRequestAmount(25000);
    }
    public void setNonMember(){
        customerService = CustomerServicesFactory.getService("Non Member");
        nonMember = customerService.getCustomer();
        nonMember.setFullName("Michael", "Alan", "Hendricks");
        nonMember.setAddress(91, "Grosvernor", "Avondale", "Atlantis", "South Africa", 7349);
        nonMember.setCustomerId(2);
        nonMember.setMonthlyIncome(20000);
        nonMember.setRequestAmount(250000);
    }


    @Before
    public void setUp() throws Exception {
        setMember();
        setNonMember();
        setManager();
        setConsultant();
    }

    @Test
    public void testCarLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Car Loan");
        securityCheck = securityService.process(member);
        loanService = LoanServicesFactory.getService("Car Loan");
        loan = loanService.process(member, "Years", 5);
        loan.setUser(manager);
        Assert.assertEquals("Car Loan",loan.getType());
        Assert.assertEquals("Manager",loan.getUser().getType());
        if(loan.isApproved())
            Assert.assertEquals(29349.22,loan.getTotalAmount(),0.000000000000007); //Apporved Loans
        else
            Assert.assertFalse(loan.isApproved());  //Rejected Loans
    }

    @Test
    public void testHouseLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check House Loan");
        securityCheck = securityService.process(nonMember);
        loanService = LoanServicesFactory.getService("House Loan");
        loan = loanService.process(nonMember, "Years", 5);
        loan.setUser(consultant);
        Assert.assertEquals("House Loan",loan.getType());
        Assert.assertEquals("Consultant",loan.getUser().getType());
        if(loan.isApproved())
            Assert.assertEquals(333666.72,loan.getTotalAmount(),0.000000000000007);
        else
            Assert.assertFalse(loan.isApproved());
    }

    @Test
    public void testInstantLoan() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Instant Loan");
        securityCheck = securityService.process(nonMember);
        loanService = LoanServicesFactory.getService("Instant Loan");
        loan = loanService.process(nonMember, "Years", 5);
        loan.setUser(consultant);
        Assert.assertEquals("Instant Loan",loan.getType());
        Assert.assertEquals("Consultant",loan.getUser().getType());
        if(loan.isApproved())
            Assert.assertEquals(389108.27,loan.getTotalAmount(),0.000000000000007);
        else
            Assert.assertFalse(loan.isApproved());
    }

    @Test
    public void testNonMember() throws Exception {
        Assert.assertEquals(2, nonMember.getCustomerId());
        Assert.assertEquals("Michael, Alan, Hendricks", nonMember.getFullName().toString());
        Assert.assertEquals("91, Grosvernor, Avondale, Atlantis, South Africa, 7349", nonMember.getAddress().toString());
        Assert.assertEquals("Non Member", nonMember.getType());
        Assert.assertEquals(20000,nonMember.getMonthlyIncome(),0.00000000007);

    }

    @Test
    public void testMember() throws Exception {
        Assert.assertEquals(1, member.getCustomerId());
        Assert.assertEquals("Michale Smith", member.getFullName().toString());
        Assert.assertEquals("1, Paarl str, Goodwood, South Africa, 7460", member.getAddress().toString());
        Assert.assertEquals("Member", member.getType());
        Assert.assertEquals(4000,member.getMonthlyIncome(),0.0000007);

    }

    @Test
    public void testConsultant() throws Exception {
        Assert.assertEquals("Goodwood", consultant.getBranch().getName());
        Assert.assertEquals("Alan, John", consultant.getFullName().toString());
        Assert.assertEquals(1, consultant.getUserNo());
        Assert.assertEquals("12, Barow, Monti Vista, South Africa, 7460", consultant.getAddress().toString());
        Assert.assertEquals("Consultant", consultant.getType());
    }

    @Test
    public void testManager() throws Exception {
        Assert.assertEquals("Cape Town", manager.getBranch().getName());
        Assert.assertEquals("Ngozi", manager.getFullName().toString());
        Assert.assertEquals(1, manager.getUserNo());
        Assert.assertEquals("14, Main rd, Section C, Khalitsa, South Africa, 7660", manager.getAddress().toString());
        Assert.assertEquals("Manager", manager.getType());
    }

    @Test
    public void testHeadOffice() throws Exception {
        branchService = BranchServiceFactory.getService("Head Office");
        branch = branchService.getBranch();
        branch.setBranchNo(1);
        Assert.assertEquals("Head Office", branch.getName());
        Assert.assertEquals(1,branch.getBranchNo());
        Assert.assertEquals("7, Main rd, JHB, South Africa, 8650",branch.getAddress().toString());
    }

    @Test
    public void testCapeTown() throws Exception {
        branchService = BranchServiceFactory.getService("Cape Town");
        branch = branchService.getBranch();
        branch.setBranchNo(2);
        Assert.assertEquals("Cape Town", branch.getName());
        Assert.assertEquals(2,branch.getBranchNo());
        Assert.assertEquals("7, Main rd, Cape Town, South Africa, 7650",branch.getAddress().toString());
    }

    @Test
    public void testGoodwood() throws Exception {
        branchService = BranchServiceFactory.getService("Goodwood");
        branch = branchService.getBranch();
        branch.setBranchNo(3);
        Assert.assertEquals("Goodwood", branch.getName());
        Assert.assertEquals(3,branch.getBranchNo());
        Assert.assertEquals("7, Alice str, Goodwood, South Africa, 7640",branch.getAddress().toString());
    }

    @Test
    public void testInvoice() throws Exception {
        securityService = SecurityServiceFactory.getService("Check Instant Loan");
        securityCheck = securityService.process(nonMember);
        loanService = LoanServicesFactory.getService("Instant Loan");
        loan = loanService.process(nonMember, "Years", 5);
        loan.setUser(manager);
        reportService = ReportServiceFactory.getService(loan,"Invoice");
        report = reportService.getReport();
        System.out.println(report.display());
        Assert.assertEquals(loan,report.getLoan());
    }
}
