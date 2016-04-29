package org.springframework.batch.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.NoSuchStepException;
import org.springframework.beans.factory.DisposableBean;

public class MarkLogicJobService implements JobService, DisposableBean {
	
	//private static final Log logger = LogFactory.getLog(MarkLogicJobService.class);
	
	private JobLauncher jobLauncher;
	private ListableJobLocator jobLocator;
	private JobRepository jobRepository;
	private JobExplorer jobExplorer;
	
	private Collection<JobExecution> activeExecutions = Collections.synchronizedList(new ArrayList<JobExecution>());
	
	public MarkLogicJobService() {
	}
	
	public MarkLogicJobService(JobRepository jobRepository, JobLauncher jobLauncher,
			ListableJobLocator jobLocator, JobExplorer jobExplorer) {
		this.jobLocator = jobLocator;
		this.jobRepository = jobRepository;
		this.jobLauncher = jobLauncher;
		this.jobExplorer = jobExplorer;
	}

	@Override
	public boolean isLaunchable(String jobName) {
		return jobLocator.getJobNames().contains(jobName);
	}

	@Override
	public JobExecution launch(String jobName, JobParameters params)
			throws NoSuchJobException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Job job = jobLocator.getJob(jobName);

		JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, params);
		boolean restart = false;
		if (lastJobExecution != null) {
			BatchStatus status = lastJobExecution.getStatus();
			if (status.isUnsuccessful() && status!=BatchStatus.ABANDONED) {
				restart = true;
			}
		}

		if (job.getJobParametersIncrementer() != null && !restart) {
			params = job.getJobParametersIncrementer().getNext(params);
		}

		JobExecution jobExecution = jobLauncher.run(job, params);

		if (jobExecution.isRunning()) {
			activeExecutions.add(jobExecution);
		}
		return jobExecution;
	}

	@Override
	public JobParameters getLastJobParameters(String jobName) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobExecution restart(Long jobExecutionId)
			throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, NoSuchJobException, JobParametersInvalidException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobExecution stop(Long jobExecutionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobExecution abandon(Long jobExecutionId)
			throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> listJobs(int start, int count) {
		Collection<String> jobNames = new LinkedHashSet<String>(jobLocator.getJobNames());
		if (start + count > jobNames.size()) {
			jobNames.addAll(jobExplorer.getJobNames());
		}
		if (start >= jobNames.size()) {
			start = jobNames.size();
		}
		if (start + count >= jobNames.size()) {
			count = jobNames.size() - start;
		}
		return new ArrayList<String>(jobNames).subList(start, start + count);
	}

	@Override
	public int countJobs() {
		return jobExplorer.getJobNames().size();
	}

	@Override
	public JobInstance getJobInstance(long jobInstanceId) throws NoSuchJobInstanceException {
		return jobExplorer.getJobInstance(jobInstanceId);
	}

	@Override
	public Collection<JobInstance> listJobInstances(String jobName, int start, int count) throws NoSuchJobException {
		return jobExplorer.getJobInstances(jobName, start, count);
	}

	@Override
	public int countJobInstances(String jobName) throws NoSuchJobException {
		return 0;
	}

	@Override
	public Collection<JobExecution> listJobExecutionsForJob(String jobName, int start, int count)
			throws NoSuchJobException {
		List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, start, count);
		List<JobExecution> jobExecutions = new ArrayList<JobExecution>();
		for ( JobInstance jobInstance : jobInstances ) {
			for ( JobExecution jobExecution : jobExplorer.getJobExecutions(jobInstance)) {
				jobExecutions.add(jobExecution);
			}
		}
		return jobExecutions;
	}

	@Override
	public int countJobExecutionsForJob(String jobName) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<JobExecution> getJobExecutionsForJobInstance(String jobName, Long jobInstanceId)
			throws NoSuchJobException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JobExecution> listJobExecutions(int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countJobExecutions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JobExecution getJobExecution(Long jobExecutionId) throws NoSuchJobExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<StepExecution> getStepExecutions(Long jobExecutionId) throws NoSuchJobExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<StepExecution> listStepExecutionsForStep(String jobName, String stepName, int start, int count)
			throws NoSuchStepException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countStepExecutionsForStep(String jobName, String stepName) throws NoSuchStepException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StepExecution getStepExecution(Long jobExecutionId, Long stepExecutionId)
			throws NoSuchStepExecutionException, NoSuchJobExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int stopAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isIncrementable(String jobName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<String> getStepNamesForJob(String jobName) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
