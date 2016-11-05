package br.com.quartz.jobs;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.UnableToInterruptJobException;

import br.com.quartz.email.Authentication;
import br.com.quartz.utils.ConnectionFactory;
import br.com.quartz.utils.StatusException;
import br.com.quartz.jobs.params.EnviaEmailParams;
import br.com.quartz.model.JobDetails;

// @DisallowConcurrentExecution - Desabilita a execução simultânea de um job. 
public class EnviaEmail implements Job, InterruptableJob {

	public ObjectMapper mapper;
	ConnectionFactory connectionFactory = new ConnectionFactory();

	public EnviaEmail() {}

	@Override
	public void execute(JobExecutionContext context) {

		try {
			this.mapper = new ObjectMapper();
			EnviaEmailParams enviaEmailParams = mapper.readValue(context.getTrigger().getJobDataMap().getString("json"), EnviaEmailParams.class);

			verificaParametrosObrigatorios(enviaEmailParams);

			//seta todas as propriedades para configuração de envio de email(gmail).
			Properties props = setProps();

			//autenticação de email.
			Authentication authentication = new Authentication();
			Authenticator auth = authentication.autenticaEmail(enviaEmailParams.getUsername(), enviaEmailParams.getPassword());

	        MimeMessage message = new MimeMessage(Session.getInstance(props, auth));
	        message.setFrom(new InternetAddress(enviaEmailParams.getUsername()));//from
	        message.setSubject(enviaEmailParams.getTitle()); //title
	        message.setText(enviaEmailParams.getMsg()); //text
	        
	        for (String destinatario : enviaEmailParams.getTo()) {
	        	message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			}
	        
	        Transport.send(message);
	        
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
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			return props;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public JobDetails verificaParametros() {
		Query query = null;

		try {
			EntityManager manager = connectionFactory.getConnection();

			query = manager.createQuery("select j from JobBd as j where j.id = 1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (JobDetails) query.getSingleResult();
	}

	private void verificaParametrosObrigatorios(EnviaEmailParams enviaEmailParams) throws Exception {
		try {
			if (enviaEmailParams.getUsername().equals(null) || enviaEmailParams.getUsername().equals("")) {
				System.out.println(" O usuário é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O usuário é obrigatório.");
			}

			if (enviaEmailParams.getPassword().equals(null) || enviaEmailParams.getPassword().equals("")) {
				System.out.println("A senha é obrigatória.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " A senha é obrigatória.");
			}

			if (enviaEmailParams.getTo().equals(null) || enviaEmailParams.getTo().equals("")) {
				System.out.println(" O destinatário é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O destinatário é obrigatório.");
			}

			if (enviaEmailParams.getTitle().equals(null) || enviaEmailParams.getTitle().equals("")) {
				System.out.println(" O titulo é obrigatório.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O titulo é obrigatório.");
			}

			if (enviaEmailParams.getMsg().equals(null) || enviaEmailParams.getMsg().equals("")) {
				System.out.println(" A menssagem é obrigatória.");
				throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " A menssagem é obrigatória.");
			}
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
			throw e;
		}
	}

	public void interrupt() throws UnableToInterruptJobException {
		System.out.println("Job Envio de Email interrompido.");
	}
}
