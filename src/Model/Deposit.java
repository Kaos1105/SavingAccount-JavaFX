package Model;

import java.sql.ResultSet;
import java.util.Date;

public class Deposit {
    private int ID;
    private int AccountID;
    private String Name;
    private long Money;
    private Date DateAdd;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getMoney() {
        return Money;
    }

    public void setMoney(long money) {
        Money = money;
    }

    public Date getDateAdd() {
        return DateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        DateAdd = dateAdd;
    }
    public Deposit()
    {

    }
    public Deposit(int id, int accountID, String name, long money, Date dateAdd)
    {
        ID=id;
        AccountID=accountID;
        Name=name;
        Money=money;
        DateAdd=dateAdd;
    }
    public Deposit(ResultSet resultSet)
    {
        try {

            ID = resultSet.getInt("MaPhieuGuiTien");
            AccountID =resultSet.getInt("MaSoTietKiem");
            Name=resultSet.getNString("TenKhachHang");
            Money=resultSet.getLong("SoTienGui");
            DateAdd=resultSet.getDate("NgayGui");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
