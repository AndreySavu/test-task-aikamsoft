package ru.projects.test_task_aikamsoft.service.search.criterias;

import com.google.gson.JsonElement;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LimitsExpenses implements Criteria {
    private int minExpenses;
    private int maxExpenses;

    public int getMinExpenses() {
        return minExpenses;
    }

    public int getMaxExpenses() {
        return maxExpenses;
    }

    @Override
    public String getCriteriaName() {
        return "limitExpenses";
    }

    @Override
    public void initCriteriaParams(Set<Map.Entry<String, JsonElement>> params) {
        Iterator<Map.Entry<String, JsonElement>> iterator = params.iterator();

        Map.Entry<String, JsonElement> minValue = iterator.next();
        this.minExpenses = Integer.parseInt(minValue.getValue().getAsString());

        Map.Entry<String, JsonElement> maxValue = iterator.next();
        this.maxExpenses = Integer.parseInt(maxValue.getValue().getAsString());
    }
}
