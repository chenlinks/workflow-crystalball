package com.deepexi.workflow.crystalball.simulator.delegate;

/*
 * #%L
 * simulator
 * %%
 * Copyright (C) 2012 - 2013 crystalball
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.util.xml.Element;

public abstract class AbstractSimulationActivityBehavior implements ActivityBehavior {

	  /**
	   * The namespace of the CrystalBall custom BPMN extensions.
	   */
	  public static final String CRYSTALBALL_BPMN_EXTENSIONS_NS = "http://crystalball.org/simulation";
	  
	public AbstractSimulationActivityBehavior(Element scriptTaskElement, ScopeImpl scope, ActivityImpl activity) {
		
	}
	
	abstract public void execute(ActivityExecution execution) throws Exception;
}
