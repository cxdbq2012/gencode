package com.yikemm;

import com.yikemm.gencode.Column;
import com.yikemm.gencode.Table;
import com.yikemm.utils.DatabaseUtil;
import com.yikemm.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.*;
import java.util.*;

/**
 * @goal gencode
 */
public class GencodeMojo extends AbstractMojo {

    /**
     * 生成代码文件时如果已经存在是否覆盖文件.
     * @parameter parameter="${override}"
     */
    private Boolean override = false;

    /**
     * name of the package.
     * @parameter parameter="${basedir}"
     *
     */
    private String baseDir;

    /**
     * name of the package.
     * @parameter parameter="${basePackageName}"
     * @required
     */
    private String basePackageName;

    /**
     * url of the database.
     * @parameter parameter="${dbHost}"
     * @required
     */
    private String dbHost;
    /**
     * url of the database.
     * @parameter parameter="${dbName}"
     * @required
     */
    private String dbName;

    /**
     * user of the the database.
     * @parameter parameter="${dbUser}"
     * @required
     */
    private String dbUser;

    /**
     * password of the the database.
     * @parameter parameter="${dbPwd}"
     * @required
     */
    private String dbPwd;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        DatabaseUtil.LOGGER = getLog();
        DatabaseUtil.init(dbHost,dbName,dbUser,dbPwd);

        getLog().info("baseDir="+baseDir);

        String basePackageName =  StringUtils.converPkg2Path(this.basePackageName);
        getLog().info("basePackagePath："+basePackageName+" basePackageName： "+ this.basePackageName);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        Template temp = null;
        Template mapperTemp = null;
        Template serviceTemp = null;
        Template controllerTemp = null;

        try {
            cfg.setClassForTemplateLoading(getClass(),"/template");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            temp = cfg.getTemplate("entity.ftl");
            mapperTemp = cfg.getTemplate("mapper.ftl");
            serviceTemp = cfg.getTemplate("service.ftl");
            controllerTemp = cfg.getTemplate("controller.ftl");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> root = new HashMap<>();

        List<String> tables = DatabaseUtil.getTableNames();
        Date date = new Date();
        for(String tb : tables) {
            root.clear();

            List<String> columnNames = DatabaseUtil.getColumnNames(tb);
            List<String> ccs = DatabaseUtil.getColumnComments(tb);
            List< String> types = DatabaseUtil.getColumnTypes(tb);

            Table table = new Table();
            table.setBasePackageName(this.basePackageName);
            table.setTableName( StringUtils.firstChar2Up( StringUtils.changeToJavaFiled(tb)) );
            List<Column> columns = new ArrayList<>();
            table.setColumns(columns);
            for(int i= 0;i<columnNames.size();i++){
                Column column = new Column();
                column.setColumnTableName(columnNames.get(i));
                column.setGetMethodName("get"+StringUtils.firstChar2Up( StringUtils.changeToJavaFiled(  columnNames.get(i) )  ));
                column.setColumnName( StringUtils.changeToJavaFiled(  columnNames.get(i) ) );
                column.setColumnRemark( StringUtils.trim( ccs.get(i) ) );
                column.setColumnType( StringUtils.typeConvert( types.get(i)  )  );
                columns.add(column);
            }

            root.put("table",table);

            try {
                File f = new File(baseDir+"/src/main/java/"+basePackageName+"/entity/");
                f.mkdirs();
                f = new File(baseDir+"/src/main/java/"+basePackageName+"/entity/"+table.getTableName()+".java");
                f = checkFile(basePackageName, date, table, f);
                FileWriter out = new FileWriter(f);
                temp.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File f = new File(baseDir+"/src/main/java/"+basePackageName+"/dao/mapper/");
                f.mkdirs();
                f = new File(baseDir+"/src/main/java/"+basePackageName+"/dao/mapper/"+table.getTableName()+"Mapper.java");
                f = checkFile(basePackageName, date, table, f);
                FileWriter out = new FileWriter(f);

                mapperTemp.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File f = new File(baseDir+"/src/main/java/"+basePackageName+"/service/");
                f.mkdirs();
                f = new File(baseDir+"/src/main/java/"+basePackageName+"/service/"+table.getTableName()+"Service.java");
                f = checkFile(basePackageName, date, table, f);
                FileWriter out = new FileWriter(f);

                serviceTemp.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                File f = new File(baseDir+"/src/main/java/"+basePackageName+"/controller/");
                f.mkdirs();
                f = new File(baseDir+"/src/main/java/"+basePackageName+"/controller/"+table.getTableName()+"Controller.java");
                f = checkFile(basePackageName, date, table, f);
                FileWriter out = new FileWriter(f);

                controllerTemp.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DatabaseUtil.closeConnection();
    }

    private File checkFile(String basePackageName, Date date, Table table, File f) {
        if(f.exists()){
            if(!override){
                f = new File(baseDir+"/src/main/java/"+basePackageName+"/entity/"+table.getTableName()+".java."+date.getTime());
                getLog().info("==>源文件已经存在，生成别名:"+f.getAbsolutePath());
            } else {
                getLog().info("==>源文件已经存在，覆盖原文件:"+f.getAbsolutePath());
            }
        }else{
            getLog().info("==>生成源文件:"+f.getAbsolutePath());
        }
        return f;
    }
}
