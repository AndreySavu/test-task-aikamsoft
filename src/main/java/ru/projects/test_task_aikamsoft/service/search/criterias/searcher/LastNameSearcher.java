package ru.projects.test_task_aikamsoft.service.search.criterias.searcher;

import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.service.search.criterias.Criteria;
import ru.projects.test_task_aikamsoft.service.search.criterias.LastName;
import ru.projects.test_task_aikamsoft.result.subresult.CriteriaResult;

import java.sql.SQLException;
import java.util.List;

public class LastNameSearcher extends CriteriaSearcher{
    @Override
    public CriteriaResult searchByCriteria(Criteria criteria) throws SQLException{
        LastName criteriaLastName = (LastName) criteria;

        CriteriaResult criteriaResult = new CriteriaResult();
        criteriaResult.setCriteria(criteriaLastName);

        String sql = "select customers.first_name, customers.last_name from customers " +
                "where customers.last_name like '" + criteriaLastName.getLastName() + "'";

        List<Customer> foundCustomers = findCustomersBySql(sql);
        criteriaResult.setResultsCustomers(foundCustomers);

        return criteriaResult;
    }
}
