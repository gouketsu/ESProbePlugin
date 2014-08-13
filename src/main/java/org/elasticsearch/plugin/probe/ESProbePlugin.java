package org.elasticsearch.plugin.probe;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import java.util.Collection;



public class ESProbePlugin extends AbstractPlugin {
	 static boolean stopping = false;
	
	 @Override public String name() {
	     return "ESProbePlugin";
	 }

	 @Override public String description() {
	     return "VIP control plugin to gracefully shutdown";
	 }
	 @Override
	 public Collection<Class<? extends Module>> modules() {
	     Collection<Class<? extends Module>> modules = Lists.newArrayList();
	     modules.add(ProbeRestModule.class);
	     return modules;
	 }
	 
	 static public void setStopping(boolean value) {
		 stopping = value;
	 }
	 
	 static public boolean getStopping() {
		 return stopping;
	 }
	 
	
}
