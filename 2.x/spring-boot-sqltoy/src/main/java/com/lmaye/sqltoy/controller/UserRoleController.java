package com.lmaye.sqltoy.controller;

import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.UserRoleDTO;
import com.lmaye.sqltoy.service.IUserRoleService;
import com.lmaye.sqltoy.vo.UserRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -- UserRoleController
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:48
 * @email lmay@lmaye.com
 * @since JDK17
 */
@RestController
@RequestMapping("/user/role")
@Api(tags = "用户角色相关接口")
public class UserRoleController {
    /**
     * IUserService
     */
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 新增记录
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增记录", notes = "新增记录")
    public ResultVO<Boolean> save(@RequestBody UserRoleDTO param) {
        return ResultVO.success(userRoleService.save(param));
    }

    /**
     * 分页查询记录
     *
     * @param param 请求参数
     * @return ResultVO<PageResult < UserRoleVO>>
     */
    @PostMapping("/search")
    @ApiOperation(value = "分页查询记录", notes = "分页查询记录")
    public ResultVO<PageResult<UserRoleVO>> search(@RequestBody PageQueryDTO param) {
        return ResultVO.success(userRoleService.search(param));
    }
}
