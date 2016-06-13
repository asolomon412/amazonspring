/**
 * 
 */
package com.gc.model.dto;

/**
 * @author Maurice Tedder
 * Data transfer object for user_roles database table
 */
public class UserRole {
	private long userid;
	private String username;
	private String role;
	private User user;
	private float rating;
	/**
	 * Default constructor
	 */
	public UserRole() {
		// TODO Auto-generated constructor stub
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



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}		
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String formatedUserRole = "Username: " + this.getUsername() + "\nRole: " + this.getRole() +
				"\nRating: " + this.rating;
		return formatedUserRole;
	}

	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(float rating) {
		this.rating = rating;
	}	
}
