package ru.projects.test_task_aikamsoft.parser.json.operation;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import ru.projects.test_task_aikamsoft.utils.error.WrongArgumentCountException;
import ru.projects.test_task_aikamsoft.utils.error.WrongArgumentException;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.service.search.Search;
import ru.projects.test_task_aikamsoft.service.search.criterias.*;
import ru.projects.test_task_aikamsoft.result.Result;
import ru.projects.test_task_aikamsoft.result.SearchResult;
import ru.projects.test_task_aikamsoft.result.serialize.SearchResultSerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchJsonParser implements OperationJsonParser{

    private static final Logger logger = Logger.getLogger(SearchJsonParser.class.getName());

    @Override
    public Operation initOperation(String inputSrcName)
            throws WrongArgumentCountException, WrongArgumentException, IOException {
        Gson gson = new Gson();

        try(JsonReader reader = new JsonReader(new FileReader(inputSrcName))){
            Search search = new Search();

            JsonObject json = gson.fromJson(reader, JsonObject.class);
            JsonArray jsonCriterias = json.getAsJsonArray("criterias");

            List<Criteria> criterias = initCriterias(jsonCriterias);

            search.setCriteriaList(criterias);

            return search;
        }
    }

    @Override
    public void saveOperationResult(Result result, String outputSrcName) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(SearchResult.class, new SearchResultSerializer())
                .setPrettyPrinting().create();

        try(FileWriter writer = new FileWriter(outputSrcName)){
            gson.toJson(result, writer);
        } catch (Exception exception){
            logger.log(Level.SEVERE, exception.getMessage());
        }
    }

    private List<Criteria> initCriterias(JsonArray jsonArray) throws WrongArgumentCountException, WrongArgumentException {

        List<Criteria> criterias = new ArrayList<>();

        for(JsonElement element : jsonArray){
            criterias.add(initCriteria(element.getAsJsonObject()));
        }

        return criterias;
    }

    private Criteria initCriteria(JsonObject jsonObject) throws WrongArgumentCountException, WrongArgumentException {
        Set<String> keys = jsonObject.keySet();

        Criteria criteria = defineCriteriaByName(keys);
        criteria.initCriteriaParams(jsonObject.entrySet());

        return criteria;
    }

    private Criteria defineCriteriaByName(Set<String> keys) throws WrongArgumentException, WrongArgumentCountException {
        Iterator<String> iterator = keys.iterator();

        if (keys.size() == 1) {
            String paramName = iterator.next();

            if (paramName.equals("lastName")) {
                return new LastName();
            } else if (paramName.equals("badCustomers")) {
                return new BadCustomers();
            } else throw new WrongArgumentException("Название параметра неизвестно!");

        } else if (keys.size() == 2) {

            String firstParam = iterator.next();

            String secondParam = iterator.next();

            if (firstParam.equals("productName") && secondParam.equals("minTimes")) {
                return new ProductTimes();
            } else if (firstParam.equals("minExpenses") && secondParam.equals("maxExpenses")) {
                return new LimitsExpenses();
            } else {
                throw new WrongArgumentException("Не указан второй параметр составного параметра! Или неверный порядок параметров!");
            }
        } else {
            throw new WrongArgumentCountException("Неправильное количество аргмуентов для критерия!");
        }
    }

}
