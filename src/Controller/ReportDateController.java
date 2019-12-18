package Controller;

import Model.DataProvider;
import Model.ReportDate;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportDateController {
    private static ReportDateController instance;

    public static ReportDateController getInstance() {
        if(instance==null) instance=new ReportDateController();
        return instance;
    }

    public static void setInstance(ReportDateController instance) {
        ReportDateController.instance = instance;
    }
    private ReportDateController(){}
    public ArrayList<ReportDate> GetListReportDate(Date date)
    {
        ArrayList<ReportDate> list = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        String query = "select * from (select TenLoaiTietKiem as tltk_1, sum(SoTienGui) as TongThu from PhieuGuiTien pgt, LoaiTietKiem ltk, SoTietKiem stk where pgt.MaSoTietKiem = stk.MaSoTietKiem and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem and pgt.NgayGui = '"+strDate+"' group by TenLoaiTietKiem) t1 full outer join (select TenLoaiTietKiem as tltk_2, sum(SoTienRut) as TongChi from PhieuRutTien prt, LoaiTietKiem ltk, SoTietKiem stk where prt.MaSoTietKiem = stk.MaSoTietKiem and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem and prt.NgayRut = '"+strDate+"' group by TenLoaiTietKiem) t2 on t1.tltk_1 = t2.tltk_2";
        ResultSet result = DataProvider.getInstance().ExecuteQuery(query, null);
        try
        {
            while (result.next())
            {
                ReportDate report = new ReportDate(result);
                list.add(report);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
