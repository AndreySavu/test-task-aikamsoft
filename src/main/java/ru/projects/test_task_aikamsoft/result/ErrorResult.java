package ru.projects.test_task_aikamsoft.result;

public class ErrorResult extends Result{

    private String message;

    public ErrorResult(){
        super();
        setType("error");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
