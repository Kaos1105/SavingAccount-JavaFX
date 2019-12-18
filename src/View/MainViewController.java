package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import sample.ExternalWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    Button openAccountBtn;
    @FXML
    Button openDepositBtn;
    @FXML
    Button openWithdrawBtn;
    @FXML
    Button searchBtn;
    @FXML
    MenuButton reportBtn;
    @FXML
    Button savingTypeBtn;

    public void SetOpenAccountBtn(ActionEvent actionEvent)
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/OpenAccountView.fxml"));
        try {
            ExternalWindow.display("Phiếu mở sổ", loader, 700, 490);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SetSearchBtn(ActionEvent actionEvent)
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/AccountView.fxml"));
        try {
            ExternalWindow.display("Tra cứu sổ", loader, 890, 540);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SetSavingTypehBtn(ActionEvent actionEvent)
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SavingTypeView.fxml"));
        try {
            ExternalWindow.display("Quản lí sổ", loader, 900, 650);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SetDepositBtn(ActionEvent actionEvent)
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/DepositView.fxml"));
        try {
            ExternalWindow.display("Phiếu gửi tiền", loader, 700, 490);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SetWithdrawBtn(ActionEvent actionEvent)
    {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/WithdrawView.fxml"));
        try {
            ExternalWindow.display("Phiếu rút tiền", loader, 700, 490);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    EventHandler<ActionEvent> ReportDate = event -> {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/ReportDateView.fxml"));
        try {
            ExternalWindow.display("Báo cáo ngày", loader, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    EventHandler<ActionEvent> ReportMonth = event -> {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/ReportMonthView.fxml"));
        try {
            ExternalWindow.display("Báo cáo tháng", loader, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItem m1 = new MenuItem("Báo cáo ngày");
        MenuItem m2 = new MenuItem("Báo cáo tháng");
        m1.setOnAction(ReportDate);
        m2.setOnAction(ReportMonth);
        reportBtn.getItems().clear();
        reportBtn.getItems().addAll(m1, m2);
    }
}
