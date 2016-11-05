package br.com.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import br.com.quartz.utils.Utils;
import br.com.quartz.jobs.params.Params;
import br.com.quartz.model.JobDetails;
import br.com.quartz.model.TriggerDetails;

public class TriggerImplement implements TriggerInterface {

	private Utils utils;
	private Scheduler scheduler;

	public TriggerImplement(Utils utils) throws Exception {
		this.scheduler = new StdSchedulerFactory().getScheduler();
		this.utils = utils;
	}

    /**
    * Método responsável por criar uma trigger para um determinado job.
    *         
    * @triggerDetails recebe o objeto com os atributos necessários.
    * 
    */
	public void criaTrigger(TriggerDetails triggerDetails) {
		try {
			String classTrigger = triggerDetails.getTriggerName().substring(0, 1).toUpperCase()
					.concat(triggerDetails.getTriggerName().substring(1));
			
			String params = triggerDetails.getParamJson();
			
			// Transforma o json em objeto
			Class<?> classParams = Class.forName("br.com.quartz.jobs.params." + classTrigger + "Params");
			Object parametersClass = utils.transformaJsonEmObjeto(classParams, params);

			// Cria job key
			JobKey jobKey = JobKey.jobKey(triggerDetails.getJobName(), triggerDetails.getJobGroup());

			// Realiza o agendamento da trigger para o Job.
			Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerDetails.getTriggerName(), triggerDetails.getTriggerGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(((Params) parametersClass).getPeriodicidade()))
                    .withDescription(triggerDetails.getTriggerDescription())
                    .forJob(scheduler.getJobDetail(jobKey))
                    .build();
			
			trigger.getJobDataMap().put("json", params);

			scheduler.scheduleJob(trigger);

			System.out.println("--------------- Trigger criada ---------------");
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}

    /**
    * Método responsável por executar uma trigger imediatamente
    * independente da sua periodicidade.
    * 
    * @params são os parâmetros informados pelo usuário para execução
    *         da trigger.
    *         
    * @classTrigger o nome da classe.
    * 
    */
	@Override
	public void executaTrigger(String params, String classTrigger) {
		try {
			// identifica a class do job que será executado/criado.
			Class<?> classParams = Class.forName("br.com.quartz.jobs.params." + classTrigger + "Params");

			// Transforma o json em objeto
			Object parametersClass = utils.transformaJsonEmObjeto(classParams, params);

			// verifica se os parametros obrigatórios estao no json
			utils.verificaParamTrigger(parametersClass);

			// cria job key
			JobKey jobKey = JobKey.jobKey(((Params) parametersClass).getJobNome(),
					((Params) parametersClass).getJobGroup());

			// cria trigger key
			TriggerKey triggerKey = TriggerKey.triggerKey(((Params) parametersClass).getTriggerNome(),
					((Params) parametersClass).getTriggerGroup());

			// cria trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withDescription(((Params) parametersClass).getTriggerDescription())
                    .forJob(jobKey)
                    .build();
            
            trigger.getJobDataMap().put("json", params);

			scheduler.scheduleJob(trigger);
			System.out.println("--------------- O job " + jobKey.getName() + " foi executado com sucesso. --------------- ");

		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}

    /**
    * Método responsável por deletar uma trigger.
    * 
    * @nomeTrigger nome da trigger que será removida.
    * 
    * @groupTrigger grupo da trigger que será removida.
    * 
    */
	@Override
	public void deletaTrigger(TriggerDetails trigger) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(trigger.getTriggerName(), trigger.getTriggerGroup());
			scheduler.unscheduleJob(triggerKey);
			System.out.println("--------------- Trigger deletada ---------------");
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}

   /**
    * Método responsável por modificar uma trigger.
    * 
    * @params parâmetros necessários para realizar a alteração da trigger.
    * 
    * @classTrigger o nome da classe.
    * 
    */
	@Override
	public void editaTrigger(String params, String classTrigger) {
		try {

			// Identifica a class da trigger que será alterada.
			Class<?> classParams = Class.forName("br.com.quartz.jobs.params." + classTrigger + "Params");

			// Transforma o json em objeto
			Object paramsTrigger = utils.transformaJsonEmObjeto(classParams, params);

			Trigger oldTrigger = scheduler.getTrigger(TriggerKey.triggerKey(((Params) paramsTrigger).getTriggerNome(),
					((Params) paramsTrigger).getTriggerGroup()));

			TriggerBuilder tb = oldTrigger.getTriggerBuilder();

			Trigger newTrigger = tb.newTrigger()
					.withIdentity(((Params) paramsTrigger).getTriggerNome(), ((Params) paramsTrigger).getTriggerGroup())
					.withSchedule(CronScheduleBuilder.cronSchedule(((Params) paramsTrigger).getPeriodicidade()))
					.withDescription(params).build();

			scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);

			System.err.println("--------------- Trigger modificada ---------------");
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}

    /**
     * Método responsável por iniciar uma trigger que estava em pause/parada.
     * 
     * @params parâmetros necessários para poder iniciar a trigger.
     * 
     */
	@Override
	public void startTrigger(String params) {
		try {

			Object paramsTrigger = utils.transformaJsonEmObjeto(Params.class, params);

			TriggerKey triggerKey = TriggerKey.triggerKey(((Params) paramsTrigger).getTriggerNome(),
					((Params) paramsTrigger).getTriggerGroup());

			scheduler.resumeTrigger(triggerKey);

			System.out.println("--------------- Trigger iniciada ---------------");
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}

    /**
     * Método responsável por pausar/parar uma trigger agendada.
     * 
     * @params parâmetros necessários para poder pausar/parar a trigger.
     * 
     */
	@Override
	public void stopTrigger(String params) {
		try {

			Object paramsTrigger = utils.transformaJsonEmObjeto(Params.class, params);

			TriggerKey triggerKey = TriggerKey.triggerKey(((Params) paramsTrigger).getTriggerNome(),
					((Params) paramsTrigger).getTriggerGroup());

			scheduler.pauseTrigger(triggerKey);

			System.out.println("--------------- Trigger parada ---------------");
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
	}
	
    /**
     * Método responsável por listar as triggers de um determinado job.
     * 
     * @return retorna uma lista de triggers cadastradas.
     */    
	@Override
	public List<TriggerDetails> listaTriggers() {
		List<TriggerDetails> list = new ArrayList<>();
		try {
			for (String group : scheduler.getTriggerGroupNames()) {
				for (TriggerKey triggerKey : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group))) {
					TriggerDetails trigger = new TriggerDetails();
					
					trigger.setTriggerName(triggerKey.getName());
					trigger.setTriggerGroup(triggerKey.getGroup());
					
					list.add(trigger);
				}
			}
			System.out.println(list);
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}
		return list;
	}	
}