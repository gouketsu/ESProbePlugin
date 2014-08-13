package org.elasticsearch.plugin.probe.action;
import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentBuilderString;
import org.elasticsearch.plugin.probe.ESProbePlugin;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.XContentRestResponse;
import org.elasticsearch.rest.action.support.RestXContentBuilder;




public class ProbeAction extends Action implements Iaction {
	
	public ProbeAction(Client client) {
         super(client);
    }
	
	@Override
	public void doAndRespond(ESLogger logger, RestRequest request,
			RestChannel channel) {
		// TODO Auto-generated method stub

			if (ESProbePlugin.getStopping()) {
				try{
					XContentBuilder builder = RestXContentBuilder.restContentBuilder(request)
                         .startObject();

					builder.field(new XContentBuilderString("ok"), false);
					builder.field("Node is stopping" )
                         .endObject();
					channel.sendResponse(new XContentRestResponse(request, RestStatus.BAD_REQUEST, builder));
				}	
				catch (IOException e) {
                        onFailure(channel, request, logger, e);
                }
			}else{ 
				try {
					XContentBuilder builder = RestXContentBuilder.restContentBuilder(request)
                    .startObject();

					builder.field(new XContentBuilderString("ok"), true);
					builder.field("Node is running" )
                    .endObject();
					channel.sendResponse(new XContentRestResponse(request, RestStatus.OK, builder));
				}
				catch (IOException e) {
                    onFailure(channel, request, logger, e);
				}
			}
				
	}

}
