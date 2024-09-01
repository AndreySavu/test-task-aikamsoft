package ru.projects.test_task_aikamsoft.service.search.criterias.searcher;

import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.service.search.criterias.Criteria;
import ru.projects.test_task_aikamsoft.service.search.criterias.LimitsExpenses;
import ru.projects.test_task_aikamsoft.result.subresult.CriteriaResult;

import java.sql.SQLException;
import java.util.List;

public class LimitExpensesSearcher extends CriteriaSearcher{
    @Override
    public CriteriaResult searchByCriteria(Criteria criteria) throws SQLException {
        LimitsExpenses limitCriteria = (LimitsExpenses) criteria;

        CriteriaResult criteriaResult = new CriteriaResult();
        criteriaResult.setCriteria(limitCriteria);

        String sql = "with ct1 as(\n" +
                "    select purchases.customer_id as customer_id, products.cost as cost\n" +
                "    from purchases inner join products on purchases.product_id = products.id\n" +
                "),\n" +
                "ct2 as(\n" +
                "    select ct1.customer_id, sum(ct1.cost) as total_cost from ct1\n" +
                "    group by ct1.customer_id\n" +
                "),\n" +
                "ct3 as(\n" +
                "    select customers.firstname, customers.lastname\n" +
                "    from ct2 inner join customers on ct2.customer_id = customers.id\n" +
                "    where (ct2.total_cost >= "+ limitCriteria.getMinExpenses() +" " +
                "and ct2.total_cost <= "+ limitCriteria.getMaxExpenses() +")\n" +
                ")\n" +
                "select * from ct3;";

        List<Customer> foundCustomers = findCustomersBySql(sql);
        criteriaResult.setResultsCustomers(foundCustomers);

        return criteriaResult;
    }
}
