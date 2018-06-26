package Interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {



    @FXML TextField restauranteName;
    @FXML PasswordField senhaUsuario;


    public void initialize(URL url, ResourceBundle rb){

    }


    public void handleGoToSingUp(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Go to Login");
        Parent singUpPage = FXMLLoader.load(getClass().getResource("singUp.fxml"));
        Scene singUpScene = new Scene(singUpPage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(singUpScene);
        app_stage.show();
    }


    public void handleGoToMenu(javafx.event.ActionEvent event) throws IOException {



        String senha = this.senhaUsuario.getText();
        String login = this.restauranteName.getText();


        if(!senha.equals("") && !login.equals("")){

            if(true){//chava função para chegar dados no banco de dados

                File file = new File("../userInfo.txt");
                FileWriter W = new FileWriter(file);
                W.write(login);
                W.close();

                System.out.println("Go to Menu");
                Parent loginPage = FXMLLoader.load(getClass().getResource("pedidos.fxml"));
                Scene loginScene = new Scene(loginPage);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(loginScene);
                app_stage.show();

            }else{//usuario não cadastrado no banco, retorna erro


            }
        }else{ //se falto digitar senha ou login da mensagem de erro
            Stage erroScrean = new Stage();
            Parent newPage = FXMLLoader.load(getClass().getResource("paginasDeErro/erroLogin"));
            erroScrean.setTitle("Hello World");
            erroScrean.setScene(new Scene(newPage, 997, 686));
            erroScrean.show();
        }



    }
}
