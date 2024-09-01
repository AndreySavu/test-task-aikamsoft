package ru.projects.test_task_aikamsoft.parser.json.operation;

import ru.projects.test_task_aikamsoft.utils.error.WrongArgumentCountException;
import ru.projects.test_task_aikamsoft.utils.error.WrongArgumentException;
import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.result.Result;

import java.io.IOException;

public interface OperationJsonParser {
    Operation initOperation(String inputSrcName)
            throws
            WrongArgumentCountException,
            WrongArgumentException,
            IOException,
            WrongDateFormatException;

    void saveOperationResult(Result result, String outputSrcName);

}
