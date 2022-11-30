package com.lmaye.sqltoy.controller;

import com.lmaye.cloud.starter.web.context.PageResult;
import com.lmaye.cloud.starter.web.context.ResultVO;
import com.lmaye.sqltoy.dto.PageQueryDTO;
import com.lmaye.sqltoy.dto.SysUserDTO;
import com.lmaye.sqltoy.service.IUserService;
import com.lmaye.sqltoy.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 新增记录
     *
     * @param param 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增记录", notes = "新增记录")
    public ResultVO<Boolean> save(@RequestBody SysUserDTO param) {
        return ResultVO.success(userService.save(param));
    }

    /**
     * 查询记录
     *
     * @param id 主键ID
     * @return ResultVO<SysUserVO>
     */
    @GetMapping("/info/{id}")
    @ApiOperation("查询记录")
    public ResultVO<SysUserVO> userInfo(@PathVariable @ApiParam(value = "主键ID", required = true) Long id) {
        return ResultVO.success(userService.getUserInfo(id));
    }

    /**
     * 分页查询记录
     *
     * @param param 请求参数
     * @return ResultVO<PageResult < SysUserVO>>
     */
    @PostMapping("/search")
    @ApiOperation(value = "分页查询记录", notes = "分页查询记录")
    public ResultVO<PageResult<SysUserVO>> search(@RequestBody PageQueryDTO param) {
        return ResultVO.success(userService.search(param));
    }
}
