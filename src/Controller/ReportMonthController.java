package Controller;

import Model.DataProvider;
import Model.ReportDate;
import Model.ReportMonth;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReportMonthController {
    private static ReportMonthController Instance;

    public static ReportMonthController getInstance() {
        if(Instance==null) Instance=new ReportMonthController();
        return Instance;
    }

    public static void setInstance(ReportMonthController instance) {
        Instance = instance;
    }
    private ReportMonthController(){}

    public ArrayList<ReportMonth> GetListReportMonth(int month, String name)
    {
        ArrayList<ReportMonth> list = new ArrayList<>();
        String query = "select * from (select stk.NgayMoSo, count(stk.MaLoaiTietKiem) as SoMo from SoTietKiem stk, LoaiTietKiem ltk where MONTH(stk.NgayMoSo) = " + month + " and ltk.TenLoaiTietKiem = N'" + name + "' and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem group by stk.NgayMoSo) t1 full outer join (select prt.NgayRut, count(prt.MaPhieuRutTien) as SoDong from PhieuRutTien prt, LoaiTietKiem ltk, SoTietKiem stk where MONTH(prt.NgayRut) =" + month + " and prt.TatToan=1 and ltk.TenLoaiTietKiem = N'" + name + "' and stk.MaLoaiTietKiem = ltk.MaLoaiTietKiem and stk.MaSoTietKiem = prt.MaSoTietKiem group by prt.NgayRut) t2 on t1.NgayMoSo = t2.NgayRut order by NgayRut asc, NgayMoSo asc";
        ResultSet resultSet = DataProvider.getInstance().ExecuteQuery(query, null);
        try
        {
            while (resultSet.next())
            {
                ReportMonth report = new ReportMonth(resultSet);
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
