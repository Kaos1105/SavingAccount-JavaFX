package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ExternalWindow {
    public static void display(String title, FXMLLoader loader, int width, int height) throws IOException {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setResizable(false);

        //Display window and wait for it to be closed before returning
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, width, height);
        window.setScene(scene);
        window.showAndWait();
    }
}
