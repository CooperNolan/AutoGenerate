package ${package};

<#if Result??>
import ${Result};
</#if>
<#if BaseQueryVo??>
import ${BaseQueryVo};
</#if>
<#if dirType == 1>
import ${generatePackage}.domain.${javaName};
import ${generatePackage}.service.${javaName}Service;
</#if>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @name: ${javaName}Controller
* @description: ${javaName}Controller
* @author: ${author}
* @datetime: ${datetime}
**/
@RestController
@RequestMapping("${javaName_awl}")
public class ${javaName}Controller {

    @Resource
    private ${javaName}Service ${javaName_fwl}Service;

    @GetMapping
    public Result getEntry(@RequestBody BaseQueryVo queryVo) {
        PageHelper.startPage(queryVo.getPageNum(), queryVo.getPageSize());
        List<${javaName}> ${javaName_fwl}List = ${javaName_fwl}Service.selectByEntry(null);
        return new Result(new PageInfo<>(${javaName_fwl}List));
    }

    @GetMapping(value = "/{id}")
    public Result getEntryById(@PathVariable(value = "id") Long id) {
        return new Result(${javaName_fwl}Service.selectByKey(id));
    }

    @PostMapping
    public Result addEntry(@RequestBody ${javaName} ${javaName_fwl}) {
        ${javaName_fwl}Service.insertEntry(${javaName_fwl});
        return new Result(${javaName_fwl});
    }

    @PutMapping
    public Result setEntryById(@RequestBody User ${javaName_fwl}) {
        return new Result(${javaName_fwl}Service.updateByKey(${javaName_fwl}));
    }

    @DeleteMapping("/{id}")
    public Result delEntryById(@PathVariable(value = "id") Long id) {
        return new Result(${javaName_fwl}Service.deleteByKey(id));
    }

}