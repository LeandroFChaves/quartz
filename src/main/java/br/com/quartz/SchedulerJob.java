package br.com.quartz;

import java.lang.reflect.InvocationTargetException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Esta é a classe que deve ser invocada e irá disparar a tarefa nos momentos
 * determinados
 *
 */
public class SchedulerJob {
	
	private static Scheduler scheduler;

	public static void executarAgenda(String classe, String paramJob, String paramTrigger, String paramTempo)
			throws ClassNotFoundException {

		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			
			/*
			 * Reflections reflections = new Reflections("br.com.email", new
			 * SubTypesScanner(false), ClasspathHelper.forClassLoader());
			 * 
			 * Set<Class<?>> allClasses =
			 * reflections.getSubTypesOf(Object.class);
			 * 
			 * Map<String, Class<?>> classBySimpleName = new HashMap<>();
			 * 
			 * for(Class<?> c : allClasses) {
			 * classBySimpleName.put(c.getSimpleName(), c); }
			 * 
			 * Class jobClass = classBySimpleName.get(classe);
			 * jobClass.getConstructor(String.class).newInstance("listaparams");
			 */

			// Se a classe estiver em outro pacote dentro de um .jar informar a raiz do pacote, conforme exemplo.
			Class<?> jobClass = Class.forName("br.com.quartz.jobs." + classe);
			jobClass.getConstructor(String.class, String.class, String.class, String.class, String.class, String.class).
					newInstance("usernamEmail", "password", "remetente", "destinatario", "assunto", "conteudo");
			
			newJob(jobClass, paramJob, paramTrigger, "grupo1", ("0/" + paramTempo + " * * * * ?"));

		} catch (SchedulerException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Error creating scheduler " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void newJob(Class<?> pJob, String pNomeJob, String pNomeTrigger, String pGrupo, String pPeriodicidade) throws SchedulerException {
		JobDetail job = null;
		
		// Cria o job que será executado.
		JobKey jobKey = JobKey.jobKey(pNomeJob, pGrupo);
		if (scheduler.checkExists(jobKey)) {
			scheduler.deleteJob(jobKey);
		}
		
		// storeDurably(true) - Grava o job na tabela mesmo sem trigger vinculada.
		// requestRecovery(true) - Instrui o Scheduler a voltar a ser executado se uma "recuperação" ou situação "failover" é encontrado.
		job = JobBuilder.newJob().
				ofType((Class<? extends Job>) pJob).
				storeDurably(true).
				requestRecovery(true).
				withIdentity(pNomeJob, pGrupo).build();
		
		newTrigger(job, pNomeTrigger, pGrupo, pPeriodicidade);
	}
	
	public static void newTrigger(JobDetail pJob, String pNomeTrigger, String pGrupo, String pPeriodicidade) throws SchedulerException{
		Trigger trigger = null;
		
		if (pPeriodicidade.isEmpty()) {
			// Executa a trigger somente uma vez (Não realiza agendamento)
			trigger = TriggerBuilder.newTrigger().
						withIdentity(pNomeTrigger, pGrupo).
						startNow().
						build();
		} else {
			// Realiza o agendamento da trigger para o Job.
			trigger = TriggerBuilder.newTrigger().
									withIdentity(pNomeTrigger, pGrupo).
									withSchedule(CronScheduleBuilder.cronSchedule(pPeriodicidade)).
									build();
		}

		scheduler.start();
		scheduler.scheduleJob(pJob, trigger);
	}
	
	public static void deleteTrigger(String pNomeTrigger, String pGrupo) throws SchedulerException{
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		TriggerKey triggerKey = TriggerKey.triggerKey(pNomeTrigger, pGrupo); 
		scheduler.unscheduleJob(triggerKey);
	}
	
	public static void updateTrigger(String pNomeTrigger, String pGrupo, String pCronExpression) throws SchedulerException{
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
	
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(pCronExpression);
		CronTrigger newCronTrigger = TriggerBuilder.newTrigger().withIdentity(pNomeTrigger).withSchedule(cronScheduleBuilder).build();

		scheduler.rescheduleJob(TriggerKey.triggerKey(pNomeTrigger, pGrupo), newCronTrigger);
	}	
}