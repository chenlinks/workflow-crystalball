/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package com.deepexi.workflow.crystalball.simulator.impl.persistence.entity;

import org.activiti.crystalball.simulator.impl.context.SimulationContext;
import org.activiti.crystalball.simulator.runtime.SimulationInstanceImpl;
import org.activiti.crystalball.simulator.runtime.SuspensionState;
import org.activiti.engine.impl.db.PersistentObject;

import java.util.HashMap;
import java.util.Map;


public class SimulationInstanceEntity extends SimulationInstanceImpl implements	PersistentObject {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	String id;

	public SimulationInstanceEntity() {
		
	}
	
	public String toString() {
		return "SimulationInstanceEntity[" + id + "]";
	}

	// getters and setters
	// //////////////////////////////////////////////////////

	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("suspensionState", this.suspensionState);
		return persistentState;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * check whether simulation instance is still active
	 */
	public void checkActivity() {
		  long count = SimulationContext.getCommandContext().getJobManager().findJobCountBySimulationInstanceId( id);
		  if ( count == 1) {
			  // no other simulation run to execute exists
			  this.setSuspensionState(SuspensionState.FINISHED.getStateCode());
			  SimulationContext.getCommandContext().getSimulationInstanceManager().update(this);
		  }
	}
}
