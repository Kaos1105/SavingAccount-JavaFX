package View;

import Controller.SavingTypeController;
import Model.AlertDialog;
import Model.SavingType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SavingTypeViewController implements Initializable {
    @FXML
    Button btnEdit;
    @FXML
    Button btnDelete;
    @FXML
    Button btnReload;
    @FXML
    Button btnAdd;
    @FXML
    TextField  tBPeriodEdit;
    @FXML
    TextField tBRateEdit;
    @FXML
    TextField  tBRateMonthEdit;
    @FXML
    TextField  tBDateAddEdit;
    @FXML
    TextField tBDateWithdrawEdit;
    @FXML
    TextField tBMoneyAddEdit;
    @FXML
    TextField  tBMoneyEdit;
    @FXML
    TextField tBIDEdit;
    @FXML
    CheckBox cBIsActive;
    @FXML
    TextField  tBPeriodAdd;
    @FXML
    TextField tBRateAdd;
    @FXML
    TextField  tBRateMonthAdd;
    @FXML
    TextField  tBDateAddAdd;
    @FXML
    TextField tBDateWithdrawAdd;
    @FXML
    TextField tBMoneyAddAdd;
    @FXML
    TextField  tBMoneyAdd;
    @FXML
    TextField tBTypeNameAdd;
    @FXML
    CheckBox cBIsActiveAdd;
    @FXML
    ComboBox<SavingType> typeComboBox;

    ArrayList<SavingType> listType;

    public ObservableList<SavingType> getType() {
        try {
            listType = SavingTypeController.getInstance().GetListType();
            ObservableList<SavingType> types = FXCollections.observableArrayList(listType);
            return types;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String CastIfNull(Object o, TextField textField)
    {
        if(o==null)
        {
            textField.setEditable(false);
            return "Không có thuộc tính";
        }
        else
        {
            textField.setEditable(true);
            return o.toString();
        }
    }

    Boolean CheckInputUpdate(String[] list, TextField textField1, TextField textField2)
    {
//        TextField textField1=null;
//        TextField textField2=null;
//        if(textFields!=null)
//        {
//            textField1=textFields[0];
//            textField2=textFields[1];
//        }
        int period;
        try
        {
            period=Integer.parseInt(list[0]);
        }
        catch (Exception e)
        {
            AlertDialog.ShowAlert("Kỳ hạn phải lớn hơn hoặc bằng 0");
            return  false;
        }
        if(period==0)
        {
            if(textField1!=null)
            {
                textField1.clear();
                textField1.setEditable(false);
            }
            try
            {
                if(Double.parseDouble(list[2])<0)
                {
                    AlertDialog.ShowAlert("Lãi suất không kì hạn phải lớn hơn 0");
                    return false;
                }
            }
            catch (Exception e)
            {
                AlertDialog.ShowAlert("Lãi suất không kì hạn phải là một số thực");
                return  false;
            }
        }
        else if(period>0)
        {
            if(textField2!=null)
            {
                textField2.clear();
                textField2.setEditable(false);
            }
            try
            {
                if(Double.parseDouble(list[1])<0)
                {
                    AlertDialog.ShowAlert("Lãi suất có kì hạn phải lớn hơn 0");
                    return false;
                }

            } catch (Exception e)
            {
                AlertDialog.ShowAlert("Lãi suất có kì hạn phải là một số thực");
                return false;
            }
            try
            {
                if(Integer.parseInt(list[5])<period)
                {
                    AlertDialog.ShowAlert("Ngày được thêm tiền vào sổ phải lớn hơn hoặc bằng kỳ hạn: "+period+" ngày");
                    return false;
                }
            }
            catch (Exception e)
            {
                AlertDialog.ShowAlert("Số ngày được gởi thêm phải là một số nguyên");
                return false;
            }
        }
        else {
            AlertDialog.ShowAlert("Kỳ hạn phải lớn hơn 0");
            return false;
        }
        try
        {
            if (Integer.parseInt(list[3]) < 0)
            {
                AlertDialog.ShowAlert("Tiền mở sổ phải lớn hơn 0");
                return false;
            }
        }
        catch(Exception e)
        {
            AlertDialog.ShowAlert("Số tiền mở sổ phải là một số nguyên");
            return false;
        }
        try
        {
            if (Integer.parseInt(list[4]) < 0)
            {
                AlertDialog.ShowAlert("Tiền thêm vào sổ phải lớn hơn 0");
                return false;
            }
        }
        catch(Exception e)
        {
            AlertDialog.ShowAlert("Số tiền gửi thêm phải là một số nguyên");
            return false;
        }
        try
        {
            if (Integer.parseInt(list[5]) < 0)
            {
                AlertDialog.ShowAlert("Ngày được thêm tiền vào sổ phải lớn hơn 0");
                return false;
            }
        }
        catch(Exception e)
        {
            AlertDialog.ShowAlert("Số ngày được gởi thêm phải là một số nguyên");
            return false;
        }
        try
        {
            if (Integer.parseInt(list[6]) < 0)
            {
                AlertDialog.ShowAlert("Ngày được rút tiền phải lớn hơn 0");
                return false;
            }
        }
        catch(Exception e)
        {
            AlertDialog.ShowAlert("Số ngày được rút phải là một số nguyên");
            return false;
        }
        return true;
    }

    public void LoadComboBox() {
        if (getType() != null) ;
        {
            typeComboBox.setItems(getType());
//            Callback<ListView<SavingType>, ListCell<SavingType>> factory = lv -> new ListCell<SavingType>() {
//
//                @Override
//                protected void updateItem(SavingType item, boolean empty) {
//                    super.updateItem(item, empty);
//                    setText(empty ? "" : item.Name);
//                }
//
//            };
//
//            typeComboBox.setCellFactory(factory);
//            typeComboBox.setButtonCell(factory.call(null));
        }
        typeComboBox.getSelectionModel().selectFirst();
    }
    public void ComboBoxListener()
    {
        typeComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            try {
                listType=SavingTypeController.getInstance().GetListType();
            }
            catch (Exception e)
            {
                AlertDialog.ShowAlert("Load failed");
            }
            tBPeriodEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getPeriod()));
            tBRateEdit.setText(CastIfNull(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getInterestRate(), tBRateEdit));
            tBRateMonthEdit.setText(CastIfNull(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getInterestRateMonth(), tBRateMonthEdit));
            tBDateAddEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getDateCanAdd()));
            tBDateWithdrawEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getDateWithdraw()));
            tBMoneyAddEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getMinAddMoney()));
            tBMoneyEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getMinMoney()));
            tBIDEdit.setText(String.valueOf(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getID()));
            cBIsActive.setSelected(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).isActive());
        });
    }
    public void SetButtonEdit(ActionEvent actionEvent)
    {
        if(AlertDialog.ShowConfirm("Bạn chắc muốn sửa loại tiết kiệm ?"))
        {
            if(!CheckInputUpdate(new String[]{tBPeriodEdit.getText(), tBRateEdit.getText(), tBRateMonthEdit.getText(), tBMoneyEdit.getText(), tBMoneyAddEdit.getText(), tBDateAddEdit.getText(), tBDateWithdrawEdit.getText() }, null, null))
                return;
            String isActive;
            if (cBIsActive.isSelected())
                isActive = "1";
            else isActive = "0";
            try
            {
                SavingTypeController.getInstance().UpdateSavingType(new String[] { typeComboBox.getValue().toString(), tBPeriodEdit.getText(), tBRateEdit.getText(), tBRateMonthEdit.getText(), tBMoneyEdit.getText(), tBMoneyAddEdit.getText(), tBDateAddEdit.getText(), tBDateWithdrawEdit.getText(), isActive });
                AlertDialog.ShowAlert("Cập nhật loại tiết kiệm thành công\n");
                LoadComboBox();
            }
            catch(Exception e)
            {
                AlertDialog.ShowAlert("Cập nhật loại tiết kiệm không thành công !\n Xin quý khách vui lòng thử lại !");
            }
        }
    }
    public void SetButtonAdd(ActionEvent actionEvent)
    {
        if(AlertDialog.ShowConfirm("Bạn chắc muốn thêm loại tiết kiệm ?"))
        {
            if(!CheckInputUpdate(new String[]{tBPeriodAdd.getText(), tBRateAdd.getText(), tBRateMonthAdd.getText(), tBMoneyAdd.getText(), tBMoneyAddAdd.getText(), tBDateAddAdd.getText(), tBDateWithdrawAdd.getText() }, tBRateAdd, tBRateMonthAdd))
                return;
            String isActive;
            if (cBIsActiveAdd.isSelected())
                isActive = "1";
            else isActive = "0";
            try
            {
                SavingTypeController.getInstance().InsertSavingType(new String[] { tBTypeNameAdd.getText(), tBPeriodAdd.getText(), tBRateAdd.getText(), tBRateMonthAdd.getText(), tBMoneyAdd.getText(), tBMoneyAddAdd.getText(), tBDateAddAdd.getText(), tBDateWithdrawAdd.getText(), isActive });
                AlertDialog.ShowAlert("Thêm loại tiết kiệm thành công\n");
                LoadComboBox();
            }
            catch(Exception e)
            {
                AlertDialog.ShowAlert("Thêm loại tiết kiệm không thành công !\n Xin quý khách vui lòng thử lại !");
            }
        }
    }
    public void SetButtonDelete(ActionEvent actionEvent)
    {
        if(AlertDialog.ShowConfirm("Bạn có chắc muốn xóa loại tiết kiệm này không ?"))
        {
            if(SavingTypeController.getInstance().CheckSavingTypeInUse(listType.get(typeComboBox.getSelectionModel().getSelectedIndex()).getID())==1)
            {
                AlertDialog.ShowAlert("Loại tiết kiệm này đang được sử dụng, không thể xóa\n Hủy kích hoạt thay vì xóa");
                return;
            }
            try
            {
                SavingTypeController.getInstance().DeleteSavingType(typeComboBox.getValue().toString());
                AlertDialog.ShowAlert("Xóa loại tiết kiệm thành công");
                LoadComboBox();
            }
            catch (Exception e)
            {
                AlertDialog.ShowAlert("Xóa loại tiết kiệm không thành công !");
            }
        }
    }
    public void SetButtonReset(ActionEvent actionEvent)
    {
        tBTypeNameAdd.clear();
        tBPeriodAdd.clear();
        tBRateAdd.clear();
        tBRateAdd.setEditable(true);
        tBRateMonthAdd.clear();
        tBRateMonthAdd.setEditable(true);
        tBMoneyAdd.clear();
        tBMoneyAddAdd.clear();
        tBDateAddAdd.clear();
        tBDateWithdrawAdd.clear();
    }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            LoadComboBox();
            ComboBoxListener();
            tBPeriodEdit.setEditable(false);
            tBIDEdit.setEditable(false);
        }
    }
