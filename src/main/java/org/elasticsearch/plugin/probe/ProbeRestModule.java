package org.elasticsearch.plugin.probe;
import org.elasticsearch.common.inject.AbstractModule;



public class ProbeRestModule extends AbstractModule {
	@Override
    protected void configure() {
        bind(ProbeRestHandler.class).asEagerSingleton();
    }
}
