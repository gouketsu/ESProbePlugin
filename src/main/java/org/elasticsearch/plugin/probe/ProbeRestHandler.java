package org.elasticsearch.plugin.probe;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugin.probe.action.ActionFactory;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;


public class ProbeRestHandler extends BaseRestHandler {
	
	@Inject
    public ProbeRestHandler(Settings settings, Client client, RestController restController) {
		super(settings, client);
		restController.registerHandler(Method.GET, "/ESProbe/{command}", this);
        restController.registerHandler(Method.POST, "/ESProbe/{command}", this);
    }
	

    public void handleRequest(final RestRequest request, final RestChannel channel, final Client client) {
		 String command = request.param("command");
		 Method method  = request.method();
		 ActionFactory.getAction(client,method, command).doAndRespond(logger, request, channel);
    }

}
