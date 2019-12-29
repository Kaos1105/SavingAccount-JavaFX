package Model;

import java.sql.ResultSet;

public class SavingType {
    private int ID;
    private String Name;
    private int Period;
    private boolean IsActive;
    private int DateWithdraw;
    private int MinMoney;
    private int MinAddMoney;
    private Object InterestRate;
    private Object InterestRateMonth;
    private int DateCanAdd;

    public SavingType() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPeriod() {
        return Period;
    }

    public void setPeriod(int period) {
        Period = period;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public int getDateWithdraw() {
        return DateWithdraw;
    }

    public void setDateWithdraw(int dateWithdraw) {
        DateWithdraw = dateWithdraw;
    }

    public int getMinMoney() {
        return MinMoney;
    }

    public void setMinMoney(int minMoney) {
        MinMoney = minMoney;
    }

    public int getMinAddMoney() {
        return MinAddMoney;
    }

    public void setMinAddMoney(int minAddMoney) {
        MinAddMoney = minAddMoney;
    }

    public Object getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(Object interestRate) {
        InterestRate = interestRate;
    }

    public Object getInterestRateMonth() {
        return InterestRateMonth;
    }

    public void setInterestRateMonth(Object interestRateMonth) {
        InterestRateMonth = interestRateMonth;
    }

    public int getDateCanAdd() {
        return DateCanAdd;
    }

    public void setDateCanAdd(int dateCanAdd) {
        DateCanAdd = dateCanAdd;
    }
    public SavingType(int id, String name, int period, boolean isActive, int dateWithdraw, int dateCanAdd, int minMoney, int minAddMoney, int interestRate, int interestRateMonth)
    {
        ID=id;
        Name = name;
        Period = period;
        IsActive = isActive;
        DateWithdraw = dateWithdraw;
        MinMoney = minMoney;
        MinAddMoney = minAddMoney;
        InterestRate = interestRate;
        InterestRateMonth = interestRateMonth;
        DateCanAdd = dateCanAdd;
    }
    public SavingType(ResultSet resultSet)
    {
        try
        {
            Object o =resultSet.getFloat("LaiSuat");
            Object i =resultSet.getFloat("LaiSuatThang");
            ID= resultSet.getInt("MaLoaiTietKiem");
            Name= resultSet.getString("TenLoaiTietKiem");
            Period= resultSet.getInt("KyHan");
            IsActive= resultSet.getBoolean("DangDung");
            MinMoney= resultSet.getInt("SoTienGuiToiThieu");
            MinAddMoney= resultSet.getInt("TienGuiThemToiThieu");
            InterestRate= DoubleTryCatch(resultSet.getFloat("LaiSuat"));
            InterestRateMonth=  DoubleTryCatch(resultSet.getFloat("LaiSuatThang"));
            DateCanAdd= resultSet.getInt("ThoiGianDuocGoiThem");
            DateWithdraw= resultSet.getInt("SoNgayDuocRut");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    Object DoubleTryCatch(float o)
    {
        if(o==0f)
            return null;
        else return o;
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
}
