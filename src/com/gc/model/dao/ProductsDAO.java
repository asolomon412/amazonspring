/**
 * 
 */
package com.gc.model.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.gc.model.dto.Product;

/**
 * @author Maurice Tedder Data Access Object for products table.
 *         Ref:http://www.javatpoint.com/hibernate-and-spring-integration -
 *         Topic 4 or we can use hibernate style
 *         SessionFactory.getCurrentSession() instead of the HibernateTemplate
 *         class.
 *
 */
public class ProductsDAO {

	HibernateTemplate template;
	DriverManagerDataSource dataSource;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	/**
	 * Initialize with the datasource so that we can use system environment
	 * variable to set the username and password and not have them hard
	 * coded. There may be a better way to do this in spring but this is my
	 * solution when using HibernateTemplate. I'll create another example using
	 * Hibernate Session as a branch of this example.
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DriverManagerDataSource dataSource) {		
		try {
			URI uri = new URI(System.getenv("DATABASE_URL"));
			String[] userInfo = uri.getUserInfo().split(":");//get username and password from uri string
			String username = userInfo[0];
			String password = userInfo[1];					
			String dbURL = "jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();// + "?ssl=true&amp;sslfactory=org.postgresql.ssl.NonValidatingFactory";
			//set datasource properties
			dataSource.setUsername(username);	
			dataSource.setPassword(password);
			//dataSource.setUrl(dbURL);
			//System.out.println("URI1:" + dbURL);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//dataSource.setPassword(password);
		this.dataSource = dataSource;		
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
		Product p = (Product) template.get(Product.class, productId);
		return p;
	}

}
