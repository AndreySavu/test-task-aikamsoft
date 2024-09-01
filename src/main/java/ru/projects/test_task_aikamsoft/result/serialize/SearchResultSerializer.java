package ru.projects.test_task_aikamsoft.result.serialize;

import com.google.gson.*;
import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.result.SearchResult;
import ru.projects.test_task_aikamsoft.result.subresult.CriteriaResult;

import java.lang.reflect.Type;
import java.util.List;

public class SearchResultSerializer implements JsonSerializer<SearchResult> {
    @Override
    public JsonElement serialize(SearchResult searchResult, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add("type", context.serialize(searchResult.getType()));

        JsonArray results = new JsonArray();

        List<CriteriaResult> criteriaResultList = searchResult.getCriteriaResultList();

        for(CriteriaResult criteriaResult : criteriaResultList){
            JsonObject jsonSubResult = new JsonObject();
            jsonSubResult.add("criteria", context.serialize(criteriaResult.getCriteria()));

            JsonArray customerJsonArray = new JsonArray();
            List<Customer> customerList = criteriaResult.getResultsCustomers();
            for(Customer customer: customerList){
                JsonObject customerJson = new JsonObject();
                customerJson.add("firstName", context.serialize(customer.getFirstName()));
                customerJson.add("lastName", context.serialize(customer.getLastName()));
                customerJsonArray.add(customerJson);
            }

            jsonSubResult.add("results", customerJsonArray);

            results.add(jsonSubResult);
        }

        json.add("results", results);
        return json;
    }
}
