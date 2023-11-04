package ${package.Entity};

<#list table.importPackages as pkg>
    import ${pkg};
</#list>
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* -- ${entity}DTO
*
* @author ${author}
* @date ${date}
* @email lmay@lmaye.com
* @since JDK1.8
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "${entity}DTO", description = "${table.comment!}")
public class ${entity}DTO implements Serializable {
    private static final long serialVersionUID = 1L;

<#list table.fields as field>
    @ApiModelProperty("${field.comment}")
    private ${field.propertyType} ${field.propertyName};
</#list>
}
