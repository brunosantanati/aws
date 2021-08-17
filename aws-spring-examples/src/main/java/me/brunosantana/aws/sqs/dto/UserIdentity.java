
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserIdentity {

    @JsonProperty("principalId")
    private String mPrincipalId;

    public String getPrincipalId() {
        return mPrincipalId;
    }

    public void setPrincipalId(String principalId) {
        mPrincipalId = principalId;
    }

}
