package br.com.quartz.utils;

import javax.inject.Named;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.quartz.jobs.params.Params;
import br.com.quartz.model.JobDetails;

@Named
public class Utils {

    public Utils() {}
    
    /**
     * Método responsável em verificar todos os parametros obrigatórios para a
     * criação de um job.
     * 
     * @param parametersClass Objeto (Params).
     * @throws StatusException
     */
     public void verificaParamJob(JobDetails jobDetails) throws Exception {
         try {

            if (jobDetails.getJobName() == null || jobDetails.getJobName() == "") {
            	System.out.println("Erro " + " O nome do job é obrigatório.");
                throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O nome do job é obrigatório.");
            }

            if (jobDetails.getJobGroup() == null ||jobDetails.getJobGroup() == "") {
            	System.out.println("Erro " + " O grupo do job é obrigatório.");
                throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O grupo do job é obrigatório.");
            }

        } catch (Exception e) {
        	System.out.println("Erro " + e.getMessage());
            throw e;
        }
     }
     
     /**
      * Método responsável em verificar todos os parametros obrigatórios para a
      * criação de uma trigger.
      * 
      * @param parametersClass Objeto (Params).
      * @throws StatusException
      */
      public void verificaParamTrigger(Object parametersClass) throws Exception {
          try {

             if (((Params) parametersClass).getTriggerNome() == null){
            	 System.out.println("Erro " + " O nome da trigger é obrigatório.");
                 throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O nome da trigger é obrigatório.");
             }

             if (((Params) parametersClass).getTriggerGroup() == null) {
            	 System.out.println("Erro " + " O grupo da trigger é obrigatório.");
                 throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " O grupo da trigger é obrigatório.");
             }
     
             if (((Params) parametersClass).getPeriodicidade() == null ) {
            	 System.out.println("Erro " + " A periodicidade é obrigatória.");
                 throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " A periodicidade é obrigatória.");
             }

         } catch (Exception e) {
        	 System.out.println("Erro " + e.getMessage());
             throw e;
         }
      }

     /**
     * Método responsável em transformar o params (Json) em Param (Objeto).
     * 
     * @param classParams A Classe (Params) responsavel pelos parametros obrigatórios na criação de um novo job/trigger
     * @param params Os parametros que vem do front-end para pode transformar esses parametros em uma Objeto java
     * @return o Objeto (Params) instanciado.
     * @throws Exception
     */
     public Object transformaJsonEmObjeto(Class<?> classParams, String params) throws Exception {
        try {

            if (params.isEmpty() || params.startsWith("null")) {
            	System.out.println("Erro " + "Os Parametros são obrigatórios e não podem ser null");
                throw new StatusException(Status.BAD_REQUEST.getStatusCode(), " Os Parametros são obrigatórios e não podem ser null");
            }
            
            ObjectMapper mapper = new ObjectMapper();
            Object parametersClass = classParams.newInstance();
            parametersClass = mapper.readValue(params, classParams);
            return parametersClass;

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
            throw e;
        }
     }
}