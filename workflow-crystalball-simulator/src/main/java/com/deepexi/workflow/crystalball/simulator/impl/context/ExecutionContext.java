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

package com.deepexi.workflow.crystalball.simulator.impl.context;

import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;


/**
 * @author Tom Baeyens
 */
public class ExecutionContext {

  protected ExecutionEntity execution;
  
  public ExecutionContext(InterpretableExecution execution) {
    this.execution = (ExecutionEntity) execution;
  }
  
  public ExecutionEntity getExecution() {
    return execution;
  }

  public ExecutionEntity getProcessInstance() {
    return execution.getProcessInstance();
  }

  public ProcessDefinitionEntity getProcessDefinition() {
    return (ProcessDefinitionEntity) execution.getProcessDefinition();
  }

  public DeploymentEntity getDeployment() {
    String deploymentId = getProcessDefinition().getDeploymentId();
    DeploymentEntity deployment = SimulationContext
      .getCommandContext()
      .getDeploymentManager()
      .findDeploymentById(deploymentId);
    return deployment;
  }
}
