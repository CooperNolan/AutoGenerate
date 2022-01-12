package ${package};

<#if dirType == 1>
import ${generatePackage}.domain.${javaName};
</#if>
<#if baseService??>
import ${baseService};
</#if>

/**
* @name: ${javaName}Service
* @description: ${javaName}Service
* @author: ${author}
* @datetime: ${datetime}
**/
public interface ${javaName}Service extends BaseService<${javaName}, ${primary.javaType}> {

}
