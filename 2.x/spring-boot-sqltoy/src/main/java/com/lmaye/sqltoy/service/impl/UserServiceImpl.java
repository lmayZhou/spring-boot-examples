package com.lmaye.sqltoy.service.impl;

import com.lmaye.cloud.core.utils.IdUtils;
import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.service.impl.RestConverterImpl;
import com.lmaye.sqltoy.converter.SysUserRestConverter;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.SysUserDTO;
import com.lmaye.sqltoy.entity.SysUser;
import com.lmaye.sqltoy.service.IUserService;
import com.lmaye.sqltoy.vo.SysUserVO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * -- UserServiceImpl
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:50
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Service
public class UserServiceImpl extends RestConverterImpl<SysUserRestConverter, SysUser, SysUserVO, SysUserDTO>
        implements IUserService {
    /**
     * SqlToyLazyDao
     */
    @Autowired
    private SqlToyLazyDao sqlToyLazyDao;

    /**
     * 新增记录
     *
     * @param dto 记录信息
     * @return Boolean
     */
    @Override
    public Boolean save(SysUserDTO dto) {
        SysUser user = restConverter.convertDtoToEntity(dto);
        user.setId(IdUtils.nextId());
        return Objects.nonNull(sqlToyLazyDao.save(user));
    }

    /**
     * 查询记录
     * - 分页
     *
     * @param dto 查询信息
     * @return PageResult<SysUserVO>
     */
    @Override
    public PageResult<SysUserVO> search(PageQueryDTO dto) {
        Page<SysUser> page = new Page<>(dto.getPageSize().intValue(), dto.getPageIndex());
        SysUser query = new SysUser();
        query.setUserName(dto.getUserName());
        Page<SysUser> pageInfo = sqlToyLazyDao.findPageBySql(page, "searchPage", query);
        PageResult<SysUserVO> rs = new PageResult<>();
        rs.setPageIndex(dto.getPageIndex());
        rs.setPageSize(dto.getPageSize());
        rs.setPages(pageInfo.getTotalPage());
        rs.setTotal(pageInfo.getRecordCount());
        rs.setRecords(pageInfo.getRows().stream().map(restConverter::convertEntityToVo).collect(Collectors.toList()));
        return rs;
    }
}
