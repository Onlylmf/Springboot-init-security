package com.murphy.controller.sys;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.murphy.constants.ContentConstant;
import com.murphy.common.validatgroup.AddGroup;
import com.murphy.common.validatgroup.UpdateGroup;
import com.murphy.model.common.Result;
import com.murphy.model.entity.sys.SysRole;
import com.murphy.service.sys.SysRoleService;
import io.swagger.annotations.ApiOperation;

/**
* @date  2023-12-07 16:23:52
*/
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

	@Autowired
	SysRoleService sysRoleService;

	@GetMapping("/findById/{id}")
	@ApiOperation(value = "根据id查找", notes = "/sysRole/findById/1")
	public SysRole findById(@PathVariable Long id) throws Exception {
		SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
		return sysRole;
	}

	@PostMapping("/findAll")
	@ApiOperation(value = "根据条件查找所有", notes = "条件 {'属性':'value'}")
	public List<SysRole> findAll(@RequestBody HashMap<String, Object> condition) throws Exception {
		List<SysRole> list = sysRoleService.selectAllByCondition(condition);
		return list;
	}
	
	
	@PostMapping("/findPage/{page}/{size}")
	@ApiOperation(value = "根据条件分页查找", notes = "条件 {'属性':'value'}")
	public PageInfo<SysRole> findPage(@RequestBody HashMap<String, Object> condition,@PathVariable  int page, @PathVariable  int size) throws Exception {
		PageInfo<SysRole> pageInfo = sysRoleService.selectPageByCondition(condition, page, size);
		return pageInfo;
	}
	
	@PostMapping("/add")
	@ApiOperation(value = "添加sysRole", notes = "添加sysRole")
	public Result add(@RequestBody @Validated(value = { AddGroup.class }) SysRole sysRole) throws Exception {
		sysRoleService.save(sysRole);
		Result ok = Result.ok(ContentConstant.ADD_SUCCESS);
		ok.put("record", sysRole);
		return ok;
	}
	
	@PostMapping("/update")
	@ApiOperation(value = "更新sysRole", notes = "更新sysRole")
	public Result update(@RequestBody @Validated(value = { UpdateGroup.class }) SysRole sysRole) throws Exception {
		sysRoleService.updateByPrimaryKey(sysRole);
		Result ok = Result.ok(ContentConstant.UPDATE_SUCCESS);
		ok.put("record", sysRole);
		return ok;
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ApiOperation(value = "根据主键删除sysRole", notes = "根据主键删除sysRole ")
	public Result deleteById(@PathVariable Long id) throws Exception {
		SysRole delete = new SysRole().setId(id);
		sysRoleService.updateByPrimaryKey(delete);
		Result ok = Result.ok(ContentConstant.DELETE_SUCCESS);
		return ok;
	}
	
	@DeleteMapping("/realDeleteById/{id}")
	@ApiOperation(value = "根据主键删除sysRole", notes = "根据主键删除sysRole ")
	public Result realDeleteById(@PathVariable Long id) throws Exception {
		int count = sysRoleService.deleteByPrimaryKey(id);
		Result ok = Result.ok(ContentConstant.DELETE_SUCCESS);
		ok.put("count", count);
		return ok;
	}
	
	@PostMapping("/deleteByIds")
	@ApiOperation(value = "根据主键删除sysRole", notes = "根据主键删除sysRole [111,222,333]")
	public Result deleteByIds(@RequestBody List<Long> ids) throws Exception {
		int count = sysRoleService.deleteByPrimaryKeys(ids);
		Result ok = Result.ok(ContentConstant.DELETE_SUCCESS);
		ok.put("count", count);
		return ok;
	}
	
}
