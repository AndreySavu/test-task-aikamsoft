package ru.projects.test_task_aikamsoft.service.stat;

import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;
import ru.projects.test_task_aikamsoft.service.Operation;
import ru.projects.test_task_aikamsoft.service.stat.searcher.CustomerPurchasesSearcher;
import ru.projects.test_task_aikamsoft.parser.util.TotalDaysCounter;
import ru.projects.test_task_aikamsoft.result.Result;
import ru.projects.test_task_aikamsoft.result.StatResult;

import java.sql.SQLException;
import java.util.ArrayList;

public class Stat implements Operation {
    private String startDate;

    private String endDate;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public Result performOperation() throws SQLException, WrongDateFormatException {
        StatResult statResult = new StatResult();

        TotalDaysCounter totalDaysCounter = new TotalDaysCounter();
        statResult.setTotalDays(totalDaysCounter.getTotalDaysCount(startDate, endDate));

        CustomerPurchasesSearcher customerPurchasesSearcher = new CustomerPurchasesSearcher();
        statResult.setCustomersPurchases(new ArrayList<>(customerPurchasesSearcher.
                getCustomersPurchasesForPeriod(startDate, endDate).values()));

        return statResult;
    }



}
