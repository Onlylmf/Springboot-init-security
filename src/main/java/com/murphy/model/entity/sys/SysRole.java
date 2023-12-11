package com.murphy.model.entity.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.murphy.constants.ContentConstant;
import com.murphy.common.validatgroup.AddGroup;
import com.murphy.common.validatgroup.FindGroup;
import com.murphy.common.validatgroup.UpdateGroup;
import com.murphy.utils.UUIdGenId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.murphy.utils.MyIDGenId;
import tk.mybatis.mapper.annotation.KeySql;
/**
 * @date  2023-12-07 16:23:51
 * 从 sys_role 表 自动生成的entity.
 */
@Table(name="sys_role")
@JsonInclude(JsonInclude.Include.NON_NULL) 
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
     *   编号
     */
	@Id
	@KeySql(genId = UUIdGenId.class)
	@Null(groups = {AddGroup.class }, message = ContentConstant.ID_NOT_NULL)
	@NotNull(groups = {UpdateGroup.class,FindGroup.class }, message = ContentConstant.ID_NULL)
    @ApiModelProperty(value="编号  ")
	private Long id;
	
	
	/**
     *   角色名称
     */
	/** @NotNull(groups = {AddGroup.class }, message = "角色名称 不能为空") */ 
    @ApiModelProperty(value="角色名称  ")
	@NotNull(groups = {AddGroup.class }, message = "角色名称 不能为空")
	private String name;
	
	
	/**
     *   状态 0不启用 1启用
     */
	/** @NotNull(groups = {AddGroup.class }, message = "状态 0不启用 1启用 不能为空") */ 
    @ApiModelProperty(value="状态 0不启用 1启用  ")
	private Integer state;
	
	
	/**
     *   创建者
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建者 不能为空") */ 
    @ApiModelProperty(value="创建者  ")
	private Long createBy;
	
	
	/**
     *   创建时间
     */
	/** @NotNull(groups = {AddGroup.class }, message = "创建时间 不能为空") */ 
    @ApiModelProperty(value="创建时间  ")
	private Date createDate;
	
	
	/**
     *   备注信息
     */
	/** @NotNull(groups = {AddGroup.class }, message = "备注信息 不能为空") */ 
    @ApiModelProperty(value="备注信息  ")
	private String remarks;
	
	


	
}