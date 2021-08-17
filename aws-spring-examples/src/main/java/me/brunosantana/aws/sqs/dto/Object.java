
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Object {

    @JsonProperty("eTag")
    private String mETag;
    @JsonProperty("key")
    private String mKey;
    @JsonProperty("sequencer")
    private String mSequencer;
    @JsonProperty("size")
    private Long mSize;

    public String getETag() {
        return mETag;
    }

    public void setETag(String eTag) {
        mETag = eTag;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getSequencer() {
        return mSequencer;
    }

    public void setSequencer(String sequencer) {
        mSequencer = sequencer;
    }

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        mSize = size;
    }

}
