package ru.projects.test_task_aikamsoft.entity;

import java.util.Objects;

public class Customer {

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Customer customer = (Customer) obj;
        return (this.firstName.equals(customer.firstName) && this.lastName.equals(customer.lastName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
