package Controller;

import Model.Account;
import Model.DataProvider;
import Model.Deposit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DepositController {
    private static DepositController instance;
    ArrayList<Account> list;

    public static DepositController getInstance() {
        if(instance==null)
            instance=new DepositController();
        return instance;
    }

    public static void setInstance(DepositController instance) {
        DepositController.instance = instance;
    }
    private DepositController(){}

    public int GetActiveAccount(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0)
            return -1;
        if (list.get(0).getStatus() == false)
            return -2;
        return list.get(0).getID();
    }
    public void InsertDeposit(String[]listStr, Date date)
    {
        Deposit deposit = new Deposit();
        deposit.setAccountID(Integer.parseInt(listStr[0]));
        deposit.setName(listStr[1]);
        deposit.setMoney(Integer.parseInt(listStr[2]));
        deposit.setDateAdd(date);
        String query = "USP_InsertPhieuGuiTien ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[] {deposit.getAccountID(), deposit.getName(), deposit.getMoney(), deposit.getDateAdd()});
    }
    public int GetMinMoney(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int typeID = list.get(0).getTypeID();
        String query = "select TienGuiThemToiThieu from LoaiTietKiem where MaLoaiTietKiem = " + typeID;
        int minMoney = (int)DataProvider.getInstance().ExecuteScalar(query, null);
        return minMoney;
    }
    public int GetMaxID()
    {
        String query = "select max(MaPhieuGuiTien) from PhieuGuiTien";
        try
        {
            return (int)DataProvider.getInstance().ExecuteScalar(query, null);
        }
        catch(Exception e)
        {
            return -1;
        }

    }
    public Date GetDateAdd(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Date dateCanAdd = list.get(0).getDateCanAdd();
        return dateCanAdd;
    }
}
