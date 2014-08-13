package org.elasticsearch.plugin.probe.action;

import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestRequest;

public interface Iaction {
      public void doAndRespond(ESLogger logger,RestRequest request, RestChannel channel);
}