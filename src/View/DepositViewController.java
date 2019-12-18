package View;

import Controller.DepositController;
import Model.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class DepositViewController implements Initializable {
    @FXML
    TextField idText;
    @FXML
    TextField idAccountText;
    @FXML
    TextField moneyText;
    @FXML
    TextField nameText;
    @FXML
    DatePicker datePicker;
    @FXML
    Button resetBtn;
    @FXML
    Button openDepositBtn;

    public void SetOpenButton(ActionEvent actionEvent)
    {
        if (AlertDialog.ShowConfirm("Bạn chắc muốn gửi tiền ?"))
        {
            int accID;
            int money;
            try
            {
                int temp = Integer.parseInt(idAccountText.getText());
                accID = DepositController.getInstance().GetActiveAccount(temp);
                if (accID == -1)
                {
                    AlertDialog.ShowAlert("Mã sổ tiết kiệm không tồn tại");
                    return;
                }
                if (accID == -2)
                {
                    AlertDialog.ShowAlert("Sổ tiết kiệm đã hết hạn hoặc đã khóa");
                    return;
                }
            }
            catch(Exception e)
            {

                AlertDialog.ShowAlert("Mã sổ tiết kiệm phải là một số nguyên");
                return;
            }
            try
            {
                int minMoney = DepositController.getInstance().GetMinMoney(accID);
                money = Integer.parseInt(moneyText.getText());
                if (money < minMoney)
                {
                    AlertDialog.ShowAlert("Tiền gửi thêm phải tối thiểu " + minMoney + " VNĐ");
                    return;
                }
                else if (money < 0)
                {
                    AlertDialog.ShowAlert("Tiền gửi thêm phải lớn hơn 0");
                    return;
                }
            }
            catch(Exception e)
            {
                AlertDialog.ShowAlert("Tiền gửi thêm phải là một số nguyên");
                return;
            }
            if (nameText.getText() == "")
            {
                AlertDialog.ShowAlert("Tên người gửi không được để trống");
                return;
            }
            if(datePicker.getValue().isBefore(LocalDate.now()))
            {
                AlertDialog.ShowAlert("Thời gian gởi tiền không hợp lệ");
                return;
            }
            else
            {
                try
                {
                    Date minDateAdd = (Date) DepositController.getInstance().GetDateAdd(accID);
                    if (datePicker.getValue().isBefore(minDateAdd.toLocalDate()))
                    {
                        AlertDialog.ShowAlert("Chỉ được gửi thêm tiền sau ngày: " + minDateAdd.toString());
                        return;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    AlertDialog.ShowAlert("Tải ngày không thành công");
                    return;
                }

            }
            try
            {
                DepositController.getInstance().InsertDeposit(new String[] { idAccountText.getText(), nameText.getText(), moneyText.getText() }, java.sql.Date.valueOf(datePicker.getValue()));
                AlertDialog.ShowAlert("Gửi thêm tiền thành công");
                idText.setText(String.valueOf(DepositController.getInstance().GetMaxID()));
            }
            catch(Exception e)
            {
                AlertDialog.ShowAlert("Gửi tiền không thành công\n Xin quý khách vui lòng thử lại");
            }
        }
    }

    public void SetResetButton(ActionEvent actionEvent)
    {
        idText.clear();
        idAccountText.clear();
        moneyText.clear();
        nameText.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setEditable(false);
    }
}
