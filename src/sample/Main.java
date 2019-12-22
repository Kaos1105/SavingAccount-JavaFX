package sample;

import Model.AlertDialog;
import Model.SQLServerConnUtils_SQLJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        Parent root = (Parent) loader.load();
        window.setTitle("Màn hình chính");
        window.setScene(new Scene(root, 450, 450));
        window.setResizable(false);
        window.show();
        window.setOnCloseRequest(e->{
            e.consume();
            OnCloseProgram();
        });
//        Connection connection= DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Demo;integratedSecurity=true;");
//        Statement statement = connection.createStatement();
//
//        String sql = "Select Emp_Id, Emp_No, Emp_Name from Employee";
//
//        ResultSet rs = statement.executeQuery(sql);
//
//        while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
//            int empId = rs.getInt(1);
//            String empNo = rs.getString(2);
//            String empName = rs.getString("Emp_Name");
//            System.out.println("--------------------");
//            System.out.println("EmpId:" + empId);
//            System.out.println("EmpNo:" + empNo);
//            System.out.println("EmpName:" + empName);
//        }
//        connection.close();
    }
    public void OnCloseProgram()
    {
        try {
            Connection conn = SQLServerConnUtils_SQLJDBC.getSQLServerConnection();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(AlertDialog.ShowConfirm("Bạn có chắc muốn thoát ?"))
            window.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
