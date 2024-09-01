package ru.projects.test_task_aikamsoft.service.stat.searcher;

import ru.projects.test_task_aikamsoft.utils.connetion.DatabaseConnection;
import ru.projects.test_task_aikamsoft.entity.Customer;
import ru.projects.test_task_aikamsoft.entity.Purchases;
import ru.projects.test_task_aikamsoft.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CustomerPurchasesSearcher{

    public Map<Customer, Purchases> getCustomersPurchasesForPeriod(String startDate, String endDate) throws SQLException {

        String sql = "with ct1 as(\n" +
                "    select customers.firstname, customers.lastname, purchases.product_id\n" +
                "    from customers inner join purchases on customers.id = purchases.customer_id\n" +
                "    where\n" +
                "    purchases.date_of_purchase >= to_date('"+startDate+"', 'yyyy-mm-dd')\n" +
                "      and purchases.date_of_purchase <= to_date('"+endDate+"', 'yyyy-mm-dd')\n" +
                "    and date_part('dow', purchases.date_of_purchase) != 0\n" +
                "    and date_part('dow', purchases.date_of_purchase) != 6" +
                "),\n" +
                "ct2 as(\n" +
                "    select ct1.firstname, ct1.lastname, products.name, products.cost\n" +
                "    from ct1 inner join products on ct1.product_id = products.id\n" +
                "),\n" +
                "ct3 as(\n" +
                "    select ct2.firstname, ct2.lastname, ct2.name, ct2.cost, sum(ct2.cost) as sum\n" +
                "    from ct2\n" +
                "    group by ct2.firstname, ct2.lastname, ct2.name, ct2.cost\n" +
                "    order by ct2.firstname\n" +
                ")\n" +
                "select * from ct3 order by ct3.sum desc;";

        return getCustomersPurchasesBySql(sql);
    }


    private Map<Customer, Purchases> getCustomersPurchasesBySql(String sql) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        Map<Customer, Purchases> customersPurchases = new HashMap<>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setFirstName(resultSet.getString("firstname"));
                customer.setLastName(resultSet.getString("lastname"));

                if(customersPurchases.containsKey(customer)){
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setCost(resultSet.getInt("cost"));

                    customersPurchases.get(customer).getPurchasesPerProducts().put(product, resultSet.getInt("sum"));

                }else{
                    Purchases customerPurchases = new Purchases();
                    customerPurchases.setCustomer(customer);

                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setCost(resultSet.getInt("cost"));

                    customerPurchases.getPurchasesPerProducts().put(product, resultSet.getInt("sum"));

                    customersPurchases.put(customer, customerPurchases);
                }
            }

            return customersPurchases;
        }
    }
}
