package Interface.paginasDeErro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class erroLoginController {


    @FXML
    private Button botaoFecha;

    public void fechaPagina(javafx.event.ActionEvent event){
        Stage stage =  (Stage) botaoFecha.getScene().getWindow();
        stage.close();
    }
}
