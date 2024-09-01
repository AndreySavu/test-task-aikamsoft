package ru.projects.test_task_aikamsoft;

import ru.projects.test_task_aikamsoft.parser.cli.ArgumentParser;
import ru.projects.test_task_aikamsoft.parser.json.ErrorJsonParser;
import ru.projects.test_task_aikamsoft.parser.json.operation.OperationJsonParser;
import ru.projects.test_task_aikamsoft.result.ErrorResult;
import ru.projects.test_task_aikamsoft.result.Result;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.utils.connetion.DatabaseConnection;

import java.sql.SQLException;

public class TestTaskAikamsoftApplication {

	public static void main(String[] args) {
		ArgumentParser argumentParser = new ArgumentParser();

		try {
			argumentParser.parseCliArguments(args);
			DatabaseConnection.getInstance().createConnection();

			OperationJsonParser operationJsonParser = argumentParser.createOperationJsonParserByName();
			Operation operation = operationJsonParser.initOperation(argumentParser.getInputSrcName());
			Result result = operation.performOperation();
			operationJsonParser.saveOperationResult(result, argumentParser.getOutputSrcName());

			DatabaseConnection.getInstance().closeConnection();
		}
		catch (Exception exception){

			ErrorResult errorResult = new ErrorResult();
			StringBuilder errorMessage = new StringBuilder();

			try {
				DatabaseConnection.getInstance().closeConnection();
			}
			catch (SQLException sqlException){
				errorMessage.append(sqlException.getMessage()).append(System.lineSeparator());
			}

			errorMessage.append(exception.getMessage());
			errorResult.setMessage(errorMessage.toString());

			String errorFileName = argumentParser.getOutputSrcName()!= null
					&& !argumentParser.getOutputSrcName().isEmpty() ?
					argumentParser.getOutputSrcName() : "error.json";

			ErrorJsonParser.saveErrorResult(errorResult, errorFileName);
		}
	}

}


