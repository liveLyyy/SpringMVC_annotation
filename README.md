SpringMVC
=========
1、字符编码过滤器<br>
```xml
<!-- 字符编码配置器-->
    <filter>
        <filter-name>Encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>Encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>Encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
2、参数的传递<br>
>2.1、把内容写到方法（HandlerMethod）参数中,SpringMVC只有有这个内容，注入内容<br>
>2.2、基本数据类型参数,默认保证参数名称和请求中传递的参数相同<br>
```java
@Controller
public class DemoController {
    @RequestMapping("demo")
    public String Demo(String name, String password) {
        System.out.println("执行controller"+"+name+"+"password");
        return "index.jsp";
    }
}
```
>2.3、required=true，必须要传递的参数<br>
>2.4、请求参数名和方法名参数名不对应时使用@RequestParam（）赋值<br>
```java
@Controller
public class DemoController {
    @RequestMapping("demo")
    public String Demo(@RequestParam(value = "name1") String name, @RequestParam(value = "password1") String password) {
        System.out.println("执行controller"+"+name+"+"password");
        return "index.jsp";
    }
}
```
>2.5、如果参数是基本数据类型（不是封装类）可以通过@RequestParam设置赋值,防止没有参数是500错误<br>
```java
@Controller
public class DemoController {
    @RequestMapping("demo")
    public String page(@RequestParam(defaultValue = "2")int pageSize,@RequestParam(defaultValue = "1") int pageNumber){
        System.out.println(pageSize+pageNumber);
        return "index.jsp";
    }
}
```
3、视图解析器<br>
>3.1、Spring会提供默认视图解析器<br>
>3.2、自定义视图解析器<br>
```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
```
>3.3、如果希望不执行自定义视图解析器，在方法返回值前添加forword或redirect

4、@ResponseBody(恒不跳转)
>4.1、在方法上只有@ResponseBody是，无论方法返回值是什么都认为不需要跳转<br>
>4.2、在方法上添加@ResponseBody后，如果返回值满足key-value形式(对象或map)<br>
>>4.2.1、把相应头设置为application/json；charset=utf-8<br>
>>4.2.2、把转换后的内容输出流的形式响应给客户端<br>

>4.3、返回值不满足key-value形式(对象或map)<br>
>>4.3.1、把相应头设置为text/html<br>
>>4.3.2、把方法方绘制以流的形式直接输出<br>
>>4.3.3、如果返回值包含中文;produces表示响应头中Content-Type取值<br>
```java
 @RequestMapping(value = "demo5",produces = "text/html;charset=utf-8")
```