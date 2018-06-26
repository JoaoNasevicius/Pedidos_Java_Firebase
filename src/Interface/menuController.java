package Interface;

import AcessoBD.acesso;
import Firebase.FirebaseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class menuController {


    acesso meuBD = new acesso();
    @FXML ListView menuList;


    String userName = "";
    public menuController() throws IOException, FirebaseException {
        System.out.println("Atualizando Menu");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("userInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
             userName = br.readLine();

        } finally {
            br.close();
        }

        System.out.println("User Name " + userName);

        Map<String, Object> dados = meuBD.retornarMenu(userName);
        System.out.println(dados);


    }

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
        System.out.println("Abre tela para adicionar produto");
        Parent loginPage = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }


    public void handleGoDeletaProduto(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Abre tela para deletar produto");
        Parent loginPage = FXMLLoader.load(getClass().getResource("deletaProduto.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    public void handleGoAddConfig(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Config");
        Parent loginPage = FXMLLoader.load(getClass().getResource("configuracoes.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }


}
