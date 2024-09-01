package ru.projects.test_task_aikamsoft.service.search.criterias;

import com.google.gson.JsonElement;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BadCustomers implements Criteria{
    private int badCustomers;

    public int getBadCustomers() {
        return badCustomers;
    }

    @Override
    public String getCriteriaName() {
        return "badCustomers";
    }

    @Override
    public void initCriteriaParams(Set<Map.Entry<String, JsonElement>> params) {
        Iterator<Map.Entry<String, JsonElement>> iterator = params.iterator();
        Map.Entry<String, JsonElement> entry = iterator.next();
        badCustomers = Integer.parseInt(entry.getValue().getAsString());
    }
}
