package ${package.Entity};

import org.mapstruct.Mapper;
import com.lmaye.cloud.starter.web.service.IRestConverter;
import ${package.Entity}.${entity};

/**
* -- ${entity}RestConverter
*
* @author ${author}
* @date ${date}
* @email lmay@lmaye.com
* @since JDK1.8
*/
@Mapper(componentModel = "spring")
public interface ${entity}RestConverter extends IRestConverter<${entity}, ${entity}VO, ${entity}DTO> {
}
