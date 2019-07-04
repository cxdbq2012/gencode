package ${table.basePackageName}.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${table.basePackageName}.entity.${table.tableName};
import ${table.basePackageName}.param.*;
import ${table.basePackageName}.service.${table.tableName}Service;
import ${table.basePackageName}.utils.AppContext;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/${table.lowerCaseTableName}")
public class ${table.tableName}Controller {
    @Resource
    private ${table.tableName}Service service;

    @RequestMapping(value= "/search", method = RequestMethod.POST)
    public PageResponse<${table.tableName}> search(${table.tableName} dto,Integer pageIndex,Integer pageSize){

        IPage<${table.tableName}> res = service.search(dto,pageIndex,pageSize);
        return PageResponse.success(res.getRecords()).page(res);
    }

    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public Response add(${table.tableName} dto){
        service.add(dto);
        return Response.successWithoutData();
    }

    @RequestMapping(value= "/update", method = RequestMethod.POST)
    public Response update(${table.tableName} dto){
        int i = service.update(dto);
        return Response.successWithoutData();
    }
}