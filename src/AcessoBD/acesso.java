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
	
	public acesso() throws FirebaseException {
		firebase = new Firebase("https://projetoteste-9d563.firebaseio.com/");
	}
	
	public static void criaMenu(String restaurante) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		dataMap1 = new LinkedHashMap<String, Object>();
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
		dataMap1.put( Integer.toString((Integer)dataMap1.get("Tamanho")), dataMap2);
		dataMap1.put("Tamanho", (Integer) dataMap1.get("Tamanho") + 1);
		dataMap0.put("Lojas", dataMap1);
	
		firebase.put(dataMap0);
	}
	
	public static void insereItem(String restaurante, String nome, String descricao, double preco) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		String verificacao_loja = null, verificacao_item;
		int i = 0;
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();

		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Lojas");

		System.out.println((Integer) dataMap1.get("Tamanho"));
			
		while(i != (Integer) dataMap1.get("Tamanho")){
			verificacao_loja = Integer.toString(i);
			dataMap2 = (Map<String, Object>) dataMap1.get(verificacao_loja);
			i++;
			if( dataMap2.get("Nome").equals(restaurante) == true) {
				break;
			}
		}
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		verificacao_item =  Integer.toString((Integer) dataMap3.get("Tamanho"));
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
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		i = 0;
		while(i != (Integer) dataMap3.get("Tamanho")){
			verificacao_item = Integer.toString(i);
			dataMap4 = (Map<String, Object>) dataMap3.get(verificacao_item);
			if(dataMap4.get("Nome").equals(nome) == true)
				break;
			i++;
		}

		dataMap4.put("Descricao", descricao);
		dataMap4.put("Preco", preco);
		
		dataMap3.put(Integer.toString(i), dataMap4);
		dataMap2.put("Cardapio", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		
		firebase.put(dataMap0);
		
	}
	
	public static void removeItem(String restaurante, String nome) throws UnsupportedEncodingException, FirebaseException, JacksonUtilityException {
		String verificacao_loja = null, verificacao_item = null;
		int i = 0, int_aux;
		Map map_aux = new LinkedHashMap<String, Object>();
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		
		dataMap3 = (Map<String, Object>) dataMap2.get("Cardapio");
		
		i = 0;
		while(i != (Integer) dataMap3.get("Tamanho")){
			verificacao_item = Integer.toString(i);
			dataMap4 = (Map<String, Object>) dataMap3.get(verificacao_item);
			if(dataMap4.get("Nome").equals(nome) == true)
				break;
			i++;
		}
		
		for(int j = i; j < (Integer) dataMap3.get("Tamanho") - 1; j++) {	
			int_aux = j + 1;
			verificacao_item = Integer.toString(int_aux);
			dataMap3.put(Integer.toString(j), dataMap3.get(verificacao_item));
			dataMap3.remove(verificacao_item);
		}
		int_aux = (Integer) dataMap3.get("Tamanho");
		int_aux--;
		
		dataMap3.put("Tamanho", int_aux);
		dataMap3.remove(Integer.toString((Integer)dataMap3.get("Tamanho")));

		dataMap2.put("Cardapio", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		
		firebase.put(dataMap0);
	}
	
	public static void criarCadastro(String restaurante,String senha,String descricao,String email, String url_img, String cor) throws UnsupportedEncodingException, JacksonUtilityException, FirebaseException {
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
		response = firebase.get();
		dataMap0 = response.getBody();
		
		if(dataMap0.containsKey("Restaurantes") == true) 
			dataMap1 = (Map<String, Object>) dataMap0.get("Restaurantes");
		
		dataMap2.put("Senha", senha);
		dataMap2.put("Descricao", descricao);
		dataMap2.put("Email", email);
		dataMap2.put("Cor", cor);
		dataMap2.put("URL", url_img);
		dataMap1.put(restaurante, dataMap2);
		dataMap0.put("Restaurantes", dataMap1);
		
		firebase.put(dataMap0);	
	
	}
	
	public static int fazerLogin(String restaurante, String senha) throws UnsupportedEncodingException, FirebaseException {
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		response = firebase.get();
		dataMap0 = response.getBody();
		dataMap1 = (Map<String, Object>) dataMap0.get("Restaurantes");
		
		if(dataMap1.containsKey(restaurante) == false)
			return 1;
		
		dataMap2 = (Map<String, Object>) dataMap1.get(restaurante);

		if(dataMap2.get("Senha").equals(senha) == false)
			return 2;
		
		return 0;
	}
	
	public static Map<String, Object> retornarMenu(String restaurante) throws UnsupportedEncodingException, FirebaseException{
		int i = 0;
		String verificacao_loja;
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		
		return (Map <String, Object>)dataMap2.get("Cardapio");
	}
	
	public static Map<String, Object> retornarPedidos(String restaurante) throws UnsupportedEncodingException, FirebaseException{
		int i = 0;
		String verificacao_loja;
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		
		
		return dataMap3;
	}
	
	public static void pedidoPronto(String restaurante, String pedido) throws UnsupportedEncodingException, FirebaseException {
		int i = 0;
		String verificacao_loja = null;
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		dataMap4 = (Map<String, Object>) dataMap3.get(pedido);
		dataMap4.put("Pronto", 1);
		
		dataMap3.put(pedido, dataMap4);
		dataMap2.put("Pedidos", dataMap3);
		dataMap1.put(verificacao_loja, dataMap2);
		dataMap0.put("Lojas", dataMap1);
		
	}
	
	public static void pedidoRetirado(String restaurante, String pedido) throws UnsupportedEncodingException, FirebaseException {
		int i = 0;
		String verificacao_loja;
		dataMap1 = new LinkedHashMap<String, Object>();
		dataMap2 =  new LinkedHashMap<String, Object>();
		dataMap3 =  new LinkedHashMap<String, Object>();
		dataMap4 =  new LinkedHashMap<String, Object>();
		
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
		dataMap4.remove(pedido);
	}
}
