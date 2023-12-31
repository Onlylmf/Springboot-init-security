package com.murphy.model.entity.sys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.murphy.common.validatgroup.AddGroup;
import com.murphy.common.validatgroup.FindGroup;
import com.murphy.common.validatgroup.UpdateGroup;
import com.murphy.constants.ContentConstant;
import com.murphy.utils.UUIdGenId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;
/**
 * 从 sys_user_role 表 自动生成的entity.
 */
@Table(name="sys_user_role")
@JsonInclude(JsonInclude.Include.NON_NULL) 
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     *   编号
     */
	@Id
	@KeySql(genId = UUIdGenId.class)
	@Null(groups = {AddGroup.class }, message = ContentConstant.ID_NOT_NULL)
	@NotNull(groups = {UpdateGroup.class, FindGroup.class }, message = ContentConstant.ID_NULL)
    @ApiModelProperty(value="编号  ")
	private String id;
	
	/**
     *   用户ID
     */
    @ApiModelProperty(value="用户ID  ")
	private String userId;
	
	/**
     *   角色ID
     */
    @ApiModelProperty(value="角色ID  ")
	private String roleId;
	


	
}