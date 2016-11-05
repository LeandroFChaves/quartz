package br.com.quartz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QRTZ_TRIGGERS")
public class TriggerDetails {

	@Id
	@Column(name="trigger_name")
	private String triggerName;

	@Column(name="trigger_group")
	private String triggerGroup;
	
	@Column(name="job_name")
	private String jobName;

	@Column(name="job_group")
	private String jobGroup;	
	
	@Column(name="description")
	private String triggerDescription;
	
	@Transient
	@Column(insertable=false, updatable=false)
	private String paramJson;

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getTriggerDescription() {
		return triggerDescription;
	}

	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}

	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}	
}