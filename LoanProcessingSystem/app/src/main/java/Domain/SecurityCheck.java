package Domain;

/**
 * Created by Riaan on 4/15/2016.
 */
public class SecurityCheck {
    private long securityRefNo;
    private Customer customer;

    private SecurityCheck() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getSecurityRefNo() {
        return securityRefNo;
    }

    public SecurityCheck(Builder builder){
        this.securityRefNo = builder.securityRefNo;
        this.customer = builder.customer;
    }
    public static class Builder{
        private long securityRefNo;
        private Customer customer;

        public Builder(){
            securityRefNo = 0;
            customer = null;
        }
        public Builder securityRefNo(long value){
            this.securityRefNo = value;
            return this;
        }
        public Builder customer(Customer value){
            this.customer = value;
            return this;
        }
        public Builder copy(SecurityCheck value){
            this.securityRefNo = value.securityRefNo;
            this.customer = value.customer;
            return this;
        }
        public SecurityCheck build(){
            return new SecurityCheck(this);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            return securityRefNo == builder.securityRefNo;

        }

        @Override
        public int hashCode() {
            return (int) (securityRefNo ^ (securityRefNo >>> 32));
        }
    }
}
