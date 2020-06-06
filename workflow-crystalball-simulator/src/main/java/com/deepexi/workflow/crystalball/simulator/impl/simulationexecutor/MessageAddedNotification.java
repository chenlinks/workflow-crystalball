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
package com.deepexi.workflow.crystalball.simulator.impl.simulationexecutor;

import org.activiti.crystalball.simulator.impl.cfg.TransactionListener;
import org.activiti.crystalball.simulator.impl.interceptor.CommandContext;

import java.util.logging.Logger;

/**
 * @author Tom Baeyens
 */
public class MessageAddedNotification implements TransactionListener {
  
  private static Logger log = Logger.getLogger(MessageAddedNotification.class.getName());
  
  protected JobExecutor jobExecutor;
  
  public MessageAddedNotification(JobExecutor jobExecutor) {
    this.jobExecutor = jobExecutor;
  }

  public void execute(CommandContext commandContext) {
    log.fine("notifying job executor of new job");
    jobExecutor.jobWasAdded();
  }
}
