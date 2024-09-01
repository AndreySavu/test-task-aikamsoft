package ru.projects.test_task_aikamsoft.parser.cli;

import ru.projects.test_task_aikamsoft.utils.error.OperationNotFoundException;
import ru.projects.test_task_aikamsoft.utils.error.WrongArgumentCountException;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.service.search.Search;
import ru.projects.test_task_aikamsoft.service.stat.Stat;
import ru.projects.test_task_aikamsoft.parser.json.operation.OperationJsonParser;
import ru.projects.test_task_aikamsoft.parser.json.operation.SearchJsonParser;
import ru.projects.test_task_aikamsoft.parser.json.operation.StatJsonParser;

public class ArgumentParser {

    private OperationType operationType;

    private String inputSrcName;

    private String outputSrcName;

    public void parseCliArguments(String[] args) throws WrongArgumentCountException, OperationNotFoundException {
        checkSizeOfArguments(args);
        checkOperationName(args[0]);

        this.inputSrcName = args[1];

        this.outputSrcName = args[2];
    }

    public String getInputSrcName() {
        return inputSrcName;
    }

    public String getOutputSrcName() {
        return outputSrcName;
    }

    public Operation createOperationByName(){
        switch (this.operationType){
            case SEARCH:
                return new Search();
            case STAT:
                return new Stat();
            default:
                return null;
        }
    }

    public OperationJsonParser createOperationJsonParserByName(){
        switch (this.operationType){
            case SEARCH:
                return new SearchJsonParser();
            case STAT:
                return new StatJsonParser();
            default:
                return null;
        }
    }

    private void checkSizeOfArguments(String[] args) throws WrongArgumentCountException{
        if(args.length != 3){
            throw new WrongArgumentCountException("Неправильное количество аргументов!");
        }
    }

    private void checkOperationName(String operation) throws OperationNotFoundException {
        switch (operation){
            case "search":
                this.operationType = OperationType.SEARCH;
                break;
            case "stat":
                this.operationType = OperationType.STAT;
                break;
            default:
                throw new OperationNotFoundException("Данная операция не найдена!");
        }
    }


}
