package Controller;

import Model.Account;
import Model.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AccountController {
    private  static AccountController instance;

    public static AccountController getInstance() {
        if(instance==null)
            instance=new AccountController();
        return instance;
    }
    public static void setInstance(AccountController instance) {
        AccountController.instance = instance;
    }
    private AccountController(){}

    public ArrayList<Account> GetListAccount() throws SQLException {
        ArrayList<Account> list = new ArrayList<>();
        String query = "select * from SoTietKiem";
        ResultSet result = DataProvider.getInstance().ExecuteQuery(query, null);
        while(result.next())
        {
            Account acc = new Account(result);
            list.add(acc);
        }
        return list;
    }
    public ArrayList<Account> GetAccountByID(int id) throws SQLException {
        ArrayList<Account> list = new ArrayList<>();
        String query =  "select * from SoTietKiem where MaSoTietKiem = " + id;
        ResultSet result = DataProvider.getInstance().ExecuteQuery(query, null);
        while(result.next())
        {
            Account acc = new Account(result);
            list.add(acc);
        }
        return list;
    }
    public void InsertAccount(int[] listInt, String[] listString, Date date)
    {
        Account acc =new Account();
        acc.setTypeID(listInt[0]);
        acc.setMoney(listInt[1]);
        acc.setName(listString[0]);
        acc.setCMND(listString[1]);
        acc.setAddress(listString[2]);
        acc.setDateOpen(date);
        String query ="EXEC USP_InsertSoTietKiem ? , ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{acc.getTypeID(), acc.getMoney(), acc.getName(), acc.getCMND(), acc.getAddress(), acc.getDateOpen()});
    }
    public int GetMaxAccountID()
    {
        String query="select max(MaSoTIetKiem) from SoTietKiem";
        int max=-1;
        try
        {
//            String sql = "select max(MaSoTietKiem) from SoTietKiem";
//            ResultSet rs = DataProvider.getInstance().ExecuteQuery(query, null);
//            if (rs.next()) {
//                max = rs.getInt(1);
//            }
            max=(int)DataProvider.getInstance().ExecuteScalar(query, null);
            return max;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return max;
        }
    }
}
