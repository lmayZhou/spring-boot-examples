package ${package.Controller};

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
</#if>

/**
 * -- ${table.controllerName}
 *
 * @author ${author}
 * @date ${date}
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(tags = "${table.comment!}相关接口")
@RequestMapping("/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.name}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName}, ${entity}, ${entity}RestConverter, ${entity}VO, ${entity}DTO, Long> {
    <#else>
public class ${table.controllerName} {
    </#if>
}
</#if>
