package com.lmaye.sqltoy.controller;

import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.SysUserDTO;
import com.lmaye.sqltoy.service.IUserService;
import com.lmaye.sqltoy.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -- User Controller
 *
 * @author Lmay Zhou
 * @date 2022/11/14 15:48
 * @email lmay@lmaye.com
 * @since JDK17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {
    /**
     * IUserService
     */
    @Autowired
    private IUserService userService;

    /**
     * 用户新增
     *
     * @param param 请求参数
     * @return ResultVO<SysUserVO>
     */
    @PostMapping("/save")
    @ApiOperation(value = "用户新增", notes = "新增用户信息")
    public ResultVO<SysUserVO> save(@RequestBody SysUserDTO param) {
        return ResultVO.success(userService.save(param));
    }

    /**
     * 用户查询
     *
     * @param param 请求参数
     * @return ResultVO<PageResult < SysUserVO>>
     */
    @PostMapping("/search")
    @ApiOperation(value = "用户查询", notes = "分页查询用户信息")
    public ResultVO<PageResult<SysUserVO>> search(@RequestBody PageQueryDTO param) {
        return ResultVO.success(userService.search(param));
    }
}
