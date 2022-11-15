package com.lmaye.sqltoy.service;

import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.SysUserDTO;
import com.lmaye.sqltoy.vo.SysUserVO;

/**
 * -- User Service
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:50
 * @email lmay@lmaye.com
 * @since JDK17
 */
public interface IUserService {
    /**
     * 新增记录
     *
     * @param dto 记录信息
     * @return Boolean
     */
    Boolean save(SysUserDTO dto);

    /**
     * 查询记录
     * - 分页
     *
     * @param dto 查询信息
     * @return PageResult<SysUserVO>
     */
    PageResult<SysUserVO> search(PageQueryDTO dto);
}
