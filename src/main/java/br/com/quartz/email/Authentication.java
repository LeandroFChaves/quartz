package br.com.quartz.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Authentication {
	
	public Authenticator autenticaEmail(final String username, final String password) {
	    Authenticator autenticaEmail = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    };
	    return autenticaEmail;
	}
}
