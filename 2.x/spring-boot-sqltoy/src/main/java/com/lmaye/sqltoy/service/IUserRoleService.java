package com.lmaye.sqltoy.service;

import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.UserRoleDTO;
import com.lmaye.sqltoy.vo.UserRoleVO;

/**
 * -- User Role Service
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:50
 * @email lmay@lmaye.com
 * @since JDK17
 */
public interface IUserRoleService {
    /**
     * 新增记录
     *
     * @param dto 记录信息
     * @return Boolean
     */
    Boolean save(UserRoleDTO dto);

    /**
     * 查询记录
     * - 分页
     *
     * @param dto 查询信息
     * @return PageResult<UserRoleVO>
     */
    PageResult<UserRoleVO> search(PageQueryDTO dto);
}
