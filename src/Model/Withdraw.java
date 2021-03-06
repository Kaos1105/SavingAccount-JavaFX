package Model;

import java.sql.ResultSet;
import java.util.Date;

public class Withdraw {
    private int ID;
    private int AccountID;
    private String Name;
    private long Money;
    private Date DateWithdraw;
    private Boolean IsClosed;

    public int getID() {
        return ID;
    }

    public void setID(int ID) { this.ID = ID; }

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

    public Date getDateWithdraw() {
        return DateWithdraw;
    }

    public void setDateWithdraw(Date dateWithdraw) {
        DateWithdraw = dateWithdraw;
    }

    public Boolean getIsClosed() { return IsClosed; }

    public void setIsClosed(Boolean closed) { IsClosed = closed; }

    public Withdraw()
    {

    }
    public Withdraw(int id, int accountID, String name, long money, Date dateWithdraw, Boolean isclosed)
    {
        ID=id;
        AccountID=accountID;
        Name=name;
        Money=money;
        DateWithdraw=dateWithdraw;
        IsClosed=isclosed;
    }
    public Withdraw(ResultSet resultSet)
    {
        try {

            ID = resultSet.getInt("MaPhieuRutTien");
            AccountID =resultSet.getInt("MaSoTietKiem");
            Name=resultSet.getNString("TenKhachHang");
            Money=resultSet.getLong("SoTienRut");
            DateWithdraw=resultSet.getDate("NgayRut");
            IsClosed=resultSet.getBoolean("TatToan");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
