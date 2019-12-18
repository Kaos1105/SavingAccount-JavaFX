package View;

import Controller.ReportDateController;
import Controller.ReportMonthController;
import Controller.SavingTypeController;
import Model.AlertDialog;
import Model.ReportMonth;
import Model.SavingType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportMonthViewController implements Initializable {
    @FXML
    ComboBox<SavingType> comboBox;
    @FXML
    DatePicker datePicker;
    @FXML
    Button viewBtn;
    @FXML
    TableView<DataRow> tableView;

    public class DataRow
    {
        private ReportMonth reportMonth;
        private Integer id;
        public DataRow (ReportMonth rp, Integer iD)
        {
            this.id=iD;
            this.reportMonth=rp;
        }

        public ReportMonth getReportMonth() {
            return reportMonth;
        }

        public void setReportMonth(ReportMonth reportMonth) {
            this.reportMonth = reportMonth;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    ArrayList<DataRow> list= new ArrayList<>();
    ArrayList<ReportMonth> listReports=new ArrayList<>();
    ArrayList<SavingType> listType;

    public ObservableList<SavingType> getType() {
        try {
            listType = SavingTypeController.getInstance().GetListActiveType();
            ObservableList<SavingType> types = FXCollections.observableArrayList(listType);
            return types;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<DataRow> getReportByMonth(int month, String name)
    {
        try
        {
            list.clear();
            listReports.clear();
            listReports= ReportMonthController.getInstance().GetListReportMonth(month, name);
            for(int i=0; i<listReports.size();i++)
            {
                list.add(new DataRow(listReports.get(i), i+1));
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
        TableColumn<DataRow, String> idCol = new TableColumn<>("STT");
        TableColumn<DataRow, String> dateCol = new TableColumn<>("Ngày");
        TableColumn<DataRow, String> openCol = new TableColumn<>("Sổ mở");
        TableColumn<DataRow, String> closeCol = new TableColumn<>("Sổ đóng");
        TableColumn<DataRow, String> subtractCol = new TableColumn<>("Chênh lệch");

        idCol.setCellValueFactory(data->new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
        dateCol.setCellValueFactory(data->new ReadOnlyStringWrapper(data.getValue().reportMonth.getDateReport()));
        openCol.setCellValueFactory(data->new ReadOnlyStringWrapper(String.valueOf(data.getValue().reportMonth.getSumOpen())));
        closeCol.setCellValueFactory(data->new ReadOnlyStringWrapper(String.valueOf(data.getValue().reportMonth.getSumClose())));
        subtractCol.setCellValueFactory(data->new ReadOnlyStringWrapper(String.valueOf(data.getValue().reportMonth.getSubtract())));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(idCol, dateCol, openCol, closeCol, subtractCol);
    }

    public void LoadComboBox()
    {
        try
        {
            comboBox.setItems(getType());
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Tải loại sổ tiết kiệm không thành công");
        }
        comboBox.getSelectionModel().selectFirst();
    }

    public void SetViewButton(ActionEvent actionEvent)
    {
        try
        {
            tableView.setItems(getReportByMonth(datePicker.getValue().getMonthValue(),comboBox.getSelectionModel().getSelectedItem().toString()));
            AlertDialog.ShowAlert("Tải báo cáo thành công");
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Tải báo cáo không thành công");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadComboBox();
        LoadTableView();
    }
}
