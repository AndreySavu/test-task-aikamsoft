package ru.projects.test_task_aikamsoft.service.search.criterias.searcher;

import ru.projects.test_task_aikamsoft.utils.connetion.DatabaseConnection;
import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.service.search.criterias.Criteria;
import ru.projects.test_task_aikamsoft.result.subresult.CriteriaResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class CriteriaSearcher {
    public abstract CriteriaResult searchByCriteria(Criteria criteria) throws SQLException;

    protected List<Customer> findCustomersBySql(String sql) throws SQLException{
        List<Customer> criteriaResult = new ArrayList<>();

        Connection connection = DatabaseConnection.getInstance().getConnection();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));

                criteriaResult.add(customer);
            }
        }

        return criteriaResult;
    }
}
