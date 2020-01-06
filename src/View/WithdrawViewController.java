package View;

import Controller.WithdrawController;
import Model.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WithdrawViewController implements Initializable {

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
    Button closeBtn;
    @FXML
    Button openWithdrawBtn;
    @FXML
    Button resetBtn;

    public void SetOpenButton(ActionEvent actionEvent) throws ParseException {
        if (AlertDialog.ShowConfirm("Bạn chắc muốn rút tiền ?"))
        {
            int accID;
            int money;
            double canWithdraw;
            try
            {
                int temp = Integer.parseInt(idAccountText.getText());
                accID = WithdrawController.getInstance().GetActiveAccount(temp);
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
                int minMoney = WithdrawController.getInstance().GetMin_CurrMoney(accID).get(0);
                int currMoney = WithdrawController.getInstance().GetMin_CurrMoney(accID).get(1);
                money = Integer.parseInt(moneyText.getText());
                if (money > (currMoney - minMoney))
                {
                    AlertDialog.ShowAlert("Tiền rút phải tối đa " + (currMoney - minMoney) + " VNĐ\n Vì trong sổ phải có tối thiểu " + minMoney + " VNĐ\n Nếu muốn rút tiền trên hạn mức hãy tất toán sổ");
                    return;
                }
                else if (money < 0)
                {
                    AlertDialog.ShowAlert("Tiền rút ra phải lớn hơn 0");
                    return;
                }
            }
            catch(Exception e)
            {
                AlertDialog.ShowAlert("Tiền rút ra phải là một số nguyên");
                return;
            }
            if (nameText.getText() == "")
            {
                AlertDialog.ShowAlert("Tên khách hàng không được để trống");
                return;
            }
            if (datePicker.getValue().isBefore(LocalDate.now()))
            {
                AlertDialog.ShowAlert("Thời gian rút tiền không hợp lệ");
                return;
            }
            else
            {
                if (WithdrawController.getInstance().GetPeriod(accID)>0)
                {
                    Date dateDue = new java.sql.Date(WithdrawController.getInstance().getDueDate(accID).getTime());
                    if (datePicker.getValue().isBefore(dateDue.toLocalDate()))
                    {
                        if (!AlertDialog.ShowConfirm("Sổ có thời hạn chỉ có lãi khi tất toán sau ngày: " + dateDue.toString() + "\nBạn có chắc muốn rút tiền thay vì tất toán ?"))
                            return;
                    }
                }
            }
            canWithdraw = WithdrawController.getInstance().InterestMoney_CanWithdraw(accID, Date.valueOf(datePicker.getValue())).get(1);
            if (canWithdraw == 1)
            {
                try
                {
                    WithdrawController.getInstance().InsertWithdraw(new String[] { idAccountText.getText(), nameText.getText(), moneyText.getText(), "0" }, Date.valueOf(datePicker.getValue()));
                    AlertDialog.ShowAlert("Rút tiền thành công");
                    idText.setText( String.valueOf(WithdrawController.getInstance().GetMaxID()));
                }
                catch(Exception e)
                {
                    AlertDialog.ShowAlert("Rút tiền không thành công\n Xin quý khách vui lòng thử lại");
                }
            }
            else
            {
                AlertDialog.ShowAlert("Không được rút tiền trước hạn");
            }
        }
    }
    public void SetResetButton(ActionEvent actionEvent)
    {
        idAccountText.clear();
        idText.clear();
        moneyText.clear();
        nameText.clear();
    }

    public void SetClosedButton(ActionEvent actionEvent) {
        int accID;
        int money;
        double interestMoney;
        double canWithdraw;
        try
        {
            int temp = Integer.parseInt(idAccountText.getText());
            accID = WithdrawController.getInstance().GetActiveAccount(temp);
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
            money = WithdrawController.getInstance().GetMin_CurrMoney(accID).get(1);
        }
        catch(Exception e)
        {

            AlertDialog.ShowAlert("Mã sổ tiết kiệm phải là một số nguyên");
            return;
        }
        if (nameText.getText() == "")
        {
            AlertDialog.ShowAlert("Tên người rút không được để trống");
            return;
        }
        if (datePicker.getValue().isBefore(LocalDate.now()))
        {
            AlertDialog.ShowAlert("Thời gian tất toán không hợp lệ");
            return;
        }
        ArrayList<Double> tempList = WithdrawController.getInstance().InterestMoney_CanWithdraw(accID, Date.valueOf(datePicker.getValue()));
        interestMoney = tempList.get(0);
        canWithdraw = tempList.get(1);
        if (canWithdraw == 1 )
        {
            if ( AlertDialog.ShowConfirm("Nếu tất toán bạn sẽ rút " +money+ " VNĐ và lãi "+interestMoney +" VNĐ\nBạn có chắc muốn tất toán ?"))
            {
                try
                {
                    WithdrawController.getInstance().InsertWithdraw(new String[] { idAccountText.getText(), nameText.getText(), String.valueOf(money), "1"}, Date.valueOf(datePicker.getValue()));
                    moneyText.clear();
                    moneyText.setText(String.valueOf(money));
                    if (WithdrawController.getInstance().CloseAccountByID(accID))
                        AlertDialog.ShowAlert("Đóng sổ thành công\n Tất toán thành công");
                    idText.setText(String.valueOf( WithdrawController.getInstance().GetMaxID()));
                }
                catch(Exception e)
                {
                    AlertDialog.ShowAlert("Tất toán không thành công\n Xin quý khách vui lòng thử lại");
                }
            }
        }
        else
        {
            AlertDialog.ShowAlert("Không được tất toán trước hạn");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idText.setEditable(false);
    }
}
