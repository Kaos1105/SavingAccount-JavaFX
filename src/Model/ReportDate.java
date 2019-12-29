package Model;

import java.sql.ResultSet;
import java.sql.Types;

public class ReportDate {
    private String Name;
    private long SumAdd;
    private long SumWithdraw;
    private long Subtract;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getSumAdd() {
        return SumAdd;
    }

    public void setSumAdd(long sumAdd) {
        SumAdd = sumAdd;
    }

    public long getSumWithdraw() {
        return SumWithdraw;
    }

    public void setSumWithdraw(long sumWithdraw) {
        SumWithdraw = sumWithdraw;
    }

    public long getSubtract() {
        return Subtract;
    }

    public void setSubtract(long subtract) {
        Subtract = subtract;
    }
    public ReportDate(String name, long sumAdd, long sumWithdraw)
    {
        Name=name;
        SumAdd=sumAdd;
        SumWithdraw=sumWithdraw;
        Subtract=Math.abs(SumAdd-SumWithdraw);
    }
    public ReportDate(ResultSet resultSet)
    {
        try {
            Name = resultSet.getString("tltk_1");
            if(Name==null)
                Name = resultSet.getString("tltk_2");
            SumAdd = resultSet.getLong("TongThu");
            SumWithdraw=resultSet.getLong("TongChi");
            Subtract=Math.abs(SumAdd-SumWithdraw);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
