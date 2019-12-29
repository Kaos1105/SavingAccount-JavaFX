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
            type.setName(listStr[0]);
            type.setInterestRate(null);
            type.setInterestRateMonth(Double.parseDouble(listStr[3]));
            type.setMinMoney(Integer.parseInt(listStr[4]));
            type.setMinAddMoney(Integer.parseInt(listStr[5]));
            type.setDateCanAdd(Integer.parseInt(listStr[6]));
            type.setDateWithdraw(Integer.parseInt(listStr[7]));
            type.setActive(listStr[8].equals("1"));
        }
        else
        {
            type.setName(listStr[0]);
            type.setInterestRateMonth(null);
            type.setInterestRate(Double.parseDouble(listStr[2]));
            type.setMinMoney(Integer.parseInt(listStr[4]));
            type.setMinAddMoney(Integer.parseInt(listStr[5]));
            type.setDateCanAdd(Integer.parseInt(listStr[6]));
            type.setDateWithdraw(Integer.parseInt(listStr[7]));
            type.setActive(listStr[8].equals("1"));
        }
        String query = "EXEC USP_InsertLoaiTietKiem ? , ? , ? , ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{type.getName(), type.getInterestRate(), type.getInterestRateMonth(), type.getMinMoney(), type.getMinAddMoney(), type.getDateCanAdd(), type.getDateWithdraw(), type.isActive()});
    }
    public void InsertSavingType(String[] listStr)
    {
        SavingType type = new SavingType();
        if(listStr[1]=="0")
        {
            type.setName(listStr[0]);
            type.setPeriod(Integer.parseInt(listStr[1]));
            type.setInterestRate(null);
            type.setInterestRateMonth(Double.parseDouble(listStr[3]));
            type.setMinMoney(Integer.parseInt(listStr[4]));
            type.setMinAddMoney(Integer.parseInt(listStr[5]));
            type.setDateCanAdd(Integer.parseInt(listStr[6]));
            type.setDateWithdraw(Integer.parseInt(listStr[7]));
            type.setActive(listStr[8].equals("1"));
        }
        else
        {
            type.setName(listStr[0]);
            type.setPeriod(Integer.parseInt(listStr[1]));
            type.setInterestRateMonth(null);
            type.setInterestRate(Double.parseDouble(listStr[2]));
            type.setMinMoney(Integer.parseInt(listStr[4]));
            type.setMinAddMoney(Integer.parseInt(listStr[5]));
            type.setDateCanAdd(Integer.parseInt(listStr[6]));
            type.setDateWithdraw(Integer.parseInt(listStr[7]));
            type.setActive(listStr[8].equals("1"));
        }
        String query = "EXEC USP_AddLoaiTietKiem ? , ? , ? , ? , ? , ? , ? , ? , ?";
        DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{type.getName(), type.getPeriod(), type.getInterestRate(), type.getInterestRateMonth(), type.getMinMoney(), type.getMinAddMoney(), type.getDateCanAdd(), type.getDateWithdraw(), type.isActive()});
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
