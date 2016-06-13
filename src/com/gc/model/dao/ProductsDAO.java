/**
 * 
 */
package com.gc.model.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.gc.model.dto.Product;

/**
 * @author Maurice Tedder
 * Data Access Object for products table.
 * Ref:http://www.javatpoint.com/hibernate-and-spring-integration - Topic 4
 *
 */
public class ProductsDAO {

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {		
		this.template = template;
	}

	/*
	 * Return a list of products from the database
	 */
	public List<Product> productList() {
		List<Product> list = new ArrayList<Product>();
		list = template.loadAll(Product.class);
		return list;
	}

	/*
	 * Save product to DB.
	 */
	public void saveProduct(Product product) {		
		template.save(product);
	}
	
	/*
	 * Method to return one Product with the given product id  
	 */
	public Product getById(long productId) {
		Product p=(Product)template.get(Product.class, productId);
		return p;
	}

}
