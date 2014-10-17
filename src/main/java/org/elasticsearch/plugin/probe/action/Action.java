package org.elasticsearch.plugin.probe.action;

import static org.elasticsearch.rest.RestStatus.BAD_REQUEST;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.plugin.probe.action.suppport.RestXContentBuilder;


public class Action {
	public static final String PROBE = "_probe";
    public static final String STOP = "_stop";
    public static final String RESET = "_reset";

    protected Client client;

    /**
     * constructeur
     * @param client client ES
     */
    public Action(Client client) {
       this.client = client ;
    }

    /**
     * réponse REST en cas d'erreur.
     * @param channel channel
     * @param requestrequest
     * @param loggerlogger
     * @param e exception ES
     */
    protected void onFailure(RestChannel channel,
                                     RestRequest request,
                                     ESLogger logger,
                                     IOException e)
    {
            try {
            	XContentBuilder builder = RestXContentBuilder
                        .restContentBuilder(
                                request);
                channel.sendResponse(new BytesRestResponse(
                        BAD_REQUEST, builder.startObject().field("error",
                        e.getMessage()).endObject()));

            } catch (IOException e1) {
                    logger.error("Failed to send failure response", e1);
            }
    }
}