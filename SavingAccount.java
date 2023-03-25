package banksystem;

public class SavingAccount extends Account {
    
    private  String SerialNo;


    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public SavingAccount(String accountType, String name, double amount, long ID) {
        super(accountType, name, amount, ID);
    }
    
     public SavingAccount(String accountType, String name, double amount, long ID,String SerialNo) {
        super(accountType, name, amount, ID);
        this.SerialNo=SerialNo;
    }

    @Override
    public String toString() {
         int count=0;
         String str=(super.toString() + "\n SerialNo: " + SerialNo +"\n");
         count++;
         
        return str;
        
    }
}