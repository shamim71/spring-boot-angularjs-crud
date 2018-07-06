package com.acme.product.domain;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="sales_order")
public class SalesOrder {

    @Id
    @GeneratedValue(generator = "sales_order_generator")
    @SequenceGenerator(
            name = "sales_order_generator",
            sequenceName = "sales_order_sequence",
            initialValue = 1000
    )
    
    private Long id;
    
    private BigInteger price;
    
    
	@ManyToOne (targetEntity=Customer.class, optional=false)
	@JoinColumn(name = "customer_id",referencedColumnName="id")	
	private Customer customer;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigInteger getPrice() {
		return price;
	}


	public void setPrice(BigInteger price) {
		this.price = price;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
}
