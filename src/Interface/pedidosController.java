package Interface;


import AcessoBD.acesso;
import Firebase.FirebaseException;
import Firebase.JacksonUtilityException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;


public  class pedidosController implements Initializable {

    @FXML
    TextField idProdutoPronto;

    acesso meuBD = new acesso();
    String userName = "";

    @FXML ListView<String> pedidosListas;

    /**
     * Constructr da Classe
     * @throws FirebaseException
     */
    public pedidosController() throws FirebaseException {
    }

    /**
     * Inicializa junto com o controller
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            pegaNome();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FirebaseException e) {
            e.printStackTrace();
        }

        try {
            atualizaPedidos();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FirebaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Função acessa o banco de dados e atualiza a lista de pedidos
     * @throws IOException
     * @throws FirebaseException
     */
    public void atualizaPedidos() throws IOException, FirebaseException {
        Map<String, Object> pedidos = meuBD.retornarPedidos(userName);

        int qnt;
        qnt = (Integer) pedidos.get("Tamanho");

        ObservableList<String> items = FXCollections.observableArrayList();

        final String[] pedidosNomes = {""};
        for (int i = 0; i < qnt; i++){
            Map<String, Object> pedido = (Map<String, Object>) pedidos.get(Integer.toString(i));

            Map<String, Object> pedidosItems = (Map<String, Object>) pedido.get("Pedido");

            pedidosItems.entrySet().stream().forEach(item -> pedidosNomes[0] += item.getValue() + " " + item.getKey() + "\t");


            items.add((String)pedido.get("Nome") + "\t\t\t\t\t" + pedidosNomes[0] + "\t\t\t\t\t" + pedido.get("Comprador") );

        }
        this.pedidosListas.setItems(items);
    }


    /**
     * Acessa o arquivo salvo em disco pegando o nome de usuario para poder acessa o banco de dados posteiormente
     * @throws IOException
     * @throws FirebaseException
     */
    public void pegaNome() throws IOException, FirebaseException {
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

    /**
     * Botão para ir para o menu
     * @param event
     * @throws IOException
     */
    public void handleGoToMenu(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Menu");
        Parent loginPage = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene = new Scene(loginPage);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(loginScene);
        app_stage.show();
    }



    /**
     * Botão para atualizar os novos produtos
     * @param event
     * @throws IOException
     * @throws FirebaseException
     */
    public void handleAttProducts(javafx.event.ActionEvent event) throws IOException, FirebaseException {


        atualizaPedidos();

    }

    /**
     * Seta um pedido como pronto
     * @param event
     * @throws UnsupportedEncodingException
     * @throws FirebaseException
     * @throws JacksonUtilityException
     */
    public void handlePedidoPronto(javafx.event.ActionEvent event) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {

        String id = idProdutoPronto.getText();
        meuBD.pedidoPronto(userName, id);



    }
}
