package org.uom.cse.cs4262.api.message.response;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Chandu Herath
 * @date 23/10/2017
 * @since 1.0
 */

public class JoinResponse extends Message {

    private int value;

    private Credential senderCredential;

    public JoinResponse(int value, Credential senderCredential) {
        this.value = value;
        this.senderCredential = senderCredential;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Credential getSenderCredential() {
        return senderCredential;
    }

    public void setSenderCredential(Credential senderCredential) {
        this.senderCredential = senderCredential;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getValue();
        return super.getMessageAsString(message);
    }
}
