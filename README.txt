1、前端框架bootstrap
css+html+js（jQuery）+ajax+layer
2、spring、springmvc、mybatis、mysql、json

***前后台的请求交互***
产品类型管理模块：
（1）点击添加产品类型按钮，通过jsquery跳出模态框。
（2）通过$.post(url,data,callback) 发送数据给服务器，接受响应数据进行处理
{"name":$("#id").val()}  >>>   {"status":1,"message":"添加成功"}    >>>     前台展示
（3）点击修改按钮，将id传递给服务器，返回对应的商品类型数据，填充到模态框中；修改完产品名称后，点击修改按钮，
将id和name发送给服务器，服务器在数据库执行update操作，之后返回json格式数据{"status":1,"message":"更新成功"}

注解的使用：
@InitBinder