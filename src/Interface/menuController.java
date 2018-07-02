package Interface;

import AcessoBD.acesso;
import Firebase.FirebaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import javax.print.DocFlavor;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class menuController implements Initializable {




    @FXML ListView<String> menuList;

    /**
     * Função inicializadora do controller
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            itensMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FirebaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aeessa o bacno de dados para pegar os dados salvo do menu
     * @throws IOException
     * @throws FirebaseException
     */
    public void itensMenu() throws IOException, FirebaseException {
        System.out.println("Atualizando Menu");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("userInfo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String userName;
        try{
            assert br != null;
            userName = br.readLine();

        } finally {
            assert br != null;
            br.close();
        }

        System.out.println("User Name " + userName);

        acesso meuBD = new acesso();

        Map<String, Object> dados = meuBD.retornarMenu(userName);
        System.out.println(dados);

        int tamanho  = (Integer)dados.get("Tamanho");

        Map<String, Object> dado;

        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < tamanho; i++){
            dado = (Map<String, Object>) dados.get(Integer.toString(i));
            System.out.println((String)dado.get("Nome") +"\t\t\t\t\t" + (String)dado.get("Descricao") + "\t\t\t\t\t" + Double.toString((Double) dado.get("Preco")));


            items.add((String)dado.get("Nome") + "\t\t\t\t\t" + (String)dado.get("Descricao") + "\t\t\t\t\t" + Double.toString((Double) dado.get("Preco")) );

        }
        System.out.println(items);
        menuList.setItems(items);


    }

    /**
     * Inicializa os dados do menu com os valores que esta no menu
     * @throws IOException
     * @throws FirebaseException
     */
    public menuController(){
        super();
    }

    /**
     * Vai para a tela de pedidos
     * @param event
     * @throws IOException
     */
    public void handleGoPedidos(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to pedidos");
        Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    /**
     * Vai para tela para adicionar um novo pedido os menu
     * @param event
     * @throws IOException
     */
    public void handleGoAddProduto(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Abre tela para adicionar produto");
        Parent loginPage = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }

    /**
     * Deleta um pedido do menu
     * @param event
     * @throws IOException
     */
    public void handleGoDeletaProduto(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Abre tela para deletar produto");
        Parent loginPage = FXMLLoader.load(getClass().getResource("deletaProduto.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }






}
