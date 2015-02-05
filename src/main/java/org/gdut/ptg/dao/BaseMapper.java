package org.gdut.ptg.dao;

import java.io.Serializable;
import java.util.List;

/**
 * <pre> 
 * 描述：基础接口，提供通用方法接口
 * 构建组：trunk
 * 作者：guojh
 * 日期:2015-2-2-下午4:49:51
 * </pre>
 */
public interface BaseMapper<M> {
	
	/**
	 * 获取所有数据
	 * @return 
	 * List<M>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<M> getAll();
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return 
	 * M
	 * @exception 
	 * @since  1.0.0
	 */
	public M get(Serializable id);
	
	/**
	 * 根据ID删除实体
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void del(Serializable id);
	
	/**
	 * 更新实体
	 * @param model 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void update(M model);
	
	/**
	 * 新增实体
	 * @param model 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void add(M model);
}
