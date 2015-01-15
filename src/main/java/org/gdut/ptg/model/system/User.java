package org.gdut.ptg.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 对象功能:ptg_user Model对象
 * 开发人员:guojh
 * 创建时间:2015-01-15 13:42:35
 */
public class User{
	// ID_
	protected String  id;
	// USER_NAME
	protected String  userName;
	// PASSWORD
	protected String  password;
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 返回 ID_
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 返回 USER_NAME
	 * @return
	 */
	public String getUserName() {
		return this.userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 返回 PASSWORD
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		User rhs = (User) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userName, rhs.userName)
		.append(this.password, rhs.password)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userName) 
		.append(this.password) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userName", this.userName) 
		.append("password", this.password) 
		.toString();
	}
   
  

}