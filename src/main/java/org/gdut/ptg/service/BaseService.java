package org.gdut.ptg.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.gdut.ptg.dao.BaseMapper;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * <pre> 
 * 描述：M：model的类型
 * 	D：dao的类型
 * 构建组：trunk
 * 作者：guojh
 * 日期:2015-2-2-下午4:46:03
 * </pre>
 */
public class BaseService<M, D extends BaseMapper<M>> {
	
	private D dao;
	protected Class<D> daoClass;

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 初始化时，获取泛型D的真实Class
	 */
	public BaseService(){
		Type genericSuperclass = getClass().getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType){
			Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
			if(actualTypeArguments.length<2) return;
			daoClass =(Class<D>) actualTypeArguments[1];
		}
	}
	
	public D getDao(){
		if(dao==null) {
			dao = sqlSessionTemplate.getMapper(daoClass);
		}
		return dao;
	}
	
	/**
	 * 获取所有数据
	 * @return 
	 * List<M>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<M> getAll(){
		return getDao().getAll();
	}
	
	/**
	 * 根据ID获取实体
	 * TODO方法名称描述
	 * @param id
	 * @return 
	 * M
	 * @exception 
	 * @since  1.0.0
	 */
	public M get(Serializable id){
		return getDao().get(id);
	}
	
	/**
	 * 根据ID删除实体
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void del(Serializable id){
		getDao().del(id);
	}
	
	/**
	 * 更新实体
	 * @param model 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void update(M model){
		getDao().update(model);
	}
	
	/**
	 * 新增实体
	 * @param model 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void add(M model){
		getDao().add(model);
	}
}
