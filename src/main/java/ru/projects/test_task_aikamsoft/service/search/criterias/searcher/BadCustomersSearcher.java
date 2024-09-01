package ru.projects.test_task_aikamsoft.service.search.criterias.searcher;

import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.service.search.criterias.BadCustomers;
import ru.projects.test_task_aikamsoft.service.search.criterias.Criteria;
import ru.projects.test_task_aikamsoft.result.subresult.CriteriaResult;

import java.sql.SQLException;
import java.util.List;

public class BadCustomersSearcher extends CriteriaSearcher{
    @Override
    public CriteriaResult searchByCriteria(Criteria criteria) throws SQLException {
        BadCustomers criteriaBadCustomers = (BadCustomers) criteria;

        CriteriaResult criteriaResult = new CriteriaResult();
        criteriaResult.setCriteria(criteriaBadCustomers);

        String sql = "with ct1 as (\n" +
                "    select c.first_name, c.last_name, count(*) as purchases_count\n" +
                "    from purchases\n" +
                "        inner join customers c on c.id = purchases.customer_id\n" +
                "    group by c.last_name, c.first_name\n" +
                "),\n" +
                "    ct2 as(\n" +
                "        select min(ct1.purchases_count) as min_count from ct1\n" +
                "    ),\n" +
                "    ct3 as(\n" +
                "        select ct1.first_name, ct1.last_name from ct1\n" +
                "        where ct1.purchases_count = (select * from ct2)\n" +
                "    )\n" +
                "select * from ct3 limit " + criteriaBadCustomers.getBadCustomers();

        List<Customer> foundCustomers = findCustomersBySql(sql);
        criteriaResult.setResultsCustomers(foundCustomers);

        return criteriaResult;
    }
}
