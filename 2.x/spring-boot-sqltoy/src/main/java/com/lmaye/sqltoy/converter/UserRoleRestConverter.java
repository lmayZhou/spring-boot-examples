package com.lmaye.sqltoy.converter;

import com.lmaye.cloud.starter.web.service.IRestConverter;
import com.lmaye.sqltoy.dto.UserRoleDTO;
import com.lmaye.sqltoy.entity.UserRole;
import com.lmaye.sqltoy.vo.UserRoleVO;
import org.mapstruct.Mapper;

/**
 * -- UserRoleRestConverter
 *
 * @author Lmay Zhou
 * @date 2022/11/14 16:51
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Mapper(componentModel = "spring")
public interface UserRoleRestConverter extends IRestConverter<UserRole, UserRoleVO, UserRoleDTO> {
}
