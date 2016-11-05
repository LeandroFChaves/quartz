package br.com.quartz;

import java.util.List;

import br.com.quartz.model.JobDetails;

public interface JobInterface {
	
    void criaEditaJob(JobDetails jobDetails);

    List<JobDetails> listaJobs();

    void deleteJob(JobDetails job);
    
    void interruptJob(String nomeJob, String grupoJob);

}
