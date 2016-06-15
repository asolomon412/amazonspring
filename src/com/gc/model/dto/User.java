/**
 * 
 */
package com.gc.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Maurice Tedder
 * Data transfer object for users database table
 */
@XmlRootElement
public class User {
	private String username;
	private String password;
	private long userid;
	private UserRole userrole;	
	
	/**
	 * Default constructor
	 */
	public User(){
		
	}
		
	/**
	 * @param username
	 * @param password
	 * @param userid
	 * @param userrole
	 */
	public User(String username, String password, long userid, UserRole userrole) {
		super();
		this.username = username;
		this.password = password;
		this.userid = userid;
		this.userrole = userrole;
	}


	/**
	 * @return the userrole
	 */
	public UserRole getUserrole() {
		return userrole;
	}

	/**
	 * @param userrole the userrole to set
	 */
	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String formatedUser = "Username: " + this.getUsername() + "\nPassword:" + this.getPassword() + "\nRoleAbc: " + this.getUserrole()
		+ "\nUserID: " + this.getUserid();
		return formatedUser;
	}		
}
