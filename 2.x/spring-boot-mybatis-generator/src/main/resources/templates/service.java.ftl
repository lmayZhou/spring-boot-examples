package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* -- ${table.serviceName}
*
* @author ${author}
* @date ${date}
* @email lmay@lmaye.com
* @since JDK1.8
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}, Long> {

}
</#if>
