package Model;

import java.sql.ResultSet;

public class ReportMonth {
    private String DateReport;
    private int SumOpen;
    private int SumClose;
    private int Subtract;

    public String getDateReport() {
        return DateReport;
    }

    public void setDateReport(String dateReport) {
        DateReport = dateReport;
    }

    public int getSumOpen() {
        return SumOpen;
    }

    public void setSumOpen(int sumOpen) {
        SumOpen = sumOpen;
    }

    public int getSumClose() {
        return SumClose;
    }

    public void setSumClose(int sumClose) {
        SumClose = sumClose;
    }

    public int getSubtract() {
        return Subtract;
    }

    public void setSubtract(int subtract) {
        Subtract = subtract;
    }
    public ReportMonth(String date, int sumOpen, int sumClose, int subtract)
    {
        this.DateReport = date;
        this.SumOpen = sumOpen;
        this.SumClose = sumClose;
        this.Subtract = Math.abs(SumOpen - SumClose);
    }
    public ReportMonth(ResultSet resultSet)
    {
        try {
            if(resultSet.getDate("NgayMoSo")==null)
                DateReport=resultSet.getDate("NgayRut").toString();
            else
                DateReport=resultSet.getDate("NgayMoSo").toString();
            SumOpen=resultSet.getInt("SoMo");
            SumClose=resultSet.getInt("SoDong");
            Subtract= Math.abs(SumOpen - SumClose);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
