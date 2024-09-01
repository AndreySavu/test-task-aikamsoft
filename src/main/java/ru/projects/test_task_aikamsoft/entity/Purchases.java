package ru.projects.test_task_aikamsoft.entity;

import java.util.HashMap;
import java.util.Map;

public class Purchases{

    private Customer customer;

    private final Map<Product, Integer> purchasesPerProducts = new HashMap<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Product, Integer> getPurchasesPerProducts() {
        return purchasesPerProducts;
    }

    public int getTotalExpenses() {
        return purchasesPerProducts.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}