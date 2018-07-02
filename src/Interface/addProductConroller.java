package Interface;


import AcessoBD.acesso;
import Firebase.FirebaseException;
import Firebase.JacksonUtilityException;
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

public class addProductConroller {


    acesso meuBD = new acesso();
    String userName = "";

    /**
     * Constructor da classe que pega nome de usuario no arquivo salvo e acessa o banco de dados
     * @throws IOException
     * @throws FirebaseException
     */
    public addProductConroller() throws IOException, FirebaseException {
        System.out.println("Acessa nome de usuario");
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

        System.out.println("User Name" + userName);

    }


    @FXML TextField productName;
    @FXML TextField ingredientesProduct;
    @FXML TextField novoProduto;

    /**
     * Pega as informações do novo item do menu e adiciona no banco de dados
     * @param event
     * @throws IOException
     * @throws FirebaseException
     * @throws JacksonUtilityException
     */
    @FXML
    private void addItem(javafx.event.ActionEvent event) throws IOException, FirebaseException, JacksonUtilityException {

        System.out.println("Novos produtos, " + productName.getText() + ingredientesProduct.getText() + novoProduto.getText());
        System.out.println("Nome do usuario" + userName);
        meuBD.insereItem(userName, productName.getText(), ingredientesProduct.getText(), Double.parseDouble(novoProduto.getText()) );

        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }
}
