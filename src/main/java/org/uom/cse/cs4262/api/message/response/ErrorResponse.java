package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Chamin Wickramarathna
 * @date 24/10/2017
 * @since 1.0
 */
public class ErrorResponse extends Message {

    @Override
    public String getMessageAsString(String message) {
        return super.getMessageAsString(message);
    }

    @Override
    public String toString() {
        System.out.println("An error occurred");

        return super.toString();
    }
}
