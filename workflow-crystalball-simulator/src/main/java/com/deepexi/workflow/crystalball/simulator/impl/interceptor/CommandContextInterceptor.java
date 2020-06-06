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

package com.deepexi.workflow.crystalball.simulator.impl.interceptor;


import org.activiti.crystalball.simulator.impl.cfg.SimulationEngineConfigurationImpl;
import org.activiti.crystalball.simulator.impl.context.SimulationContext;

import java.util.logging.Logger;

/**
 * @author Tom Baeyens
 */
public class CommandContextInterceptor extends CommandInterceptor {
  
  private final Logger log = Logger.getLogger(CommandContextInterceptor.class.getName());

  protected CommandContextFactory commandContextFactory;
  protected SimulationEngineConfigurationImpl simulationEngineConfiguration;
  protected boolean isContextReusePossible; // For backwards compatibility, the default is 'false'

  public CommandContextInterceptor() {
  }

  public CommandContextInterceptor(CommandContextFactory commandContextFactory, SimulationEngineConfigurationImpl processEngineConfiguration) {
    this.commandContextFactory = commandContextFactory;
    this.simulationEngineConfiguration = processEngineConfiguration;
  }

  public <T> T execute(Command<T> command) {
    CommandContext context = SimulationContext.getCommandContext();
    
    boolean contextReused = false;
    // We need to check the exception, because the transaction can be in a rollback state,
    // and some other command is being fired to compensate (eg. decrementing job retries)
    if (!isContextReusePossible || context == null || context.getException() != null) { 
    	context = commandContextFactory.createCommandContext(command);    	
    }  
    else {
    	log.fine("Valid context found. Reusing it for the current comment '" + command.getClass().getCanonicalName() + "'");
    	contextReused = true;
    }

    try {
      // Push on stack
      SimulationContext.setCommandContext(context);
      SimulationContext.setSimulationEngineConfiguration(simulationEngineConfiguration);
      
      return next.execute(command);
      
    } catch (Exception e) {
    	
      context.exception(e);
      
    } finally {
      try {
    	  if (!contextReused) {
    		  context.close();
    	  }
      } finally {
    	  // Pop from stack
    	  SimulationContext.removeCommandContext();
    	  SimulationContext.removeSimulationEngineConfiguration();
      }
    }
    
    return null;
  }
  
//  public <T> T execute(Command<T> command) {
//    CommandContext context = commandContextFactory.createCommandContext(command);
//
//    try {
//      Context.setCommandContext(context);
//      Context.setProcessEngineConfiguration(processEngineConfiguration);
//      return next.execute(command);
//      
//    } catch (Exception e) {
//      context.exception(e);
//      
//    } finally {
//      try {
//        context.close();
//      } finally {
//        Context.removeCommandContext();
//        Context.removeProcessEngineConfiguration();
//      }
//    }
//    
//    return null;
//  }
  
  public CommandContextFactory getCommandContextFactory() {
    return commandContextFactory;
  }
  
  public void setCommandContextFactory(CommandContextFactory commandContextFactory) {
    this.commandContextFactory = commandContextFactory;
  }

  public SimulationEngineConfigurationImpl getSimulationEngineConfiguration() {
    return simulationEngineConfiguration;
  }

  public void setProcessEngineContext(SimulationEngineConfigurationImpl simulationEngineContext) {
    this.simulationEngineConfiguration = simulationEngineContext;
  }

  
  public boolean isContextReusePossible() {
    return isContextReusePossible;
  }

  
  public void setContextReusePossible(boolean isContextReusePossible) {
    this.isContextReusePossible = isContextReusePossible;
  }
  
}
