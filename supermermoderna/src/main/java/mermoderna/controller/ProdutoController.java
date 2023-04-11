package mermoderna.controller;

import java.math.BigDecimal;

public class ProdutoController {

	int idProduto;
	String nome;
	BigDecimal valor;
	int idsecao;

	//Metodo construtor da classe
	public ProdutoController(int idProduto, String nome, BigDecimal valor, int idsecao) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.valor = valor;
		this.idsecao = idsecao;
	}

	
	//GET e SET dos atributos
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getIdsecao() {
		return idsecao;
	}

	public void setIdsecao(int idsecao) {
		this.idsecao = idsecao;
	}

	//Metodo ToString da classe
	@Override
	public String toString() {
		return " idProduto= " + idProduto + ", nome= " + nome + ", valor= " + valor + ", id_secao= " + idsecao + "]";
	}

}
