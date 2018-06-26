package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.IOException;

public class menuController {



    public void handleGoPedidos(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void handleGoAddProduto(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

}
