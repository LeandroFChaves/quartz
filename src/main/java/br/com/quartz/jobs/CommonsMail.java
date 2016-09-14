package br.com.quartz.jobs;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.mail.SimpleEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.quartz.JobBd;
import br.com.quartz.email.Authentication;
import br.com.quartz.repository.ConnectionFactory;

// @DisallowConcurrentExecution - Desabilita a execução simultânea de um job. 
public class CommonsMail implements Job {

	private static String username;
	private static String password;
	private static String remetente;
	private static String destinatario;
	private static String assunto;
	private static String mensagem;
	
	ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public CommonsMail(){}
	
	@SuppressWarnings("static-access")	
	public CommonsMail(String username, String password, String remetente, String destinatario, String assunto, String mensagem){
		this.username = username;
		this.password = password;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SimpleEmail email = new SimpleEmail();

		try {
			Authentication authentication = new Authentication();
			Authenticator auth = authentication.auth(username, password);

			Properties props = setProps();
			Session session = Session.getInstance(props, auth);

			email.setMailSession(session);

			email.addTo(destinatario, "Teste E-mail"); // Destinatário
			email.setFrom(remetente, "Remetente"); // Remetente
			email.setSubject(assunto); // Assunto do e-mail
			email.setMsg(mensagem); // Conteúdo

			email.send();

			System.out.println("E-mail enviado com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro no envio do E-mail. Erro - " + e.getMessage());

			e.printStackTrace();
		}
	}

	private static Properties setProps() throws Exception {
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			return props;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	public JobBd verificaParametros() {
		Query query = null;

		try {
			EntityManager manager = connectionFactory.getConnection();

			query = manager.createQuery("select j from JobBd as j where j.id = 1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (JobBd) query.getSingleResult();
	}
}
