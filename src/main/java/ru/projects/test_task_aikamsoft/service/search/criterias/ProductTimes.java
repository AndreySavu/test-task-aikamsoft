package ru.projects.test_task_aikamsoft.service.search.criterias;

import com.google.gson.JsonElement;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ProductTimes implements Criteria {
    private String productName;
    private int minTimes;

    public String getProductName() {
        return productName;
    }

    public int getMinTimes() {
        return minTimes;
    }

    @Override
    public String getCriteriaName() {
        return "productTimes";
    }

    @Override
    public void initCriteriaParams(Set<Map.Entry<String, JsonElement>> params) {
        Iterator<Map.Entry<String, JsonElement>> iterator = params.iterator();

        Map.Entry<String, JsonElement> product = iterator.next();
        this.productName = product.getValue().getAsString();

        Map.Entry<String, JsonElement> times = iterator.next();
        this.minTimes = Integer.parseInt(times.getValue().getAsString());
    }
}
