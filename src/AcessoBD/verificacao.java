package AcessoBD;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import Firebase.Firebase;
import Firebase.FirebaseException;
import Firebase.FirebaseResponse;
import Firebase.JacksonUtilityException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class verificacao extends Thread{
	static Firebase firebase;
	static FirebaseResponse response;
	
	static Map<String, Object> dataMap0 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap1 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap2 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap3 = new LinkedHashMap<String, Object>();



	public void run(String restaurante) throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
		String verificacao_loja = null;
		
		while(true) {
			int i = 0;
			acesso pedidos = new acesso();
			firebase = new Firebase("https://projetoteste-9d563.firebaseio.com/");

			System.out.println("VERIFICA PEDIDO");
			firebase = new Firebase("https://projetoteste-9d563.firebaseio.com/");
			response = firebase.get();
			dataMap0 = response.getBody();
			dataMap1 = (Map<String, Object>) dataMap0.get("Lojas");
				
			while(i != (Integer) dataMap1.get("Tamanho")){
				verificacao_loja = Integer.toString(i);
				dataMap2 = (Map<String, Object>) dataMap1.get(verificacao_loja);
				i++;
				if( dataMap2.get("Nome").equals(restaurante) == true)
					break;
			}
		
			dataMap3 = (Map<String, Object>) dataMap2.get("Pedidos");
			System.out.println(dataMap2.get("Pedidos"));
			
			System.out.println();
			if((Integer)dataMap3.get("Flag") == 1) {
				//

				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Novo pedido", ButtonType.CLOSE);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.CLOSE) {
					alert.close();
				}
				System.out.println("AVISO!");
				
				
				//
				dataMap3.put("Flag", 0);
				dataMap2.put("Pedidos", dataMap3);
				dataMap1.put(verificacao_loja, dataMap2);
				dataMap0.put("Lojas", dataMap1);
				
				firebase.put(dataMap0);
				return;	
			}
		}
	}
}
