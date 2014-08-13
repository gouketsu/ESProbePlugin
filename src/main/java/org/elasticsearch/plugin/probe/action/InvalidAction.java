package org.elasticsearch.plugin.probe.action;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentBuilderString;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.XContentRestResponse;
import org.elasticsearch.rest.action.support.RestXContentBuilder;

public class InvalidAction extends Action implements Iaction {
        private String section ;
        private String command ;
        private Method method ;

        public InvalidAction(Client client,Method method, String command) {
                super(client);
                this.command = command ;
                this.method = method ;
        }

        @Override
        public void doAndRespond(ESLogger logger,RestRequest request, RestChannel channel) {

                try {
                        XContentBuilder builder = RestXContentBuilder.restContentBuilder(request)
                                                     .startObject();
                        
                        builder.field(new XContentBuilderString("ok"), false);
                        builder.field("invalid_command",method.toString()+"/"+command )
                                                     .endObject();
                        channel.sendResponse(new XContentRestResponse(request, RestStatus.OK, builder));
                } catch (IOException e) {
                        onFailure(channel, request, logger, e);
                }
        }
}