package org.gdut.ptg.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 对象功能:ptg_role Model对象
 * 开发人员:guojh
 * 创建时间:2015-01-15 13:42:35
 */
public class SysRole{
	// ID_
	protected String  id;
	protected String  roleName;
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
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 返回 USER_NAME
	 * @return
	 */
	public String getRoleName() {
		return this.roleName;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysRole)) {
			return false;
		}
		SysRole rhs = (SysRole) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.roleName, rhs.roleName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.roleName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("roleName", this.roleName) 
		.toString();
	}
   
  

}