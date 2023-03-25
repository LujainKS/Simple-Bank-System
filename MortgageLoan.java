package banksystem;

public class MortgageLoan extends Loan {
        private String realEstateAddress;
    
        public MortgageLoan() {
        }
    
    public MortgageLoan(int loanNum, double amount, String type) {
        super(loanNum,amount, type);
    }
    public MortgageLoan(String realEstateLocation, int loanNum, double amount, String type) {
        super(loanNum, amount, type);
        this.realEstateAddress = realEstateLocation;
    }

    public String getRealEstateAddress() {
        return realEstateAddress;
    }

    public void setRealEstateAddress(String realEstateLocation) {

            this.realEstateAddress = realEstateLocation;

    }

    @Override
    public String toString() {
        return super.toString()+"\n Real estate address: " + realEstateAddress +"\n";
    }
}   