package com.murphy.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.murphy.constants.ConfigConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murphy.utils.SpringContextUtils;
import org.springframework.cache.Cache;
import com.murphy.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import com.murphy.model.entity.sys.SysRole;
import com.murphy.mapper.sys.SysRoleMapper;
import com.murphy.service.sys.SysRoleService;





/**
* @date  2023-12-07 16:23:52
*/
@Service
public class SysRoleServiceImpl implements SysRoleService{
	
	
    @Autowired
	private SysRoleMapper sysRoleDao;

	@Override
	public List<SysRole> listUserRoles(String userId){
		return sysRoleDao.findRolesByUserId(userId);
	}
	
	@Override
	public int countByCondition(HashMap<String, Object> condition) throws Exception {
		Example example = createExample(condition);
		return  sysRoleDao.selectCountByExample(example);
	}

	@Override
	@CacheEvict(value=CACHE_NAME,allEntries=true)// 清空  缓存
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteByCondition(HashMap<String, Object> condition) throws Exception {
		Example example = createExample(condition);
		return  sysRoleDao.deleteByExample(example);
	}

	@Override
	public List<SysRole> findRolesByUserId(String id) {
		return sysRoleDao.findRolesByUserId(id);
	}

	@Override
	@CacheEvict(value =CACHE_NAME, key = "#id") // 清空 缓存
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteByPrimaryKey(Long id) throws Exception {
		
		return  sysRoleDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(SysRole record) throws Exception {
		
		return sysRoleDao.insertSelective(record);
	}

	@Override
	public List<SysRole> selectAllByCondition(HashMap<String, Object> condition) throws Exception {
		Example example = createExample(condition);

		String orderByClause = (String) condition.get("orderByClause");
		if(orderByClause!=null) {
			example.setOrderByClause(orderByClause);
		}
		return sysRoleDao.selectByExample(example);
	}

	@Override
	public PageInfo<SysRole> selectPageByCondition(HashMap<String, Object> condition, Integer pageNum, Integer pageSize)
			throws Exception {
		Example example = createExample(condition);

		PageHelper.startPage(pageNum, pageSize, true);
		String orderByClause = (String) condition.get("orderByClause");
		if(orderByClause!=null) {
			example.setOrderByClause(orderByClause);
		}
		List<SysRole> list = sysRoleDao.selectByExample(example);
		PageInfo<SysRole> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public SysRole selectFirstByCondition(HashMap<String, Object> condition) throws Exception {
		Example example = createExample(condition);
		String orderByClause = (String) condition.get("orderByClause");
		if(orderByClause!=null) {
			example.setOrderByClause(orderByClause);
		}
		PageHelper.startPage(1, 1, true);
		List<SysRole> list = sysRoleDao.selectByExample(example);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}else {
			return list.get(0);
		}
		
	}

	@Override
	@Cacheable(value =CACHE_NAME, key = "#id", sync=true) 
	public SysRole selectByPrimaryKey(Long id) throws Exception {
		return sysRoleDao.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value=CACHE_NAME,allEntries=true)// 清空  缓存
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateByCondition(SysRole record, HashMap<String, Object> condition) throws Exception {
		Example example = createExample(condition);
		return sysRoleDao.updateByExample(record, example);
	}

	@Override
	@CacheEvict(value =CACHE_NAME, key = "#record.id") // 清除 缓存
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateByPrimaryKey(SysRole record) throws Exception {
		return sysRoleDao.updateByPrimaryKeySelective(record);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteByPrimaryKeys(List<Long> ids) throws Exception {
		Example example =new Example(SysRole.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", ids);
		Cache cache = SpringContextUtils.getCache(CACHE_NAME);
		((List<Long>) ids).forEach((id) -> cache.evict(id));
		return sysRoleDao.deleteByExample(example);
	}

	private Example createExample(HashMap<String, Object> condition) {
		Example example = new Example(SysRole.class);
		Example.Criteria criteria = example.createCriteria();
		if (condition != null) {
			if (condition.get("id") != null && !"".equals(condition.get("id"))) {
				criteria.andEqualTo("id", condition.get("id"));
			}
			if (condition.get("delFlag") != null && !"".equals(condition.get("delFlag"))) {
				criteria.andEqualTo("delFlag", condition.get("delFlag"));
			}
			if (condition.get("startTime") != null ) {
				criteria.andGreaterThanOrEqualTo("create_time", DateUtils.parse(condition.get("startTime")+""));
				
			}
			if (condition.get("endTime") != null ) {
				criteria.andLessThanOrEqualTo("create_time", DateUtils.parse(condition.get("endTime")+""));
			}
		}
		return example;
	}

	
	

}
