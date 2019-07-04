package com.yikemm.gencode;

public class Column {

		//表字段名
		private String columnTableName;
		private String getMethodName;
		// 名称
		private String columnName;
		// 类型
		private String columnType;
		// 长度
		private Integer columnSize;
		// 是否可为空
		private Integer columnNullable;
		// 默认值
		private String columnDefaultValue;
		// 备注
		private String columnRemark;
 
		public String getColumnName() {
			return columnName;
		}

	public String getGetMethodName() {
		return getMethodName;
	}

	public void setGetMethodName(String getMethodName) {
		this.getMethodName = getMethodName;
	}

	public String getColumnTableName() {
		return columnTableName;
	}

	public void setColumnTableName(String columnTableName) {
		this.columnTableName = columnTableName;
	}

	public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
 
		public String getColumnType() {
			return columnType;
		}
 
		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}
 
		public Integer getColumnSize() {
			return columnSize;
		}
 
		public void setColumnSize(Integer columnSize) {
			this.columnSize = columnSize;
		}
 
		public Integer getColumnNullable() {
			return columnNullable;
		}
 
		public void setColumnNullable(Integer columnNullable) {
			this.columnNullable = columnNullable;
		}
 
		public String getColumnDefaultValue() {
			return columnDefaultValue;
		}
 
		public void setColumnDefaultValue(String columnDefaultValue) {
			this.columnDefaultValue = columnDefaultValue;
		}
 
		public String getColumnRemark() {
			return columnRemark;
		}
 
		public void setColumnRemark(String columnRemark) {
			this.columnRemark = columnRemark;
		}
 
		@Override
		public String toString() {
			return "Column{" +
					"columnName='" + columnName + '\'' +
					", columnType='" + columnType + '\'' +
					", columnSize=" + columnSize +
					", columnNullable=" + columnNullable +
					", columnDefaultValue='" + columnDefaultValue + '\'' +
					", columnRemark='" + columnRemark + '\'' +
					'}';
		}
 
	}