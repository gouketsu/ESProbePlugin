package org.elasticsearch.plugin.probe.action;

import org.elasticsearch.client.Client;
import org.elasticsearch.rest.RestRequest.Method;

public class ActionFactory {
	public static Iaction getAction(Client client,Method method,String command) {
		if(method.equals(Method.GET)) {
			if (command.equals(Action.PROBE)) {
                return new ProbeAction(client);
             } else {
            	 return new InvalidAction(client, method, command);
             }
		}
        if(method.equals(Method.POST)) {
           if (command.equals(Action.STOP)) {
              return new StopAction(client);
           }  else if (command.equals(Action.RESET)) {
        	   return new ResetAction(client);
           } else {
               return new InvalidAction(client, method, command);
           }
        }
        
        return new InvalidAction(client, method, command);
	}
}