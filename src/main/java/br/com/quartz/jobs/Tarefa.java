package br.com.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 * Esta classe demonstra uma tarefa que ira ser invocada pelo Quartz Tem que
 * implementar a interface Job do Quartz
 * 
 */
public class Tarefa implements Job {
	/**
	 * Metodo que é executado quando a tarefa é invocada
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy – hh:mm:ss");
		System.out.println("Rodou - " + dateFormat.format(new Date()));
	}
}