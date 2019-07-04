package ${table.basePackageName}.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${table.basePackageName}.dao.mapper.${table.tableName}Mapper;
import ${table.basePackageName}.entity.${table.tableName};
import ${table.basePackageName}.param.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ${table.tableName}Service {

    @Resource
    private ${table.tableName}Mapper mapper;

    public ${table.tableName} searchById(Integer id){
        return mapper.selectById(id);
    }

    public IPage<${table.tableName}> search(${table.tableName} dto,Integer pageIndex,Integer pageSize){
        QueryWrapper<${table.tableName}> qw = new QueryWrapper<>();

        <#list table.columns as column>
        if(dto.${column.getMethodName}() != null){
            qw.eq("${column.columnTableName}",dto.${column.getMethodName}());
        }

        </#list>

        IPage<${table.tableName}> pg = new Page<>(pageIndex,pageSize);
        return mapper.selectPage(pg,qw);
    }

    public int add(${table.tableName} dto){
        return mapper.insert(dto);
    }

    public int update(${table.tableName} dto){
        return mapper.updateById(dto);
    }

}
