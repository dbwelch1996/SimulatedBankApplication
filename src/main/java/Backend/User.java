public class User {

    private String userName;
    private String password;
    private double money;

    //Full Constructor
    public User(double money, String password){
        this.money = money;

        this.password = password;
    }
    //Default Constructor
    public User(){
        this.money = 0;
        this.password = null;
    }
    //Default money constructor
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.money = 0;
    }
    public User(User o){
        this.userName = o.getUsername();
        this.password = o.getPassword();
        this.money = o.getMoney();
    }

    //Getters for field variables
    public String getUsername(){
        return this.userName;
    }
    public double getMoney(){
        return this.money;
    }
    public void addMoney(double amount){
        this.money = this.money + amount;
    }
    public void withdrawMoney(double amount){
        this.money = this.money - amount;
    }
    public String getPassword(){
        return this.password;
    }
    public void changePassword(String password){
        this.password = password;
    }

    public String checkBalance(){
        return "Current Balace: " + getMoney();
    }

}
