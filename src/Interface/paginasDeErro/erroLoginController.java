package Interface.paginasDeErro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class erroLoginController {


    /**
     * Função para fecar a tela de erro do login
     * @param event
     * @throws IOException
     */
    public void fechaPagina(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Login");
        Parent loginPage = FXMLLoader.load(getClass().getResource("../LogIn.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }
}
