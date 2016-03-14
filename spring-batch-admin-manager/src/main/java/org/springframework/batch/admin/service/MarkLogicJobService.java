package org.springframework.batch.admin.service;

import java.util.Collection;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.NoSuchStepException;

public class MarkLogicJobService implements JobService {

	@Override
	public boolean isLaunchable(String jobName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JobExecution launch(String jobName, JobParameters params)
			throws NoSuchJobException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countJobs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JobInstance getJobInstance(long jobInstanceId) throws NoSuchJobInstanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JobInstance> listJobInstances(String jobName, int start, int count) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countJobInstances(String jobName) throws NoSuchJobException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<JobExecution> listJobExecutionsForJob(String jobName, int start, int count)
			throws NoSuchJobException {
		// TODO Auto-generated method stub
		return null;
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

}
