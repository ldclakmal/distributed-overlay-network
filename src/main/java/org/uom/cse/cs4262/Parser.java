package org.uom.cse.cs4262;

import org.uom.cse.cs4262.api.Constant;
import org.uom.cse.cs4262.api.message.Message;
import org.uom.cse.cs4262.api.message.response.RegisterResponse;

import java.util.StringTokenizer;

/**
 * @author Chanaka Lakmal
 * @date 22/10/2017
 * @since 1.0
 */

public class Parser {

    public static Message parse(String message) {
        System.out.println("Message received : " + message);
        StringTokenizer st = new StringTokenizer(message, " ");

        String length = st.nextToken();
        String command = st.nextToken();

        if (command.equals(Constant.Command.REGOK)) {
            RegisterResponse registerResponse = new RegisterResponse();
            //TODO: split the message and create registerResponse object. Refer BootstrapServer.java class for tokenize
            return registerResponse;
        }

        return null;
    }
}
