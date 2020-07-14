```
自定义maven插件
```
```
引入方式
<plugin>
    <groupId>org.example</groupId>
    <artifactId>self-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
        <execution>
            <phase>initialize</phase>
            <goals>
                <goal>countLines</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
```$xslt
查找使用插件项目 所有D开头的文件
mvn initialize -Dprefix=D
```