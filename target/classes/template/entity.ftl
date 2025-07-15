package ${table.basePackageName}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class ${table.tableName} {

<#list table.columns as column>

<#if column.columnName == 'id'>
    <#if column.columnRemark != ''>
    /**
     *  ${column.columnRemark}
     */
    </#if>
    @TableId(type = IdType.AUTO)
    private ${column.columnType} ${column.columnName};
<#else>
    <#if column.columnRemark != ''>
    /**
     *  ${column.columnRemark}
     */
    </#if>
    private ${column.columnType} ${column.columnName};
</#if>

<#else>

</#list>

}
