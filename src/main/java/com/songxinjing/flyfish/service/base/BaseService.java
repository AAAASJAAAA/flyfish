package com.songxinjing.flyfish.service.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songxinjing.flyfish.dao.base.BaseDao;

/**
 * 业务服务对象基础实现类
 * 
 * @author songxinjing
 * 
 */
public abstract class BaseService<T, PK extends Serializable> {

	protected static Logger logger = LoggerFactory.getLogger(BaseService.class);

	protected BaseDao<T, PK> dao;

	/**
	 * 设置dao实例
	 * 
	 * @param dao
	 */
	protected void setDao(BaseDao<T, PK> dao) {
		this.dao = dao;
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 *            实体对象
	 * @return 主键值
	 */
	public Serializable save(final T entity) {
		return dao.save(entity);
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void update(final T entity) {
		dao.update(entity);
	}
	
	/**
	 * 新增或修改修改
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void saveOrUpdate(final T entity) {
		dao.saveOrUpdate(entity);
	}

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	public void delete(final T entity) {
		dao.delete(entity);
	}

	/**
	 * 删除一组对象
	 * 
	 * @param list
	 *            实体对象List
	 */
	public void delete(final Collection<T> entities) {
		dao.delete(entities);
	}

	/**
	 * 查询全部对象
	 * 
	 * @return 实体对象List
	 */
	public List<T> find() {
		return dao.find();
	}

	/**
	 * 通过主键ID查询对象.
	 * 
	 * @param id
	 *            主键
	 * @return 实体对象
	 */
	public T find(final PK id) {
		return dao.find(id);
	}

	/**
	 * 通过主键ID删除对象.
	 * 
	 * @param id
	 *            主键
	 */
	public void delete(final PK id) {
		T obj = find(id);
		if (obj != null) {
			delete(obj);
		}
	}

	/**
	 * HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List<?> findHql(final String hql) {
		return dao.findHql(hql);
	}
	
	/**
	 * HQL更新
	 * 
	 * @param hql
	 * @return
	 */
	public int updateHql(final String hql,final Map<String, Object> paraMap) {
		return dao.updateHql(hql, paraMap);
	}

	/**
	 * HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List<?> findHql(final String hql, final Map<String, Object> paraMap) {
		return dao.findHql(hql, paraMap);
	}
	

	/**
	 * 分页查询
	 * 
	 * @param from
	 * @param size
	 * @return
	 */
	public List<T> findPage(String hql, int from, int size) {
		return this.findPage(hql, from, size, new HashMap<String, Object>());
	}

	/**
	 * 分页查询
	 * 
	 * @param from
	 * @param size
	 * @return
	 */
	public List<?> findPage(String hql, int from, int size, Class<?> clazz) {
		return this.findPage(hql, from, size, null, clazz);
	}

	/**
	 * 分页查询
	 * 
	 * @param from
	 * @param size
	 * @return
	 */
	public List<T> findPage(String hql, int from, int size, final Map<String, Object> paraMap) {
		return dao.findPage(hql, from, size, paraMap);
	}

	/**
	 * 分页查询
	 * 
	 * @param from
	 * @param size
	 * @return
	 */
	public List<?> findPage(String hql, int from, int size, final Map<String, Object> paraMap, Class<?> clazz) {
		return dao.findPage(hql, from, size, paraMap, clazz);
	}

}
