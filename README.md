ESProbePlugin
=============

ES Plugin that allow to perform a gracefully shutdown when ElasticSearch is used in back of a load balancer

Usage
------
When ElasticSearch is used in back of load balancer, connections are losen when ES is stopped.

This plugin avoid that.

First, the load balancer must check the connectivity by sending a message to Elasticsearch.  
`curl http://servername:9200/ESProbe/_probe`

In non stopping step, ES replies  
`{"ok":true,"Node is running"}`
In stopping mode:  
`{"ok":false,"Node is stopping"}`
    
When you want to stop the cluster you have to send  
`curl -XPOST http://servername:9200/ESProbe/_stop`

I you want to reset your restart, you have to send  
`curl -XPOST http://servername:9200/ESProbe/_reset`
    
Compilation
-----------
You need to have *maven* and *java jdk* installed

* Checkout the repository
* Edit the pom.xml to put the correct version of Elasticsearch that you used
* start mvn package
* copy target/releases/ESProbePlugin-1.0.zip on each cluster nodes
* On each cluster node start `plugin --url file:.../ESProbePlugin-1.0.zip install ESProbePlugin`
