# create-api
1、打包项目使用mvn install

2、启动项目使用  java -jar creator-api-0.0.1-SNAPSHOT.jar

3、访问地址为 http://localhost:8080/service/test?wsdl

4、访问得到json的方式为 http://localhost:8080/services/rest/testUser/lyy.json 
   其中具体方法实现可参考ren.xiangmu.creatorapi.services.impl.TestServiceImpl
       lyy为需要查询的参数，.json表示需要返回的数据格式为json，若不传递，表示返回xml格式数据，暂时不支持这种

# 项目使用springboot搭建

需要使用jdk8进行编译及运行
