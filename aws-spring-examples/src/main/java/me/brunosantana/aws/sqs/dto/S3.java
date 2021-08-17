
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3 {

    @JsonProperty("bucket")
    private Bucket mBucket;
    @JsonProperty("configurationId")
    private String mConfigurationId;
    @JsonProperty("object")
    private Object mObject;
    @JsonProperty("s3SchemaVersion")
    private String mS3SchemaVersion;

    public Bucket getBucket() {
        return mBucket;
    }

    public void setBucket(Bucket bucket) {
        mBucket = bucket;
    }

    public String getConfigurationId() {
        return mConfigurationId;
    }

    public void setConfigurationId(String configurationId) {
        mConfigurationId = configurationId;
    }

    public Object getObject() {
        return mObject;
    }

    public void setObject(Object object) {
        mObject = object;
    }

    public String getS3SchemaVersion() {
        return mS3SchemaVersion;
    }

    public void setS3SchemaVersion(String s3SchemaVersion) {
        mS3SchemaVersion = s3SchemaVersion;
    }

}
