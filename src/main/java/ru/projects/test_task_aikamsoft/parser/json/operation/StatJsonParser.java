package ru.projects.test_task_aikamsoft.parser.json.operation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.service.stat.Stat;
import ru.projects.test_task_aikamsoft.parser.util.DateValidator;
import ru.projects.test_task_aikamsoft.result.Result;
import ru.projects.test_task_aikamsoft.result.StatResult;
import ru.projects.test_task_aikamsoft.result.serialize.StatResultSerializer;
import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatJsonParser implements OperationJsonParser{

    private static final Logger logger = Logger.getLogger(StatJsonParser.class.getName());

    @Override
    public Operation initOperation(String inputSrcName)
            throws IOException, WrongDateFormatException {
        Gson gson = new Gson();

        try(JsonReader reader = new JsonReader(new FileReader(inputSrcName))){
            Stat stat = new Stat();

            JsonObject json = gson.fromJson(reader, JsonObject.class);

            String startDate = json.get("startDate").getAsString();
            String endDate = json.get("endDate").getAsString();

            DateValidator dateValidator = new DateValidator("yyyy-MM-dd");
            dateValidator.validateDateStr(startDate);
            dateValidator.validateDateStr(endDate);
            dateValidator.checkStartDateBeforeEndDate(startDate, endDate);

            stat.setStartDate(startDate);
            stat.setEndDate(endDate);

            return stat;
        }

    }

    @Override
    public void saveOperationResult(Result result, String outputSrcName) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(StatResult.class, new StatResultSerializer())
                .setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(outputSrcName)){
            gson.toJson(result, writer);
        } catch (Exception exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
    }

}
