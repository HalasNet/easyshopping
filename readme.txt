1：通过git clone https://github.com/lianzhengkun/easyshopping.git 到任意目录。
2：请保证你的机器上有maven，并已配置好。cd 到刚才项目的目录。
3：执行命令：mvn eclipse:eclipse -Dwtpversion=2.0 ，这样子生成eclipse项目并且是web项目，可以被eclipse以web项目标记。第一次执行时间可能需要比较长，请耐心等待。
4：通过eclipse将项目导入，这个时候可以查看项目全貌。
5：修改src/main/resources下的两份文件：
   第一份文件：hibernate.cfg.xml，这份文件是用于自主生成表结构，请根据自己机器的情况自行修改，数据库名，用户名和密码
   第二份文件：resources.properties，这份文件主要为Spring所识别来做配置，因为项目是利用Spring来进行sessionFactory的管理，所以这份文件是Spring必需的，这里也需要大家根据自身机器的情况来修改。参照第一份文件。
6：执行src/main/java源文件夹下的com.ibm.util.TableManager类，这个类会读取hibernate.cfg.xml，这份文件里包含了通过JPA注解的实体类,所以TableManager类会根据这些注解生成表结构。
7：因为项目中使用到了一个第三方jar包，但是没有对应的pom，所以需要大家在通过eclipse来将这个第三方jar包放到项目的Build Path中，这个jar包放在webapp/WEB-INF/lib下。
8：生成表结构后，就可以把项目放在eclipse中通过你自己的web服务器进行部署了，当然也可以通过命令mvn clean install jetty:run 来构建项目，部署到jetty服务器，并且通过浏览器进行访问了。enjoy it!

