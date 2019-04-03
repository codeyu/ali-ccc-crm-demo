该项目用来演示CRM如何接入云呼叫中心坐席工作台。

步骤：
1）安装 Java SDK 1.8

2）安装 Maven最新版

3) 替换 crm-oauth2/src/main/resources/application.properties 文件中的client.id和client.secret，替换为您自己注册应用的client.id和client.secret，如何注册应用请参考 https://help.aliyun.com/document_detail/63062.html ，注册时会让您输入回调地址，请将 https://127.0.0.1:8443/aliyun/auth/callback 设置为回调地址（如果已注册，在应用列表中可以通过编辑应用增加回调地址），否则本demo无法正常运行。

4）替换 crm-demo2/crm-oauth2/src/main/resources/templates/home.html 中实例化WorkbenchSdk（第113行）时传入的 instanceId，instanceId获取方式请参考 https://help.aliyun.com/document_detail/85054.html

5) 在crm-oauth2目录下，输入命令：mvn spring-boot:run -Dmaven.test.skip

6）打开浏览器，输入https://127.0.0.1:8443

7）根据提示输入用户名密码：edward, 1234

8) 点击页面左上部的 Retrieval Aliyun Token 来绑定阿里云账户，通过阿里云子账号登录，这里的子账号就是呼叫中心的坐席人员登录的账号，新建坐席时会收到邮件，邮件中包含了坐席登录呼叫中心的链接、账户名和密码。（请记得将登录的子账号加入到任意技能组中，并且对应技能组绑定的外呼号码）

9）点击软电话的上线按钮，上线后可以进行工作。

10）该项目是一个很好的系统集成的demo，您可以详细查看内部代码逻辑。


