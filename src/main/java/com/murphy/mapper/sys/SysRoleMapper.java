package com.murphy.mapper.sys;
import com.murphy.model.entity.sys.SysRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @date  2023-12-07 16:23:52
 */
@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole>{


    @Select("select r.* from sys_user_role ru inner join sys_role r on r.id = ru.role_id where ru.user_id = #{userId}")
    SysRole findRolesByUserId(String userId);
}