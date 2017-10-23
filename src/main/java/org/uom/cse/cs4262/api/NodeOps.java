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

    void join(Credential credential);

    void joinOk(Credential senderCredentials);

    void leave();

    void search(SearchRequest searchRequest);

    void searchOk(SearchResponse searchResponse);

    List<String> createFileList();

    void processResponse(Message response);

    boolean isRegOk();

    List<String> checkForFiles(String fileName, List<String> fileList);
}
