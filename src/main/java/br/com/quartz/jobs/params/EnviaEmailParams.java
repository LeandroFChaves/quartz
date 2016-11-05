package br.com.quartz.jobs.params;

import java.util.List;

import br.com.quartz.jobs.params.Params;

public class EnviaEmailParams extends Params {
	
	private static final long serialVersionUID = 838086783631037747L;
	
	private String username;
	private String password;
	private List<String> to;
	private String assunto;
	private String msg;
	private String title;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMsg() {
		return msg;
	}

	public void setMensagem(String msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitulo(String title) {
		this.title = title;
	}	
}