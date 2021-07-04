package aplicação;

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
	
	public void alteraSenha(int id) {
		
	}
	
}
