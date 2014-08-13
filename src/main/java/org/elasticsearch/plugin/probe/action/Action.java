package org.elasticsearch.plugin.probe.action;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.XContentThrowableRestResponse;

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
                    channel.sendResponse(new XContentThrowableRestResponse(request, e));
            } catch (IOException e1) {
                    logger.error("Failed to send failure response", e1);
            }
    }
}