package org.uom.cse.cs4262.api;

import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.SearchRequest;
import org.uom.cse.cs4262.api.message.response.SearchResponse;

import java.util.List;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public interface NodeOps {

    void start();

    void register();

    void unRegister();

    void join(Credential neighbourCredential);

    void joinOk(Credential senderCredential);

    void leave(Credential neighbourCredential);

    void leaveOk(Credential senderCredential);

    void search(SearchRequest searchRequest, Credential sendCredential);

    void searchOk(SearchResponse searchResponse);

    List<String> createFileList();

    void processResponse(Message response);

    void error(Credential senderCredential);

    boolean isRegOk();

    List<String> checkForFiles(String fileName, List<String> fileList);

    void triggerSearchRequest(SearchRequest searchRequest);

    void printRoutingTable(List<Credential> routingTable);
}
