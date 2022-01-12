package ${package};

<#if dirType == 1>
import ${generatePackage}.domain.${javaName};
</#if>
<#if baseMapper??>
import ${baseMapper};
</#if>

/**
* @name: ${javaName}Mapper
* @description: ${javaName}Mapper
* @author: ${author}
* @datetime: ${datetime}
**/
public interface ${javaName}Mapper extends BaseMapper<${javaName}, ${primary.javaType}> {

}
