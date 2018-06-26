package Interface;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;


public class pedidosController {



    @FXML
    ListView<String> pedidosListas;


    public void handleGoToMenu(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Menu");
        Parent loginPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void handleAtualizaPedidos(ActionEvent event) throws IOException{
        System.out.println("Atualizando pedidos do banco de dados ");

        String produto = "batata";
        String qnt = "1";
        String comprador = "Giovanni";
        String mesa = "10";

        System.out.println(produto + "\t" + qnt + "\t" + comprador + "\t" + mesa);

        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(" ");
        items.add(produto + "\t\t\t\t\t" + qnt + "\t\t\t\t\t" + comprador + "\t\t\t\t\t\t" + mesa);

        this.pedidosListas.setItems(items);



    }
}
