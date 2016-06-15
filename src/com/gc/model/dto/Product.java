/**
 * 
 */
package com.gc.model.dto;

/**
 * @author Maurice Tedder Product bean for product table
 *
 */
public class Product {
	public long productid;
	public String name;
	public String description;
	public String price;
	public long userid;

	/**
	 * Default constructor
	 */
	public Product() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor	
	 * @param name
	 * @param description
	 * @param price
	 * @param userid
	 */
	public Product(String name, String description, String price, long userid) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.userid = userid;
	}

	
	/**
	 * Parameterized constructor
	 * @param productid
	 * @param name
	 * @param description
	 * @param price
	 * @param userid
	 */
	public Product(String name, String description, String price, long userid, long productid) {
		super();
		this.productid = productid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.userid = userid;
	}

	/**
	 * @return the productid
	 */
	public long getProductid() {
		return productid;
	}

	/**
	 * @param productid
	 *            the productid to set
	 */
	public void setProductid(long productid) {
		this.productid = productid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String formatedProduct = "Product productId: " + this.getProductid() + "\nName: " + this.getName()
				+ "\nDescription: " + this.getDescription() + "\nPrice: " + this.getPrice();
		return formatedProduct;
	}

	/**
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
