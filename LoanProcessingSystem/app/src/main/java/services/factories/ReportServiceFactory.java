package services.factories;

import Domain.Loan;
import services.ReportService;
import services.reportImpl.Invoice;

/**
 * Created by Riaan on 4/15/2016.
 */
public class ReportServiceFactory {
    private static ReportServiceFactory reportServiceFactory= new ReportServiceFactory();
    private ReportServiceFactory(){}
    public static ReportService getService(Loan loan, String service){
        if(service.equalsIgnoreCase("Invoice"))
            return new Invoice(loan);
        return null;
    }

}
