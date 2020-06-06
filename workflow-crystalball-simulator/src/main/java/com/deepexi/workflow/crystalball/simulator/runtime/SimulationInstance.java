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
package com.deepexi.workflow.crystalball.simulator.runtime;

import org.activiti.engine.repository.ProcessDefinition;


/** Represents one execution of a  {@link ProcessDefinition}.
 * 
 * @author Tom Baeyens
 * @author Joram Barrez
 * @author Daniel Meyer
 */
public interface SimulationInstance {
	  /**
	   * The unique identifier of the simulationInstance.
	   */
	  String getId();
	  
	  /**
	   * return simulation instance business key (Name) 
	   * @return
	   */
	  String getName();
	  
	  /**
	   * Indicates if the execution is suspended.
	   */
	  boolean isSuspended();
	  
	  /**
	   * Indicates if the execution is ended.
	   */
	  boolean isEnded();
	  
	  /**
	   * simulation configuration 
	   */ 
	  String getSimulationConfigurationId();
	  
	  /**
	   * get count for replications
	   * @return
	   */
	  int getReplication();  
}
