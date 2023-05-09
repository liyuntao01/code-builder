package ${classInfo.prefix}.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import ${classInfo.prefix}.${classInfo.appName}.convert.${classInfo.className}ConvertMappers;
import ${classInfo.prefix}.${classInfo.appName}.req.${classInfo.className}DTO;
import ${classInfo.prefix}.${classInfo.appName}.vo.${classInfo.className}VO;

import ${classInfo.prefix}.${classInfo.appName}.entity.${classInfo.className}Bean;
import ${classInfo.prefix}.${classInfo.appName}.service.${classInfo.className}Service;


<#if classInfo.checkFields?exists && classInfo.checkFields?keys?size gt 0>
     <#assign check = true />
</#if>
/**
 * ${classInfo.classComment}
 *
 * @author  ${classInfo.author} on '
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}'
 */
@Api(value = "${classInfo.classComment}接口", tags = {"${classInfo.classComment}API"})
@RestController
@RequestMapping("/${classInfo.appName}/${classInfo.slashName}")
public class ${classInfo.className}Controller {

    @Autowired
    private ${classInfo.className}Service ${classInfo.className?uncap_first}Service;

    @ApiOperation("新建${classInfo.classComment}")
    @PostMapping
    public ${classInfo.className}VO create${classInfo.className}(@RequestBody ${classInfo.className}DTO ${classInfo.className?uncap_first}DTO){

        ${classInfo.className}Bean ${classInfo.className?uncap_first} = ${classInfo.className}ConvertMappers.MAPPER.convertBean(${classInfo.className?uncap_first}DTO);
        ${classInfo.className?uncap_first}Service.save${classInfo.className}(${classInfo.className?uncap_first});
        return ${classInfo.className}ConvertMappers.MAPPER.convertVO(${classInfo.className?uncap_first});

    }


    @ApiOperation("修改${classInfo.classComment}")
    @PutMapping
    public void update${classInfo.className}(@RequestBody ${classInfo.className}DTO ${classInfo.className?uncap_first}DTO){

        ${classInfo.className}Bean ${classInfo.className?uncap_first} = ${classInfo.className}ConvertMappers.MAPPER.convertBean(${classInfo.className?uncap_first}DTO);
        ${classInfo.className?uncap_first}Service.update${classInfo.className}ById(${classInfo.className?uncap_first});

    }

    @ApiOperation("删除${classInfo.classComment}")
    @DeleteMapping("/{id}")
    public void delete${classInfo.className}(@PathVariable("id") @NotNull(message = "id不能为空") Long id){

        ${classInfo.className?uncap_first}Service.removeById(id);

    }

    @ApiOperation("获取${classInfo.classComment}")
    @GetMapping("/{id}")
    public ${classInfo.className}VO get${classInfo.className}ById(@PathVariable("id") @NotNull(message = "id不能为空") Long id){
        ${classInfo.className}Bean ${classInfo.className?uncap_first} = ${classInfo.className?uncap_first}Service.getById(id);
        return ${classInfo.className}ConvertMappers.MAPPER.convertVO(${classInfo.className?uncap_first});
    }

   <#-- @ApiOperation("分页获取${classInfo.tableDescription}")
    @PostMapping("/page")
    public Page<${classInfo.className}VO> pageList(@RequestBody MpPager<${classInfo.className}DTO> mpPager) {

        MpPager<${classInfo.className}> newPage = ${classInfo.className}ConvertMappers.MAPPER.convertPage(mpPager);
        MyQueryWrapper<${classInfo.className}> myQueryWrapper = MpUtil.convertObjectToMP(newPage);
        Page<${classInfo.className}> page = ${classInfo.className?uncap_first}Service.page(myQueryWrapper.getPage(), myQueryWrapper.getQueryWrapper());
        return ${classInfo.className}ConvertMappers.MAPPER.convertPageVO(page);

    }

    @ApiOperation("查看所有${classInfo.tableDescription}")
    @PostMapping("/list")
    public List<${classInfo.className}VO> list(@RequestBody MpAll<${classInfo.className}DTO> mpAll) {

        MpAll<${classInfo.className}> newAll = ${classInfo.className}ConvertMappers.MAPPER.convertAll(mpAll);
        MyQueryWrapper<${classInfo.className}> myQueryWrapper = MpUtil.convertObjectToMP(newAll);
        List<${classInfo.className}> ${classInfo.className?uncap_first}List = ${classInfo.className?uncap_first}Service.list(myQueryWrapper.getQueryWrapper());
        return ${classInfo.className}ConvertMappers.MAPPER.convertVOList(${classInfo.className?uncap_first}List);

    }-->

}
