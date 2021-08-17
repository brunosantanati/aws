
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bucket {

    @JsonProperty("arn")
    private String mArn;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("ownerIdentity")
    private OwnerIdentity mOwnerIdentity;

    public String getArn() {
        return mArn;
    }

    public void setArn(String arn) {
        mArn = arn;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public OwnerIdentity getOwnerIdentity() {
        return mOwnerIdentity;
    }

    public void setOwnerIdentity(OwnerIdentity ownerIdentity) {
        mOwnerIdentity = ownerIdentity;
    }

}
