package com.yikemm.gencode;

import java.util.ArrayList;
import java.util.List;

public class Table {
 
	// 表名
	private String tableName;

	private String basePackageName;
	// 备注
	private String tableRemark;
 
	// 列
	private List<Column> columns = new ArrayList<>();
	
	public String getTableName() {
		return tableName;
	}
	public String getLowerCaseTableName() {
		return tableName.toLowerCase();
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
 
	public String getTableRemark() {
		return tableRemark;
	}
 
	public void setTableRemark(String tableRemark) {
		this.tableRemark = tableRemark;
	}
 
	public List<Column> getColumns() {
		return columns;
	}
 
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	@Override
	public String toString() {
		return "Table{" +
				"tableName='" + tableName + '\'' +
				", tableRemark='" + tableRemark + '\'' +
				", columns=" + columns +
				'}';
	}
}
