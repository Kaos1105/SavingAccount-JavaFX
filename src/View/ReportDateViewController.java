package View;

import Controller.AccountController;
import Controller.ReportDateController;
import Model.Account;
import Model.AlertDialog;
import Model.ReportDate;
import Model.SavingType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportDateViewController implements Initializable {

    @FXML
    DatePicker datePicker;
    @FXML
    Button getButton;
    @FXML
    TableView<DataRow> tableView;

    public class DataRow{
        private ReportDate reportDate;
        private Integer id;
        public DataRow(ReportDate rp, Integer iD)
        {
            this.id=iD;
            this.reportDate=rp;
        }

        public ReportDate getReportDate() {
            return reportDate;
        }

        public void setReportDate(ReportDate reportDate) {
            this.reportDate = reportDate;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    ArrayList<DataRow> list= new ArrayList<>();
    ArrayList<ReportDate> listReports=new ArrayList<>();
   // ArrayList<Integer> integers=new ArrayList<>();

    public ObservableList<DataRow> getReportByDate(Date date)
    {
        try
        {
            list.clear();
            listReports.clear();
            listReports=ReportDateController.getInstance().GetListReportDate(date);
            for(int i=0; i<listReports.size();i++)
            {
                list.add(new DataRow(listReports.get(i),i+1));
            }
            ObservableList<DataRow> listObserveReport= FXCollections.observableArrayList(list);
            return listObserveReport;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void LoadTableView()
    {

        TableColumn<DataRow, String> idCol= new  TableColumn<DataRow, String>("STT");
        TableColumn<DataRow, String> nameCol = new TableColumn<DataRow, String>("Loại sổ tiết kiệm");
        TableColumn<DataRow, String> sumAddCol = new TableColumn<DataRow, String>("Tổng thu");
        TableColumn<DataRow, String> sumWithdrawCol = new TableColumn<DataRow, String>("Tổng chi");
        TableColumn<DataRow, String> subtractCol = new TableColumn<DataRow, String>("Chênh lệch");

        idCol.setCellValueFactory(data->new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
        nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getReportDate().getName()));
        sumAddCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReportDate().getSumAdd())));
        sumWithdrawCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReportDate().getSumWithdraw())));
        subtractCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getReportDate().getSubtract())));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(idCol, nameCol, sumAddCol, sumWithdrawCol,subtractCol);
    }

    public void SetGetButton(ActionEvent actionEvent)
    {

        try
        {
            tableView.setItems(getReportByDate(java.sql.Date.valueOf(datePicker.getValue())));
            AlertDialog.ShowAlert("Tải báo cáo thành công");
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Tải báo cáo không thành công");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadTableView();
    }
}
