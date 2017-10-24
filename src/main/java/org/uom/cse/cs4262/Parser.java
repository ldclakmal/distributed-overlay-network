package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.request.SearchRequest;
import org.uom.cse.cs4262.api.message.response.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class Parser {

    public static Message parse(String message, Credential senderCredential) {

        System.out.println("Message received : " + message);
        StringTokenizer st = new StringTokenizer(message, " ");

        String length = st.nextToken();
        String command = st.nextToken();

        if (command.equals(Constant.Command.REGOK)) {
            int numOfNodes = Integer.parseInt(st.nextToken());
            String ip;
            int port;
            List<Credential> nodes = new ArrayList<>();
            for (int i = 0; i < numOfNodes; i++) {
                ip = st.nextToken();
                port = Integer.parseInt(st.nextToken());
                nodes.add(new Credential(ip, port, null));
            }
            RegisterResponse registerResponse = new RegisterResponse(numOfNodes, nodes);
            return registerResponse;

        } else if (command.equals(Constant.Command.UNREGOK)) {
            int value = Integer.parseInt(st.nextToken());
            return new UnregisterResponse(value);

        } else if (command.equals(Constant.Command.JOINOK)) {
            int value = Integer.parseInt(st.nextToken());
            return new JoinResponse(value, senderCredential);

        } else if (command.equals(Constant.Command.LEAVEOK)) {
            int value = Integer.parseInt(st.nextToken());
            return new LeaveResponse(value);

        } else if(command.equals(Constant.Command.SEARCH)){
            int seqNum = Integer.parseInt(st.nextToken());
            String ip = st.nextToken();
            int port = Integer.parseInt(st.nextToken());
            String fileName = st.nextToken();
            int hops = Integer.parseInt(st.nextToken());
            Credential crd = new Credential(ip,port,null);
            return new SearchRequest(seqNum,crd,fileName,hops);

        } else if (command.equals(Constant.Command.SEARCHOK)) {
            int sequenceNo = Integer.parseInt(st.nextToken());
            int numOfFiles = Integer.parseInt(st.nextToken());
            String ip = st.nextToken();
            int port = Integer.parseInt(st.nextToken());
            int hops = Integer.parseInt(st.nextToken());
            List<String> fileList = new ArrayList<>();
            for (int i = 0; i < numOfFiles; i++) {
                fileList.add(st.nextToken());
            }
            Credential endNodeCredentials = new Credential(ip, port, null);
            return new SearchResponse(sequenceNo, numOfFiles, endNodeCredentials, hops, fileList);

        } else if (command.equals(Constant.Command.ERROR)) {
            return new ErrorResponse();
        }

        return null;
    }
}
