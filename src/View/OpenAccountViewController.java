package View;

import Controller.AccountController;
import Controller.SavingTypeController;
import Model.AlertDialog;
import Model.SavingType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OpenAccountViewController implements Initializable {
    @FXML
    TextField idText;
    @FXML
    ComboBox<SavingType> typeComboBox;
    @FXML
    TextField moneyText;
    @FXML
    TextField nameText;
    @FXML
    TextField cmndText;
    @FXML
    TextField addressText;
    @FXML
    DatePicker datePicker;
    @FXML
    Button openButton;
    @FXML
    Button restButton;

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

    public void LoadComboBox() {
        if (getType() != null) ;
        {
            typeComboBox.setItems(getType());
        }
        typeComboBox.getSelectionModel().selectFirst();
    }

    public void SetOpenButton(ActionEvent actionEvent)
    {
        long money;
        int cmnd;
        try
        {
            money = Long.parseLong(moneyText.getText());
            int minMoney = listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).MinMoney;
            if (money < minMoney)
            {
                AlertDialog.ShowAlert("Số tiền mở sổ phải tối thiểu " + minMoney + " VNĐ");
                return;
            }
        }
        catch(Exception e)
        {
            AlertDialog.ShowAlert("Số tiền mở sổ phải là một số nguyên");
            return;
        }
        if (nameText.getText() == "")
        {
            AlertDialog.ShowAlert("Tên khách hàng không được để trống");
            return;
        }
        try
        {
            cmnd = Integer.parseInt(cmndText.getText());
            for (char c: cmndText.getText().toCharArray())
            {
                if (cmndText.getText().charAt(0)== '0')
                {
                    if (Math.floor(Math.log10(cmnd) + 2) != 9)
                    {
                        AlertDialog.ShowAlert("Số CMND phải là một nguyên có 9 chữ số");
                        return;
                    }
                }
                else
                {
                    if (Math.floor(Math.log10(cmnd) + 1) != 9)
                    {
                        AlertDialog.ShowAlert("Số CMND phải là một nguyên có 9 chữ số");
                        return;
                    }
                }
            }
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Số CMND không hợp lệ");
            return;
        }
        if (addressText.getText() == "")
        {
            AlertDialog.ShowAlert("Địa chỉ không được để trống");
            return;
        }
        if(datePicker.getValue().isBefore(LocalDate.now()))
        {
            AlertDialog.ShowAlert("Thời gian mở sổ không hợp lệ");
            return;
        }
        try
        {
            AccountController.getInstance().InsertAccount(new int[] { listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).ID, (int)money }, new String[] { nameText.getText(), cmndText.getText(), addressText.getText() }, Date.valueOf(datePicker.getValue()));
            AlertDialog.ShowAlert("Mở sổ thành công");
            idText.setText(String.valueOf(AccountController.getInstance().GetMaxAccountID()));
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Mở sổ không thành công\n Xin quý khách vui lòng thử lại");
        }
    }

    public void SetResetButton(ActionEvent actionEvent)
    {
        idText.clear();
        moneyText.clear();
        nameText.clear();
        cmndText.clear();
        addressText.clear();
        LoadComboBox();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadComboBox();
        idText.setEditable(false);
    }
}
