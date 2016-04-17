package Domain;

/**
 * Created by Riaan on 4/15/2016.
 */
public class SecurityCheck {
    private long securityRefNo;
    private Customer customer;

    public SecurityCheck(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getSecurityRefNo() {
        return securityRefNo;
    }

    public void setSecurityRefNo(long securityRefNo) {
        this.securityRefNo = securityRefNo;
    }
}
