package com.socks.api;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

//    @DefaultValue("test")
//    String env();

//    @Key("${env}.baseUrl")
    String baseUrl();

    @DefaultValue("de")
    String locale();

    boolean logging();
}
