package Model;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class Account {
    private int ID;
    private int TypeID;
    private String Name;
    private String CMND;
    private String Address;
    private Date DateOpen;
    private long Money;
    private boolean Status;
    private String DateDue;
    private Date DateCanWithDraw;
    private Date DateCanAdd;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getDateOpen() {
        return DateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        DateOpen = dateOpen;
    }

    public long getMoney() {
        return Money;
    }

    public void setMoney(long money) {
        Money = money;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getDateDue() {
        return DateDue;
    }

    public void setDateDue(String dateDue) {
        DateDue = dateDue;
    }

    public Date getDateCanWithDraw() {
        return DateCanWithDraw;
    }

    public void setDateCanWithDraw(Date dateCanWithDraw) {
        DateCanWithDraw = dateCanWithDraw;
    }

    public Date getDateCanAdd() {
        return DateCanAdd;
    }

    public void setDateCanAdd(Date dateCanAdd) {
        DateCanAdd = dateCanAdd;
    }
    public Account()
    {

    }

    public Account(int id, int typeID, String name, String cmnd,  String address, Date dateOpen, Date dateDue, Date dateCanAdd, Date dateCanWithDraw, long money, boolean status)
    {
        ID=id;
        TypeID=typeID;
        Name=name;
        CMND=cmnd;
        Address=address;
        Money=money;
        Status=status;
        DateOpen=dateOpen;
        DateDue=dateDue.toString();
        DateCanAdd=dateCanAdd;
        DateCanWithDraw=dateCanWithDraw;
    }
    public Account(ResultSet resultSet)
    {
        try
        {
                ID = resultSet.getInt("MaSoTietKiem");
                TypeID=resultSet.getInt("MaLoaiTietKiem");
                Name=resultSet.getNString("TenKhachHang");
                CMND=resultSet.getString("SoCMND");
                Address=resultSet.getString("DiaChi");
                Money=resultSet.getLong("SoDu");
                Status=resultSet.getBoolean("DongSo");
                DateOpen=resultSet.getDate("NgayMoSo");
                DateDue=TryCatchDate(resultSet.getObject("NgayDaoHan"));
                DateCanAdd=resultSet.getDate("NgayGoiThem");
                DateCanWithDraw=resultSet.getDate("NgayRutTien");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String TryCatchDate(Object o)
    {
        String result ;
        try
        {
            Date date =(Date)o;
            DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
            return df.format(date);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            result = "Không kì hạn";
        }
        return result;
    }
}
