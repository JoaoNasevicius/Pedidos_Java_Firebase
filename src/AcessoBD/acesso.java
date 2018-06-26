package AcessoBD;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import Firebase.*;

public class acesso {
	static Firebase firebase;
	static FirebaseResponse response;
	
	static Map<String, Object> dataMap0 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap1 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap2 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap3 = new LinkedHashMap<String, Object>();
	static Map<String, Object> dataMap4 = new LinkedHashMap<String, Object>();
	
	public static void criaMenu(String restaurante) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
	
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Restaurantes");
		dataMap4 = (Map<String,Object>) dataMap1.get(restaurante);
		
		dataMap3.put("Tamanho", 0);
		dataMap2.put("Cardapio", dataMap3);
		dataMap2.put("Cor", (String) dataMap4.get("Cor"));
		dataMap1 =  (Map<String, Object>) dataMap0.get("Lojas");
		dataMap2.put("Nome", restaurante);
		dataMap1.put("Loja" + (Integer) dataMap1.get("Tamanho"), dataMap2);
		dataMap1.put("Tamanho", (Integer) dataMap1.get("Tamanho") + 1);
		dataMap0.put("Lojas", dataMap1);
	
		firebase.put(dataMap0);
	}
	
	public static void insereItem(String restaurante, String nome, String descricao, double preco) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		String verificacao_loja = null, verificacao_item;
		int i = 0;
		
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Lojas");

		System.out.println((Integer) dataMap1.get("Tamanho"));
			
		while(i != (Integer) dataMap1.get("Tamanho")){
			verificacao_loja = "Loja" + i;
			dataMap2 = (Map<String, Object>) dataMap1.get(verificacao_loja);
			i++;
			if( dataMap2.get("Nome").equals(restaurante) == true) {
				break;
			}
		}
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		verificacao_item = "Item" + (Integer) dataMap3.get("Tamanho");
		dataMap3.put("Tamanho", (Integer) dataMap3.get("Tamanho") + 1);
		
		dataMap4.put("Descricao", descricao);
		dataMap4.put("Nome", nome);
		dataMap4.put("Preco", preco);
		
		dataMap3.put(verificacao_item, dataMap4);
		dataMap2.put("Cardapio", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		firebase.put(dataMap0);
	}
	
	public static void atualizaItem(String restaurante, String nome, String descricao, double preco) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		String verificacao_loja = null, verificacao_item = null;
		int i = 0, int_aux;
		Map map_aux = new LinkedHashMap<String, Object>();
		
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Lojas");
			
		while(i != (Integer) dataMap1.get("Tamanho")){
			verificacao_loja = "Loja" + i;
			dataMap2 = (Map<String, Object>) dataMap1.get(verificacao_loja);
			i++;
			if( dataMap2.get("Nome").equals(restaurante) == true)
				break;
		}
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		i = 0;
		while(i != (Integer) dataMap3.get("Tamanho")){
			verificacao_item = "Item" + i;
			dataMap4 = (Map<String, Object>) dataMap3.get(verificacao_item);
			if(dataMap4.get("Nome").equals(nome) == true)
				break;
			i++;
		}

		dataMap4.put("Descricao", descricao);
		dataMap4.put("Preco", preco);
		
		dataMap3.put("Item" + i, dataMap4);
		dataMap2.put("Cardapio", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		
		firebase.put(dataMap0);
		
	}
	
	public static void removeItem(String restaurante, String nome) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		String verificacao_loja = null, verificacao_item = null;
		int i = 0, int_aux;
		Map map_aux = new LinkedHashMap<String, Object>();
		
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Lojas");
			
		while(i != (Integer) dataMap1.get("Tamanho")){
			verificacao_loja = "Loja" + i;
			dataMap2 = (Map<String, Object>) dataMap1.get(verificacao_loja);
			i++;
			if( dataMap2.get("Nome").equals(restaurante) == true)
				break;
		}
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		i = 0;
		while(i != (Integer) dataMap3.get("Tamanho")){
			verificacao_item = "Item" + i;
			dataMap4 = (Map<String, Object>) dataMap3.get(verificacao_item);
			if(dataMap4.get("Nome").equals(nome) == true)
				break;
			i++;
		}
		
		for(int j = i; j < (Integer) dataMap3.get("Tamanho") - 1; j++) {	
			int_aux = j + 1;
			verificacao_item = "Item" + int_aux;
			dataMap3.put("Item" + j, dataMap3.get(verificacao_item));
			dataMap3.remove(verificacao_item);
		}

		dataMap3.put("Tamanho", (Integer) dataMap3.get("Tamanho") - 1);
		dataMap3.remove("Item" + (Integer)dataMap3.get("Tamanho"));

		dataMap2.put("Cardapio", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		
		firebase.put(dataMap0);
	}
	
	public static void criarCadastro(String restaurante,String senha,String descricao,String email, String cor) throws UnsupportedEncodingException, JacksonUtilityException, FirebaseException {
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 = new LinkedHashMap<String, Object>();
		
		response = firebase.get();
		dataMap0 = response.getBody();
		
		dataMap2.put("Senha", senha);
		dataMap2.put("Descricao", descricao);
		dataMap2.put("Email", email);
		dataMap2.put("Cor", cor);
		dataMap1.put(restaurante, dataMap2);
		dataMap0.put("Restaurantes", dataMap1);
		
		firebase.put(dataMap0);
	}
	
	public static void fazerLogin(String restaurante, String nome, String senha) throws UnsupportedEncodingException, FirebaseException {
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Restaurantes");
		
		if(dataMap1.containsKey(nome) == false)
			return;
		
		dataMap2 = (Map<String, Object>) dataMap1.get(nome);

		if(dataMap2.get("Senha").equals(senha) == false)
			return;
		
		return;
	}
}
