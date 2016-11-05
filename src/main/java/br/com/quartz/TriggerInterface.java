package br.com.quartz;

import java.util.List;

import br.com.quartz.model.TriggerDetails;

public interface TriggerInterface {
	
    void criaTrigger(TriggerDetails triggerDetails);

    void executaTrigger(String params, String classTrigger);

    void editaTrigger(String params, String classTrigger);
    
    void deletaTrigger(TriggerDetails trigger);

    void startTrigger(String params);
    
    void stopTrigger(String params); 
    
    List<TriggerDetails> listaTriggers();

}