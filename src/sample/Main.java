package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Màn hình chính");
        primaryStage.setScene(new Scene(root, 450, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
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


    public static void main(String[] args) {
        launch(args);
    }
}
