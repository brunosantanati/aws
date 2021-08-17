
package me.brunosantana.aws.sqs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message {

    @JsonProperty("Records")
    private List<Record> mRecords;

    public List<Record> getRecords() {
        return mRecords;
    }

    public void setRecords(List<Record> records) {
        mRecords = records;
    }

}
