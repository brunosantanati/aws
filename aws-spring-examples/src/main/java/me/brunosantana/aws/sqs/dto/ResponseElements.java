
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseElements {

    @JsonProperty("x-amz-id-2")
    private String mXAmzId2;
    @JsonProperty("x-amz-request-id")
    private String mXAmzRequestId;

    public String getXAmzId2() {
        return mXAmzId2;
    }

    public void setXAmzId2(String xAmzId2) {
        mXAmzId2 = xAmzId2;
    }

    public String getXAmzRequestId() {
        return mXAmzRequestId;
    }

    public void setXAmzRequestId(String xAmzRequestId) {
        mXAmzRequestId = xAmzRequestId;
    }

}
