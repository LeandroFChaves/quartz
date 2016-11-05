package br.com.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.quartz.TriggerImplement;
import br.com.quartz.model.TriggerDetails;
import br.com.quartz.utils.Utils;

@Controller
public class TriggerController {

	private TriggerImplement triggerImplement;
	
	public TriggerController() throws Exception{
		try {
			triggerImplement = new TriggerImplement(new Utils());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("newTrigger")
	public String form() {
		return "trigger/criarTrigger";
	}

	@RequestMapping("saveTrigger")
	public String saveJob(TriggerDetails triggerDetails) throws Exception {
		triggerImplement.criaTrigger(triggerDetails);

		return "redirect:listTriggers";
	}
	
	@RequestMapping("listTriggers")
	public String listarTriggers(Model model) throws Exception {
		model.addAttribute("triggers", triggerImplement.listaTriggers());

		return "trigger/listarTriggers";		
	}
	
	@RequestMapping("removeTrigger")
	public String removeTrigger(TriggerDetails trigger) throws Exception {
		triggerImplement.deletaTrigger(trigger);
		
		return "redirect:listTriggers";		
	}
}