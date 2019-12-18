package View;

import Controller.AccountController;
import Model.Account;
import Model.AlertDialog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccountViewController implements Initializable {

    @FXML
    Button searchButton;
    @FXML
    Button viewButton;
    @FXML
    TextField idText;
    @FXML
    TableView<Account> tableView;

    public ObservableList<Account> getAccount()
    {
       try
       {
           ArrayList<Account> list = AccountController.getInstance().GetListAccount();
           ObservableList<Account> accounts = FXCollections.observableArrayList(list);
           return  accounts;
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }
    public ObservableList<Account> getAccountByID(int id)
    {
        try
        {
            ArrayList<Account> list = AccountController.getInstance().GetAccountByID(id);
            ObservableList<Account> accounts = FXCollections.observableArrayList(list);
            return  accounts;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void LoadTableView()
    {
        TableColumn<Account, String> idCol = new TableColumn<Account, String>("Mã sổ");
        TableColumn<Account, String> idTypeCol = new TableColumn<Account, String>("Mã loại tiết kiệm");
        TableColumn<Account, String> nameCol = new TableColumn<Account, String>("Tên khách hàng");
        TableColumn<Account, String> cmndCol = new TableColumn<Account, String>("Số CMND");
        TableColumn<Account, String> addressCol = new TableColumn<Account, String>("Địa chỉ");
        TableColumn<Account, String> dateOpenCol = new TableColumn<Account, String>("Ngày mở sổ");
        TableColumn<Account, String> moneyCol = new TableColumn<Account, String>("Số dư");
        TableColumn<Account, String> statusCol = new TableColumn<Account, String>("Trạng thái");
        TableColumn<Account, String> dateDueCol = new TableColumn<Account, String>("Ngày đáo hạn");
        TableColumn<Account, String> dateCanAddCol = new TableColumn<Account, String>("Ngày được gửi thêm");
        TableColumn<Account, String> dateCanWithDrawCol = new TableColumn<Account, String>("Ngày được rút tiền");

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idTypeCol.setCellValueFactory(new PropertyValueFactory<>("TypeID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        cmndCol.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        dateOpenCol.setCellValueFactory(new PropertyValueFactory<>("DateOpen"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("Money"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        statusCol.setCellValueFactory(c-> new SimpleStringProperty(changeStatusCell(c.getValue().Status)));
        dateDueCol.setCellValueFactory(new PropertyValueFactory<>("DateDue"));
        dateCanAddCol.setCellValueFactory(new PropertyValueFactory<>("DateCanAdd"));
        dateCanWithDrawCol.setCellValueFactory(new PropertyValueFactory<>("DateCanWithDraw"));

        tableView.getColumns().clear();
        tableView.getColumns().addAll(idCol,  idTypeCol, nameCol, cmndCol, addressCol, moneyCol, statusCol, dateOpenCol, dateDueCol, dateCanAddCol, dateCanWithDrawCol);
    }
    public String changeStatusCell(boolean status)
    {
        if(status==true)
            return "Hoạt động";
        return "Ngừng hoạt động";
    }
    public void SetSearchButton(ActionEvent actionEvent)
    {
        //tableView.getItems().clear();
        int id=-1;
        try
        {
            id=Integer.parseInt(idText.getText());
            tableView.setItems(getAccountByID(id));
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Mã sổ tiết kiệm phải là một số nguyên");
        }
    }

    public void SetViewButton(ActionEvent actionEvent)
    {
        tableView.setItems(getAccount());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadTableView();
    }
}
