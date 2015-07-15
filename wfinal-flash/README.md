wfinal flash使用说明
----
方便往页面传递redirect信息，类似Spring的`RedirectAttributes`。
本功能适用于redirect之间参数的传递
# 使用方法
将拦截器加入jfinal config中
```java
@Override
public void configInterceptor(Interceptors me) {
    me.add(new FlashMessageInterceptor());
}
```
# API说明
##### 发送成功消息，页面对应接收参数：`flash_success`
```java
public static void setSuccessMessage(Controller controller, String message)
```
* controller: 当前Controller对象
* message: 消息内容

##### 发送成功消息，页面对应接收参数：`flash_success`，带redirect功能 `v2.1.2`
```java
public static void redirectSuccessMessage(Controller controller, String url, String message)
```
* controller: 当前Controller对象
* url: redirect url
* message: 消息内容

##### 发送说明消息，页面对应接收参数：`flash_info`
```java
public static void setInfoMessage(Controller controller, String message)
```
* controller: 当前Controller对象
* message: 消息内容

##### 发送说明消息，页面对应接收参数：`flash_info`，带redirect功能 `v2.1.2`
```java
public static void redirectInfoMessage(Controller controller, String url, String message)
```
* controller: 当前Controller对象
* url: redirect url
* message: 消息内容

##### 发送错误消息，页面对应接收参数：`flash_error`
```java
public static void setErrorMessage(Controller controller, String message)
```
* controller: 当前Controller对象
* message: 消息内容

##### 发送错误消息，页面对应接收参数：`flash_error`，带redirect功能 `v2.1.2`
```java
public static void redirectErrorMessage(Controller controller, String url, String message)
```
* controller: 当前Controller对象
* url: redirect url
* message: 消息内容

##### 发送警告消息，页面对应接收参数：`flash_warning`
```java
public static void setWarningMessage(Controller controller, String message)
```
* controller: 当前Controller对象
* message: 消息内容

##### 发送警告消息，页面对应接收参数：`flash_warning`，带redirect功能 `v2.1.2`
```java
public static void redirectWarningMessage(Controller controller, String url, String message)
```
* controller: 当前Controller对象
* url: redirect url
* message: 消息内容

##### 发送自定义消息，页面接收参数自定义。
```java
public static void setMessage(Controller controller, String attribute, String message)
```
* controller: 当前Controller对象
* attribute: 页面接收参数
* message: 消息内容

##### 发送自定义消息，页面接收参数自定义，带redirect功能 `v2.1.2`
```java
public static void redirect(Controller controller, String url, String attribute, String message)
```
* controller: 当前Controller对象
* url: redirect url
* attribute: 页面接收参数
* message: 消息内容

所有预定义的页面参数都可以从`FlashMessage`源码中看到。