package com.deepexi.workflow.crystalball.simulator.impl.simulationexecutor;

import org.activiti.crystalball.simulator.impl.interceptor.Command;

public interface FailedJobCommandFactory {
	
	public Command<Object> getCommand(String jobId, Throwable exception);

}
