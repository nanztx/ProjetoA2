package aplicação;

import java.util.Random;

import DAO.UsuarioDAO;


public class Usuario {
	//Atrubutos
	private int codigo;
	private String nome;
	private String senha;
	
	//Métodos getters setters
	public int getcodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//Métodos da classe
	public void construtor() {
		
	}
	
	public void cadastraUsuario(int codigo) {
			
		this.nome = getNome();
		
	}
	
	public void alteraUsuario(int id) {
		
		
	}
	
	public void excluiUsuario(int id) {
		
		
	}
	
	public static String alteraSenha(int id) {
		
		int idRecebido=0;
		String novaSenha="";
		
		idRecebido = id;
		novaSenha = generateRandomPassword(12);
		
		UsuarioDAO.alteraSenhaDAO(idRecebido,novaSenha);
		
		return novaSenha;
	}
	
	public static String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
          +"jklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
}
