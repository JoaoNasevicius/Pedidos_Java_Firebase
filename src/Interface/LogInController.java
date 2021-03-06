package Interface;

import AcessoBD.acesso;
import AcessoBD.verificacao;
import Firebase.FirebaseException;
import Firebase.JacksonUtilityException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    acesso meuBd = new acesso();
    verificacao tNovoPedido = new verificacao();


    @FXML TextField restauranteName;
    @FXML PasswordField senhaUsuario;

    /**
     * Construter da classe
     * @throws FirebaseException
     */
    public LogInController() throws FirebaseException {
    }

    /**
     * Inicializa junto com o Controller
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb){

    }

    /**
     * Para ir na pagina de Login
     * @param event
     * @throws IOException
     */
    public void handleGoToSingUp(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Login");
        Parent singUpPage = FXMLLoader.load(getClass().getResource("singUp.fxml"));
        Scene singUpScene = new Scene(singUpPage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(singUpScene);
        app_stage.show();
    }

    /**
     * Vai para Tela de Menu quando faz login
     * @param event
     * @throws IOException
     * @throws FirebaseException
     * @throws JacksonUtilityException
     */
    public void handleGoToMenu(javafx.event.ActionEvent event) throws IOException, FirebaseException, JacksonUtilityException {

        String senha = this.senhaUsuario.getText();
        String login = this.restauranteName.getText();

        if(!senha.equals("") && !login.equals("")){

            if(meuBd.fazerLogin(login, senha) != 1){//chava função para chegar dados no banco de dados

                if (meuBd.fazerLogin(login, senha) == 0){

                    File file = new File("userInfo.txt");
                    FileWriter W = new FileWriter(file);
                    W.write(login);
                    W.close();


                    //tNovoPedido.run(login);


                    System.out.println("Go to Menu");
                    Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
                    Scene loginScene = new Scene(loginPage);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide();
                    app_stage.setScene(loginScene);
                    app_stage.show();

                }else{
                    System.out.println(senha);
                    System.out.println("Senha errada");
                }

            }else{//usuario não cadastrado no banco, retorna erro

                System.out.println("Usuaria não cadastrado no sistema");
                Parent erroPage = FXMLLoader.load(getClass().getResource("paginasDeErro/erroLogin.fxml"));
                Scene loginScene = new Scene(erroPage);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(loginScene);
                app_stage.show();
            }
        }else{ //se falto digitar senha ou login da mensagem de erro

            System.out.println("Falta digitar usuario ou senha");
        }



    }
}
