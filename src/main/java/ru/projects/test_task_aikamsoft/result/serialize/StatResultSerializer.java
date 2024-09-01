package ru.projects.test_task_aikamsoft.result.serialize;

import com.google.gson.*;
import ru.projects.test_task_aikamsoft.entity.Purchases;
import ru.projects.test_task_aikamsoft.entity.Product;
import ru.projects.test_task_aikamsoft.result.StatResult;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StatResultSerializer implements JsonSerializer<StatResult> {

    @Override
    public JsonElement serialize(StatResult statResult, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add("type", context.serialize(statResult.getType()));
        json.add("totalDays", context.serialize(statResult.getTotalDays()));

        JsonArray customersJsonArray = new JsonArray();
        List<Purchases> customersPurchasesList = statResult.getCustomersPurchases();
        sortCustomersPurchasesTotalExpenses(customersPurchasesList);

        for(Purchases customerPurchases: customersPurchasesList){
            JsonObject customerJson = new JsonObject();
            customerJson.add("name", context.serialize(customerPurchases.getCustomer().getFullName()));

            JsonArray purchasesJsonArray = new JsonArray();
            Map<Product, Integer> purchases = customerPurchases.getPurchasesPerProducts();

            for (Map.Entry<Product, Integer> entry : purchases.entrySet()){
                JsonObject purchaseJson = new JsonObject();
                purchaseJson.addProperty("name", entry.getKey().getName());
                purchaseJson.addProperty("expenses", entry.getValue());

                purchasesJsonArray.add(purchaseJson);
            }

            customerJson.add("purchases", purchasesJsonArray);

            customerJson.add("totalExpenses", context.serialize(customerPurchases.getTotalExpenses()));

            customersJsonArray.add(customerJson);
        }

        json.add("customers", customersJsonArray);

        statResult.countTotalExpenses();
        json.add("totalExpenses", context.serialize(statResult.getTotalExpenses()));

        statResult.countAvgExpenses();
        json.add("avgExpenses", context.serialize(statResult.getAvgExpenses()));

        return json;
    }

    private void sortCustomersPurchasesTotalExpenses(List<Purchases> customersPurchasesList){
        for(Purchases customerPurchases : customersPurchasesList){
            customerPurchases.getTotalExpenses();
        }

        customersPurchasesList.sort(Comparator.comparingInt(Purchases::getTotalExpenses).reversed());

    }
}
