## Spring security 

lbw-security  主模块  
├─ lbw-security-app app相关特定代码  
├─ lbw-security-browser 浏览器安全特定代码  
├─ lbw-security-core  核心业务逻辑  
├─ lbw-security-demo  样例程序  
└─ pom.xml  


Spring Security 核心原理  
基于过滤器链，先后依次调用  
UsernamePasswordAuthenticationFiler 处理表单登陆  
BasicAuthenticationFilter  处理basic登陆  
...  
ExceptionTranslationFilter 对最后一步抛出异常进行捕获并下一步处理  
FilterSecurityInterceptor 依据配置来进行放行api  


//    api安全方式
//    1. 访问api FilterSecurityInterceptor 拦截未认证，抛出异常
//    2. ExceptionTranslationFilter 捕获异常 并跳转到/login
//    3. 正确填写表单，由UsernamePasswordAuthenticationFiler来进行认证
//    4. 认证通过，调用FilterSecurityInterceptor来放行api
//    5. 访问controller方法

配置优先顺序：
请求最高，其次配置优先级，最后逻辑