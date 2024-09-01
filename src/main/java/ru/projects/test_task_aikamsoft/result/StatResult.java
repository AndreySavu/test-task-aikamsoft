package ru.projects.test_task_aikamsoft.result;

import ru.projects.test_task_aikamsoft.entity.Purchases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StatResult extends Result{

    private int totalDays;

    private List<Purchases> customersPurchases;

    private int totalExpenses = 0;

    private double avgExpenses;

    public StatResult(){
        super();
        setType("stat");
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public void countTotalExpenses(){
        for(Purchases customerPurchases : customersPurchases){
            totalExpenses += customerPurchases.getTotalExpenses();
        }
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void countAvgExpenses(){
        avgExpenses = totalExpenses/(double) customersPurchases.size();
    }

    public double getAvgExpenses() {
        BigDecimal decimal = BigDecimal.valueOf(avgExpenses);
        BigDecimal rounded = decimal.setScale(2, RoundingMode.HALF_EVEN);
        return rounded.doubleValue();
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    public List<Purchases> getCustomersPurchases() {
        return customersPurchases;
    }

    public void setCustomersPurchases(List<Purchases> customersPurchases) {
        this.customersPurchases = customersPurchases;
    }
}
