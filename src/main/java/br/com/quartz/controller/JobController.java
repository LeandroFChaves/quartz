package br.com.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.quartz.JobImplement;
import br.com.quartz.model.JobDetails;
import br.com.quartz.utils.Utils;

@Controller
public class JobController {
	
	private JobImplement jobImplement;
	
	public JobController() throws Exception{
		try {
			jobImplement = new JobImplement(new Utils());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("newJob")
	public String form() {
		return "job/criarJob";
	}

	@RequestMapping("saveJob")
	public String saveJob(JobDetails jobDetails) throws Exception {
		jobImplement.criaEditaJob(jobDetails);

		return "redirect:listJobs";
	}
	
	@RequestMapping("listJobs")
	public String listarJobs(Model model) throws Exception {
		model.addAttribute("jobs", jobImplement.listaJobs());

		return "job/listarJobs";		
	}
	
	@RequestMapping("removeJob")
	public String removeJob(JobDetails job) throws Exception {
		jobImplement.deleteJob(job);
		
		return "redirect:listJobs";		
	}
}