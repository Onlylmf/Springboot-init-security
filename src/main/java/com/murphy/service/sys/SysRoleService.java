package com.murphy.service.sys;
import java.util.HashMap;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.murphy.model.entity.sys.SysRole;
import com.murphy.service.BaseService;

import javax.management.relation.Role;


/**
 * 
 * @ClassName: SysRoleService 
 * @Description: 实现类 SysRoleServiceImpl
 * @date  2023-12-07 16:23:52
 */
public interface SysRoleService extends BaseService<SysRole,Long>{
	
    /**
	 *  CACHE_NAME     SysRole
	 */
	public final static String CACHE_NAME = "SysRole";
	
	/**
	 * 查询符合条件的 SysRole的个数
	 * @param condition 条件
	 * @return int count
	 * @throws Exception
	 */
	int countByCondition(HashMap<String, Object> condition) throws Exception;

	/**
	 * 删除符合条件的 SysRole
	 * @param condition 条件
	 * @return 成功删除的个数
	 * @throws Exception
	 */
	int deleteByCondition(HashMap<String, Object> condition) throws Exception;

	/**
	 * 根据用户id查询权限
	 */
	SysRole findRolesByUserId(String id);

	/**
	 * 根据主键删除 SysRole
	 * @param id
	 * @return 成功删除的个数
	 * @throws Exception
	 */
	int deleteByPrimaryKey(Long id) throws Exception;

	/**
	 * 保存 SysRole
	 * @param record
	 * @return  成功保存的个数
	 * @throws Exception
	 */
	int save(SysRole record) throws Exception;

	/**
	 * 查询所有符合条件的  SysRole
	 * @param condition 条件
	 * @return  List<SysRole>
	 * @throws Exception
	 */
	List<SysRole> selectAllByCondition(HashMap<String, Object> condition) throws Exception;

	/**
	 * 分页查询所有符合条件的  SysRole
	 * @param condition 条件 Integer pageNum 从1开始, Integer pageSize
	 * @return  PageInfo<SysRole>
	 * @throws Exception
	 */
	PageInfo<SysRole> selectPageByCondition(HashMap<String, Object> condition, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 查询所有符合条件的  第一个SysRole
	 * @param condition 条件
	 * @return  SysRole  record
	 * @throws Exception
	 */
	SysRole selectFirstByCondition(HashMap<String, Object> condition) throws Exception;

	/**
	 * 根据主键查询 SysRole
	 * @param id
	 * @return  SysRole  record
	 * @throws Exception
	 */
	SysRole selectByPrimaryKey(Long id) throws Exception;

	/**
	 * 将所有符合条件的SysRole 更新为SysRole  record
	 * @param condition
	 * @return  SysRole  record
	 * @throws Exception
	 */
	int updateByCondition(SysRole record, HashMap<String, Object> condition) throws Exception;

	/**
	 * 根据主键更新 SysRole
	 * @param record
	 * @return  更新成功的个数
	 * @throws Exception
	 */
	int updateByPrimaryKey(SysRole record) throws Exception;

	
	/**
	 * 删除所有主键在List<Long> ids的记录
	 * @param ids
	 * @return  更新成功的个数
	 * @throws Exception
	 */
	int deleteByPrimaryKeys(List<Long> ids) throws Exception;

}
