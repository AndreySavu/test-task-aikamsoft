package ru.projects.test_task_aikamsoft.utils.error;

public class NoDataBaseConnectionException extends Exception{

    public NoDataBaseConnectionException(String message){
        super(message);
    }
}
