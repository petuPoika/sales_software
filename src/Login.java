import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    /* Database */
    private Connection connect;
    private PreparedStatement pstmt;
    private ResultSet result;

    @FXML
    private Button loginButton;

    private Stage primaryStage;
    
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void onClick(ActionEvent event) {
        if (username.getText().isEmpty() || password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // alert.setTitle("Error");
            alert.setContentText("Wrong username or password");
            alert.showAndWait();
            username.clear();
            return;
        }
        String user = username.getText();
        System.out.println("Username: " + user);
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        connect = Database.connect();

        try{
            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, username.getText());
            pstmt.setString(2, password.getText());

            result = pstmt.executeQuery();
            if (result.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Login successful");
                alert.showAndWait();
                primaryStage.close();
                // Load the next scene or perform the next action
            }
            
        }catch (Exception e) {e.printStackTrace();}
    }

    public void setMainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
