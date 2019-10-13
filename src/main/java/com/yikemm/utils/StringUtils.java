package com.yikemm.utils;

public class StringUtils {

    public static String converPkg2Path(String pkg){
        String[]pkgArray = pkg.split("\\.");
        return org.apache.commons.lang3.StringUtils.join(pkgArray,"/");
    }

    public static String typeConvert(String dbType){
        switch (dbType){
            case "TINYINT":return "Boolean";
            case "INT":return "Integer";
            case "CHAR":return "String";
            case "VARCHAR":return "String";
            case "TIMESTAMP":return "Date";
            case "DECIMAL":return "Double";
            case "BIGINT":return "Long";
            case "DOUBLE":return "Double";
            case "BLOB":return "Byte[]";
            default:return "Unknown_"+dbType;
        }
    }

    public static String changeToJavaFiled(String field){
        field = field.toLowerCase();
        String[] fields = field.split("_");
        StringBuilder sbuilder = new StringBuilder(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            char[] cs=fields[i].toCharArray();
            cs[0]-=32;
            sbuilder.append(String.valueOf(cs));
        }
        return sbuilder.toString();
    }

    public static String firstChar2Up(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

    public static void main(String[]args){
        System.out.println(
        //firstChar2Up("asdfSadsf")
        converPkg2Path("com.asdf.adsf.asdfga.asdg.gaa")
        );
    }

    public static String trim(String s) {
        if(s == null)
            return null;
        return s.trim();
    }
}
