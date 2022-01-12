package ${package};

/**
 * @name: ${javaName}
 * @description: ${dbRemark!}
 * @author: ${author}
 * @datetime: ${datetime}
 **/
public class ${javaName} {

<#list fieldInfoList as item >
    private ${item.javaType} ${item.javaName}; // ${item.dbRemark!}
</#list>

<#list fieldInfoList as item >
    public ${item.javaType} get${item.javaName_fwc}() {
        return ${item.javaName};
    }

    public void set${item.javaName_fwc}(${item.javaType} ${item.javaName}) {
        this.${item.javaName} = ${item.javaName};
    }

</#list>

}
