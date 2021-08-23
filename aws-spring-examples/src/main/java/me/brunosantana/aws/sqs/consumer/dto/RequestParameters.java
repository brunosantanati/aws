
package me.brunosantana.aws.sqs.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestParameters {

    @JsonProperty("sourceIPAddress")
    private String mSourceIPAddress;

    public String getSourceIPAddress() {
        return mSourceIPAddress;
    }

    public void setSourceIPAddress(String sourceIPAddress) {
        mSourceIPAddress = sourceIPAddress;
    }

}
