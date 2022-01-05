package me.brunosantana.sqs;

public class TestDTO {

    private String message;
    private String description;

    public TestDTO(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
