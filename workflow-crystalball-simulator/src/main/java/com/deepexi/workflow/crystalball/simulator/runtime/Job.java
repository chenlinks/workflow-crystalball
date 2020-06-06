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

import org.activiti.engine.ManagementService;

import java.util.Date;


/**
 * Represents one job (simulation, etc.).
 * 
 * @author Joram Barrez
 */
public interface Job {
  
  /**
   * Returns the unique identifier for this job.
   */
  String getId();
  
  /**
   * Returns the date on which this job is supposed to be processed.
   */
  Date getDuedate();
  
  
  /**
   * Returns the specific simulation instance on which the job was created. 
   */
  String getSimulationInstanceId();
  
  /**
   * Returns the specific process definition on which the job was created
   */
//  String getProcessDefinitionId();
  
  /**
   * Returns the number of retries this job has left. 
   * Whenever the jobexecutor fails to execute the job, this value is decremented. 
   * When it hits zero, the job is supposed to be dead and not retried again 
   * (ie a manual retry is required then).
   */
  int getRetries();
  
  /**
   * Returns the message of the exception that occurred, the last time the job was
   * executed. Returns null when no exception occurred.
   * 
   * To get the full exception stacktrace, 
   * use {@link ManagementService#getJobExceptionStacktrace(String)}
   */
  String getExceptionMessage();

}
