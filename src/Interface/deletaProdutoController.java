package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import AcessoBD.acesso;
import Firebase.FirebaseException;
import Firebase.JacksonUtilityException;

public class deletaProdutoController {


    @FXML  TextField productName;
    String userName = "";

    /**
     * Chamada quando clica para deletar um item do menu
     * @param event
     * @throws IOException
     * @throws FirebaseException
     * @throws JacksonUtilityException
     */
    public void deletaProduto(javafx.event.ActionEvent event) throws IOException, FirebaseException, JacksonUtilityException {
    	acesso meuBD = new acesso();
    	
    	
    	//acessa restaurante nome
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
    	meuBD.removeItem(userName, productName.getText());
    	
    	
    	
        System.out.println("Produto deletao, " + productName.getText());

        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }
}
