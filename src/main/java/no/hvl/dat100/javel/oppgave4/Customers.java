package no.hvl.dat100.javel.oppgave4;

import no.hvl.dat100.javel.oppgave3.Customer;

public class Customers {

    private Customer[] customers;

    // a) Complete constructor
    public Customers(int size) {

        this.customers = new Customer[size];

    }

    // b) count number of non-null references
    public int countNonNull() {


        int count = 0;

        for (Customer customer : customers) {
            if(customer != null){
                count++;
            }
        }

        return count;
    }

    // c) return reference to customer with given id (if exists)
    public Customer getCustomer(int customer_id) {

        for (Customer customer : customers) {
            if(customer.getCustomerId() == customer_id && customer != null){
                return customer;                
            }
        }

        return null;
    }

    // d) add a customer to the reference table
    public boolean addCustomer(Customer newCustomer) {

        for (int i = 0; i < this.customers.length; i++) {
            if(this.customers[i] == null){
                this.customers[i] = newCustomer;
                return true;
            }
        }

        return false;
    }

    // e) remove customer with given id from reference table
    public Customer removeCustomer(int customer_id) {

        Customer removedCustomer = null;
        
        for (int i = 0; i < this.customers.length; i++) {
            if(this.customers[i] == null){
                continue;
            }

            if(this.customers[i].getCustomerId() == customer_id){
                removedCustomer = this.customers[i];
                this.customers[i] = null;
            }            
        }

        return removedCustomer;
    }

    // f) return reference table with all customers
    public Customer[] getCustomers() {

        Customer[] customers = new Customer[this.countNonNull()];
        
        int newIndex = 0; //make this in order to not go out of bounds on the new array when finding nullpointers

        for (int i = 0; i < this.customers.length; i++) {
            if(this.customers[i] != null){
                customers[newIndex] = this.customers[i];
            } else {
                newIndex--;
            }
            newIndex++;
        }

        return customers;
    }
}