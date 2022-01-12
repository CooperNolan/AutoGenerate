package ${package};

<#if dirType == 1>
import ${generatePackage}.dao.${javaName}Mapper;
import ${generatePackage}.domain.${javaName};
import ${generatePackage}.service.${javaName}Service;
</#if>
<#if baseMapper??>
import ${baseMapper};
</#if>
<#if baseServiceImpl??>
import ${baseServiceImpl};
</#if>
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* @name: ${javaName}ServiceImpl
* @description: ${javaName}ServiceImpl
* @author: ${author}
* @datetime: ${datetime}
**/
@Service("${javaName_fwl}Service")
public class ${javaName}ServiceImpl extends BaseServiceImpl<${javaName}, ${primary.javaType}> implements ${javaName}Service {

    @Resource
    private ${javaName}Mapper ${javaName_fwl}Mapper;

    @Override
    public BaseMapper<${javaName}, ${primary.javaType}> getMapper() {
        return this.${javaName_fwl}Mapper;
    }

}
