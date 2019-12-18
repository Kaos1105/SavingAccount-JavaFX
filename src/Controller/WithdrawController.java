package Controller;

import Model.Account;
import Model.DataProvider;
import Model.Withdraw;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WithdrawController  {
    private static WithdrawController Instance;
    ArrayList<Account> list;

    private WithdrawController(){}

    public static WithdrawController getInstance() {
        if(Instance==null) Instance=new WithdrawController();
        return Instance;
    }

    public static void setInstance(WithdrawController instance) {
        Instance = instance;
    }

    public int GetActiveAccount(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() == 0)
            return -1;
        if (list.get(0).Status == false)
            return -2;
        return list.get(0).ID;
    }

    public void InsertWithdraw(String[] listStr, java.sql.Date date)
    {
        Withdraw withdraw  = new Withdraw();
        withdraw.AccountID = Integer.parseInt(listStr[0]);
        withdraw.Name = listStr[1];
        withdraw.Money = Integer.parseInt(listStr[2]);
        withdraw.DateWithdraw = date;
        withdraw.IsClosed=listStr[3].equals("1");
        String query = "USP_InsertPhieuRutTien ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[] { withdraw.AccountID, withdraw.Name, withdraw.Money, withdraw.DateWithdraw, withdraw.IsClosed});
    }

    public ArrayList<Integer> GetMin_CurrMoney(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int typeID = list.get(0).TypeID;
        String query = "select SoTienGuiToiThieu from LoaiTietKiem where MaLoaiTietKiem = " + typeID;
        int minMoney = (int)DataProvider.getInstance().ExecuteScalar(query, null);
        int currMoney = (int) list.get(0).Money;
        ArrayList<Integer> listInt = new ArrayList<>();
        listInt.add(minMoney);
        listInt.add(currMoney);
        return listInt;
    }

    public int GetMaxID()
    {
        String query = "select max(MaPhieuRutTien) from PhieuRutTien";
        try
        {
            return (int)DataProvider.getInstance().ExecuteScalar(query, null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public Boolean CloseAccountByID(int id)
    {
        try
        {
            String query = "update SoTietKiem set DongSo=0 where MaSoTietKiem = " + id;
            if(DataProvider.getInstance().ExecuteNonQuery(query, null)==1)
                return true;
            else return false;
        }
        catch(Exception e)
        {
            return false;
        }

    }

    public int GetPeriod(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int typeID = list.get(0).TypeID;
        String query = "select KyHan from LoaiTietKiem where MaLoaiTietKiem = " + typeID;
        int periodDate = (int)DataProvider.getInstance().ExecuteScalar(query, null);
        return periodDate;
    }

    public ArrayList<Double> InterestMoney_CanWithdraw(int id, Date dateWithdraw)  {
        ArrayList<Double> listReturn = new ArrayList<>();
        double interestMoney = 0;
        int canWithdraw = 0;
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int period = WithdrawController.getInstance().GetPeriod(id);
        int money = (int) list.get(0).Money;
        int typeId = list.get(0).TypeID;
        Date dateCanWithdraw = list.get(0).DateCanWithDraw;
        if (dateCanWithdraw.compareTo(dateWithdraw) <=0)
        {
            if(period>0)
            {
                String query = "select LaiSuat from LoaiTietKiem where MaLoaiTietKiem = " + typeId;
                double interestRate= (double)DataProvider.getInstance().ExecuteScalar(query, null);
                Date result= null;
                try {
                    result = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).DateDue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (result.compareTo( dateWithdraw) <=0)
                {
                    interestMoney = money * (interestRate / 100);
                }
                else
                    interestMoney = 0;

            }
            else
            {
                String query = "select LaiSuatThang from LoaiTietKiem where MaLoaiTietKiem = " + typeId;
                double interestRate = (double)DataProvider.getInstance().ExecuteScalar(query, null);
                long diffInMillies = Math.abs(dateWithdraw.getTime() - dateCanWithdraw.getTime());
                long dateSub = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//                LocalDate ldw = LocalDate.ofInstant(dateWithdraw.toInstant(), ZoneId.systemDefault());
//                LocalDate ldcw = LocalDate.ofInstant(dateCanWithdraw.toInstant(), ZoneId.systemDefault());
//                Period periodDiff = Period.between(ldw, ldcw);
//                int dateSub = periodDiff.getDays();
                interestMoney = money * (dateSub / 30) * (interestRate/100);
            }
            canWithdraw = 1;
        }
        else
            canWithdraw = 0;
        listReturn.add(interestMoney);
        listReturn.add(Double.valueOf(canWithdraw));
        return listReturn;
    }

    public Date getDueDate(int id)
    {
        try {
            list = AccountController.getInstance().GetAccountByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Date result= new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).DateDue);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
