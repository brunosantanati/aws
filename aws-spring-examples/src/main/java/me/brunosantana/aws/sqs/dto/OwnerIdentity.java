
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OwnerIdentity {

    @JsonProperty("principalId")
    private String mPrincipalId;

    public String getPrincipalId() {
        return mPrincipalId;
    }

    public void setPrincipalId(String principalId) {
        mPrincipalId = principalId;
    }

}
