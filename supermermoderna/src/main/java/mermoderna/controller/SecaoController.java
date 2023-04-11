package mermoderna.controller;

public class SecaoController {

	String nome;
	int idSecao;
	
	//Metodo construtor da classe
	public SecaoController(String nome, int idSecao) {
		super();
		this.nome = nome;
		this.idSecao = idSecao;
	}

	//GET e SET dos atributos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdSecao() {
		return idSecao;
	}

	public void setIdSecao(int idSecao) {
		this.idSecao = idSecao;
	}

	//Metodo ToString da classe
	@Override
	public String toString() {
		return "idSecao= " + idSecao + ", Nome= " + nome + "]";
	}
		
}
