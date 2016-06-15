/**
 * 
 */
package com.gc.model.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gc.model.dto.Product;
import com.gc.model.dto.User;
import com.gc.model.dto.UserRole;

/**
 * @author Maurice Tedder
 * Data Access Object for user table.
 * or we can use hibernate style SessionFactory.getCurrentSession() instead of the
 * HibernateTemplate class.
 */
public class UserDAO {
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
			//System.out.println("URI3:" + dbURL);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//dataSource.setPassword(password);
		this.dataSource = dataSource;		
	}	
	
	/*
	 * Return a list of all users from the database
	 */
	public List<User> listUsers() {
		List<User> users = new ArrayList<User>();
		users = template.loadAll(User.class);		
		return users;
	}
	
	/**
	 * Return only user with the given user Id.
	 * @return ArrayList of User object.
	 * Ref: https://docs.jboss.org/hibernate/orm/3.5/reference/en-US/html/querycriteria.html
	 */
	public List<User> listByUserId(long userid) {
		List<User> users = new ArrayList<User>();
		DetachedCriteria query = DetachedCriteria.forClass(User.class)
			    .add( Property.forName("userid").eq(new Long(userid)));
	
		users = (List<User>) template.findByCriteria(query);		
		return users;
	}	
	
	
	/**
	 * Return only user with the role of seller.
	 * @return ArrayList of User object.
	 * Ref: https://docs.jboss.org/hibernate/orm/3.5/reference/en-US/html/querycriteria.html
	 * http://howtodoinjava.com/hibernate/hibernate-criteria-queries-tutorial-and-examples/
	 */
	public List<User> listSellers() {
		List<User> users = new ArrayList<User>();
		
		DetachedCriteria query = DetachedCriteria.forClass(User.class).createCriteria("userrole")
				.add(Restrictions.eq("role", "ROLE_SELLER"));
	
		users = (List<User>) template.findByCriteria(query);		
		return users;
	}
	
	/*
	 * Save user to DB.
	 * Ref: http://fruzenshtein.com/one-to-one-unidirectional-primary-key/
	 * https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/mapping.html#mapping-declaration-onetoone
	 */
	public void saveUser(User user) {					
		template.save(user);//save user	to DB
		UserRole userRole = user.getUserrole();
		userRole.setUserid(user.getUserid());
		userRole.setUser(user);
		template.save(userRole);//save UserRole to DB	
	}
	
	/**
	 * Update the rating for this user and average to previous value.
	 * @param userRole
	 */
	public void updatRating(UserRole userRole){
		UserRole temp = template.get(UserRole.class, userRole.getUserid());
		//Do not average if the current rating in zero (0)
		float avg = (temp.getRating() == 0)? userRole.getRating():(temp.getRating() + userRole.getRating())/2.0f;
		temp.setRating(avg);
		template.update(temp);
	}
}
