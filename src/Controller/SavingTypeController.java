package Controller;

import Model.DataProvider;
import Model.SavingType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class SavingTypeController {
    private static SavingTypeController instance;

    public static SavingTypeController getInstance() {
        if(instance==null) instance = new SavingTypeController();
        return instance;
    }

    public static void setInstance(SavingTypeController instance) {
        SavingTypeController.instance = instance;
    }
    public ArrayList<SavingType> GetListType() throws SQLException {
        ArrayList<SavingType> list = new ArrayList<SavingType>();
        String query = "select * from LoaiTietKiem";
        ResultSet result= DataProvider.getInstance().ExecuteQuery(query, null);
        while (result.next())
        {
            SavingType type = new SavingType(result);
            list.add(type);
        }
        return list;
    }
    public ArrayList<SavingType> GetListActiveType() throws SQLException {
        ArrayList<SavingType> list = new ArrayList<SavingType>();
        String query = "select * from LoaiTietKiem where DangDung = " + 1 ;
        ResultSet result= DataProvider.getInstance().ExecuteQuery(query, null);
        while (result.next())
        {
            SavingType type = new SavingType(result);
            list.add(type);
        }
        return list;
    }
    public void UpdateSavingType(String[] listStr)
    {
        SavingType type = new SavingType();
        if(listStr[1]=="0")
        {
            type.Name=listStr[0];
            type.InterestRate= null;
            type.InterestRateMonth=Double.parseDouble(listStr[3]);
            type.MinMoney=Integer.parseInt(listStr[4]);
            type.MinAddMoney=Integer.parseInt(listStr[5]);
            type.DateCanAdd=Integer.parseInt(listStr[6]);
            type.DateWithdraw=Integer.parseInt(listStr[7]);
            type.IsActive=listStr[8].equals("1");
        }
        else
        {
            type.Name=listStr[0];
            type.InterestRateMonth=null;
            type.InterestRate=Double.parseDouble(listStr[2]);
            type.MinMoney=Integer.parseInt(listStr[4]);
            type.MinAddMoney=Integer.parseInt(listStr[5]);
            type.DateCanAdd=Integer.parseInt(listStr[6]);
            type.DateWithdraw=Integer.parseInt(listStr[7]);
            type.IsActive=listStr[8].equals("1");
        }
        String query = "EXEC USP_InsertLoaiTietKiem ? , ? , ? , ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{type.Name, type.InterestRate, type.InterestRateMonth, type.MinMoney, type.MinAddMoney, type.DateCanAdd, type.DateWithdraw, type.IsActive});
    }
    public void InsertSavingType(String[] listStr)
    {
        SavingType type = new SavingType();
        if(listStr[1]=="0")
        {
            type.Name=listStr[0];
            type.Period=Integer.parseInt(listStr[1]);
            type.InterestRate= null;
            type.InterestRateMonth=Double.parseDouble(listStr[3]);
            type.MinMoney=Integer.parseInt(listStr[4]);
            type.MinAddMoney=Integer.parseInt(listStr[5]);
            type.DateCanAdd=Integer.parseInt(listStr[6]);
            type.DateWithdraw=Integer.parseInt(listStr[7]);
            type.IsActive=listStr[8].equals("1");
        }
        else
        {
            type.Name=listStr[0];
            type.Period=Integer.parseInt(listStr[1]);
            type.InterestRateMonth= null;
            type.InterestRate=Double.parseDouble(listStr[2]);
            type.MinMoney=Integer.parseInt(listStr[4]);
            type.MinAddMoney=Integer.parseInt(listStr[5]);
            type.DateCanAdd=Integer.parseInt(listStr[6]);
            type.DateWithdraw=Integer.parseInt(listStr[7]);
            type.IsActive=listStr[8].equals("1");
        }
        String query = "EXEC USP_AddLoaiTietKiem ? , ? , ? , ? , ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{ type.Name, type.Period, type.InterestRate, type.InterestRateMonth, type.MinMoney, type.MinAddMoney, type.DateCanAdd, type.DateWithdraw, type.IsActive });
    }
    public void DeleteSavingType(String str)
    {
        String query = "Delete from LoaiTietKiem where TenLoaiTietKiem = N'" + str + "'";
        DataProvider.getInstance().ExecuteNonQuery(query, null);
    }
    public int CheckSavingTypeInUse(int id)
    {
        String query = "select MaLoaiTietKiem from LoaiTietKiem L where exists (select 1 from SoTietKiem S where L.MaLoaiTietKiem = S.MaLoaiTietKiem) and L.MaLoaiTietKiem = " + id;
        int result = -1;
        try
        {
            result = (int)DataProvider.getInstance().ExecuteScalar(query, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result=-1;
        }
        return result;
    }
}
