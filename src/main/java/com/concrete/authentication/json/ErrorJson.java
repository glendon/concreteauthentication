package com.concrete.authentication.json;

public class ErrorJson {
	
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ErrorJson(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public ErrorJson() {
		super();
	}		
	

}
