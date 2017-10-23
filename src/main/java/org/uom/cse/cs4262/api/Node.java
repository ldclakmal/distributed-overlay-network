package org.uom.cse.cs4262.api;

import java.util.List;

/**
 * @author Chamin Wickramarathna
 * @date 22/10/2017
 * @since 1.0
 */
public class Node {

    private Credential credential;
    private List<String> fileList;
    private List<Credential> routingTable;
    private List<StatRecord> statTable;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public List<Credential> getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(List<Credential> routingTable) {
        this.routingTable = routingTable;
    }

    public List<StatRecord> getStatTable() {
        return statTable;
    }

    public void setStatTable(List<StatRecord> statTable) {
        this.statTable = statTable;
    }
}
