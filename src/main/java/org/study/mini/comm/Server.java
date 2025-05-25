package org.study.mini.comm;

/**
 * 1. ClassName     : Server
 * 2. FileName      : Server.java
 * 3. Package       : org.study.mini
 * 4. Author        : 윤명석
 * 5. Creation Date : 25. 5. 25. 오후 7:14
 * 6. Comment       :
 */
public enum Server {
    WAS("was"), KAFKA("kafka"), PROXY("proxy"), PROV("prov"), AGENCY("agency"), LOG("log");
    
    private final String label;
    
    Server(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}
