package com.lmaye.sqltoy.service.impl;

import com.lmaye.cloud.core.utils.IdUtils;
import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.service.impl.RestConverterImpl;
import com.lmaye.sqltoy.converter.UserRoleRestConverter;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.UserRoleDTO;
import com.lmaye.sqltoy.entity.UserRole;
import com.lmaye.sqltoy.service.IUserRoleService;
import com.lmaye.sqltoy.vo.UserRoleVO;
import org.sagacity.sqltoy.dao.SqlToyLazyDao;
import org.sagacity.sqltoy.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * -- UserRoleServiceImpl
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:50
 * @email lmay@lmaye.com
 * @since JDK17
 */
@Service
public class UserRoleServiceImpl extends RestConverterImpl<UserRoleRestConverter, UserRole, UserRoleVO, UserRoleDTO>
        implements IUserRoleService {
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
    public Boolean save(UserRoleDTO dto) {
        UserRole user = restConverter.convertDtoToEntity(dto);
        user.setId(IdUtils.nextId());
        return Objects.nonNull(sqlToyLazyDao.save(user));
    }

    /**
     * 查询记录
     * - 分页
     *
     * @param dto 查询信息
     * @return PageResult<UserRoleVO>
     */
    @Override
    public PageResult<UserRoleVO> search(PageQueryDTO dto) {
        Page<UserRole> page = new Page<>(dto.getPageSize().intValue(), dto.getPageIndex());
        UserRole query = new UserRole();
        query.setUserId(dto.getUserId());
        query.setRoleIds(dto.getRoleIds());
        query.setStartDate(dto.getStartDate());
        query.setEndDate(dto.getEndDate());
        Page<UserRole> pageInfo = sqlToyLazyDao.findPageBySql(page, "searchPage", query);
        PageResult<UserRoleVO> rs = new PageResult<>();
        rs.setPageIndex(dto.getPageIndex());
        rs.setPageSize(dto.getPageSize());
        rs.setPages(pageInfo.getTotalPage());
        rs.setTotal(pageInfo.getRecordCount());
        rs.setRecords(pageInfo.getRows().stream().map(restConverter::convertEntityToVo).collect(Collectors.toList()));
        return rs;
    }
}
