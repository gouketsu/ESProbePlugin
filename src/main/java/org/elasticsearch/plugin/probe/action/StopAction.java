package org.elasticsearch.plugin.probe.action;

import static org.elasticsearch.rest.RestStatus.OK;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentBuilderString;
import org.elasticsearch.plugin.probe.ESProbePlugin;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.plugin.probe.action.suppport.RestXContentBuilder;

public class StopAction extends Action implements Iaction {

	public StopAction(Client client) {
        super(client);
    }
	@Override
	public void doAndRespond(ESLogger logger, RestRequest request,
			RestChannel channel) {
		// TODO Auto-generated method stub
		ESProbePlugin.setStopping(true);
		try {
			XContentBuilder builder = RestXContentBuilder.restContentBuilder(request)
                .startObject();

			builder.field(new XContentBuilderString("ok"), true);
			builder.field("Node is set to stop" )
                .endObject();
			channel.sendResponse(new BytesRestResponse(
                    OK, builder));
		}
		catch (IOException e) {
            onFailure(channel, request, logger, e);
		}
	}

}
