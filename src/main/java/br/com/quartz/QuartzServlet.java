package br.com.quartz;

import java.io.IOException;
import java.util.List;

import br.com.quartz.repository.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuartzServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ConnectionFactory connectionFactory = new ConnectionFactory();

	public QuartzServlet() {
		super();

		try {
			System.out.println("Executando Jobs");
			
			for (JobBd j : verificaJobs()) {
				SchedulerJob.executarAgenda(j.getClasse(), "job" + j.getNome(), "trigger" + j.getNome(), j.getTempo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<JobBd> verificaJobs() {
		List<JobBd> listaJobs = null;
		
		try {
			EntityManager manager = connectionFactory.getConnection();

			Query query = manager.createQuery("select j from JobBd as j where j.ativa = 'S'");
			listaJobs = query.getResultList(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaJobs;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}