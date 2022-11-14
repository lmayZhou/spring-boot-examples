package com.lmaye.sqltoy.converter;

import com.lmaye.cloud.starter.web.service.IRestConverter;
import com.lmaye.sqltoy.dto.SysUserDTO;
import com.lmaye.sqltoy.entity.SysUser;
import com.lmaye.sqltoy.vo.SysUserVO;
import org.mapstruct.Mapper;

/**
 * --
 *
 * @author Lmay Zhou
 * @date 2022/11/14 16:51
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Mapper(componentModel = "spring")
public interface SysUserRestConverter extends IRestConverter<SysUser, SysUserVO, SysUserDTO> {
}
