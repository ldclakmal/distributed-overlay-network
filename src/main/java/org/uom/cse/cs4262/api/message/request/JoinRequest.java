package org.uom.cse.cs4262.api.message.request;

import org.uom.cse.cs4262.api.Credential;
import org.uom.cse.cs4262.api.message.Message;

/**
 * @author Sachithra Dangalla
 * @date 10/22/2017
 * @since 1.0
 */
public class JoinRequest extends Message {

    private Credential credential;

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String getMessageAsString(String message) {
        message += " " + this.getCredential().getIp() + " " + this.getCredential().getPort();
        return super.getMessageAsString(message);
    }
}