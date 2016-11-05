package br.com.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import br.com.quartz.model.JobDetails;
import br.com.quartz.utils.Utils;

public class JobImplement implements JobInterface {
	
	private Utils utils;
    private Scheduler scheduler;
	
    public JobImplement(Utils utils) throws Exception {
        this.scheduler = new StdSchedulerFactory().getScheduler();
        this.utils = utils;
    }

    /**
    * Método responsável por criar e editar um job.
    * 
    * @jobDetails recebe o objeto com os atributos necessários.
    * 
    */
    public void criaEditaJob(JobDetails jobDetails) {
        try {
            
            // Identifica a class do job que será executado/criado.
            Class<?> classJob = Class.forName("br.com.quartz.jobs." + jobDetails.getJobName().substring(0, 1).toUpperCase()
					.concat(jobDetails.getJobName().substring(1)));
            
            // Verifica se os parametros obrigatórios estao preenchidos
            utils.verificaParamJob(jobDetails);

            // Cria job key
			JobKey jobKey = JobKey.jobKey(jobDetails.getJobName(), jobDetails.getJobGroup());

            // Cria o job
            JobDetail job = JobBuilder.newJob((Class<? extends Job>) classJob)
                    .requestRecovery(true).storeDurably(true)
                    .withIdentity(jobKey)
                    .withDescription(jobDetails.getDescription())
                    .build();

            scheduler.addJob(job, true);
            
            System.out.println("--------------- Job " + job.getKey().getName() + " foi criado com sucesso ---------------");

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    
    /**
     * Método responsável por listar os jobs existentes na base.
     * 
     * @return retorna uma lista de jobs cadastrados.
     */    
	@Override
	public List<JobDetails> listaJobs() {
		List<JobDetails> list = new ArrayList<>();
		try {
			for (String group : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group))) {
					JobDetails job = new JobDetails();
					
					job.setJobName(jobKey.getName());
					job.setJobGroup(jobKey.getGroup());
					
					list.add(job);
				}
			}
			System.out.println(list);
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
		return list;
	}

    /**
    * Método responsável por deletar um job.
    * 
    * @nomeJob nome do job que será removido.
    * 
    * @grupoJob grupo do job que será removido.
    * 
    */
    @Override
    public void deleteJob(JobDetails job) {
        try {
            scheduler.deleteJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));
            System.out.println("--------------- O job " + job.getJobName() + " foi deletado com sucesso. --------------- ");
        } catch (Exception e) {
        	System.out.println("Erro " + e.getMessage());
        }
    }    
    
    @Override
	public void interruptJob (String nomeJob, String grupoJob) {
		try {
			Thread.sleep(5000);
			System.out.println("Status dos Jobs antes da interrupção - " + scheduler.getCurrentlyExecutingJobs());

			// Interrompe um Job em execução
			JobKey jobKey = JobKey.jobKey(nomeJob, grupoJob);
			scheduler.interrupt(jobKey);

			Thread.sleep(1000);
			System.out.println("Status dos Jobs depois da interrupção - " + scheduler.getCurrentlyExecutingJobs());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}