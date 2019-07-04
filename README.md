# gencode
maven 插件根据表结构生产实体dao、service、controller 增删改查 restfule接口
```
            <plugin>
                <groupId>com.yikemm</groupId>
                <artifactId>gencode-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <baseDir>${project.basedir}</baseDir>
                    <dbHost>localhost</dbHost>
                    <dbName>test</dbName>
                    <dbUser>root</dbUser>
                    <dbPwd>123456</dbPwd>
                    <basePackageName>com.example.demo</basePackageName>
                </configuration>
            </plugin>
```
