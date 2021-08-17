
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {

    @JsonProperty("awsRegion")
    private String mAwsRegion;
    @JsonProperty("eventName")
    private String mEventName;
    @JsonProperty("eventSource")
    private String mEventSource;
    @JsonProperty("eventTime")
    private String mEventTime;
    @JsonProperty("eventVersion")
    private String mEventVersion;
    @JsonProperty("requestParameters")
    private RequestParameters mRequestParameters;
    @JsonProperty("responseElements")
    private ResponseElements mResponseElements;
    @JsonProperty("s3")
    private S3 mS3;
    @JsonProperty("userIdentity")
    private UserIdentity mUserIdentity;

    public String getAwsRegion() {
        return mAwsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        mAwsRegion = awsRegion;
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
    }

    public String getEventSource() {
        return mEventSource;
    }

    public void setEventSource(String eventSource) {
        mEventSource = eventSource;
    }

    public String getEventTime() {
        return mEventTime;
    }

    public void setEventTime(String eventTime) {
        mEventTime = eventTime;
    }

    public String getEventVersion() {
        return mEventVersion;
    }

    public void setEventVersion(String eventVersion) {
        mEventVersion = eventVersion;
    }

    public RequestParameters getRequestParameters() {
        return mRequestParameters;
    }

    public void setRequestParameters(RequestParameters requestParameters) {
        mRequestParameters = requestParameters;
    }

    public ResponseElements getResponseElements() {
        return mResponseElements;
    }

    public void setResponseElements(ResponseElements responseElements) {
        mResponseElements = responseElements;
    }

    public S3 getS3() {
        return mS3;
    }

    public void setS3(S3 s3) {
        mS3 = s3;
    }

    public UserIdentity getUserIdentity() {
        return mUserIdentity;
    }

    public void setUserIdentity(UserIdentity userIdentity) {
        mUserIdentity = userIdentity;
    }

}
