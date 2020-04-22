## 我的自定义工具包
目前有以下小工具：
1. 将以空格分割的合适数据转换成Markdown表格数据
    如test.txt文件里的内容为：
    ```
    国家（country） 性别（sex） 人口（population） 
    中国 1 340 
    中国 2 260 
    美国 1 45 
    美国 2 55 
    加拿大 1 51 
    加拿大 2 49 
    英国 1 40 
    英国 2 60 
    ```
    那么输出的testOut.txt里的内容为
    ```
    |国家（country）|性别（sex）|人口（population）|
    |--|--|--|
    |中国|1|340|
    |中国|2|260|
    |美国|1|45|
    |美国|2|55|
    |加拿大|1|51|
    |加拿大|2|49|
    |英国|1|40|
    |英国|2|60|
    ```
    用法: 调用ConvertToTable#convertToTable(String filePath)即可；

2. 获取配置文件的工具类
> (1)根据配置文件的key，返回对应的配置值。
> ConfigUtil#public static String getConfig(String configKey) 根据配置文件的key，返回对应的配置值

> (2)根据配置文件的key，返回对应的配置值，如果configKey不存在,或者值为空的字符串，则返回默认值defaultValue
> ConfigUtil#public static String getConfig(String configKey, String defaultValue)

> (3)属性文件中是否包含键configKey，包含返回true
> ConfigUtil#public static boolean contentKey(String configKey)

> (4)根据配置文件的key，返回对应的配置值，如果configKey不存在,或者值为空的字符串，则返回默认值defaultValue
> 返回的数据类型：支持Integer.class，Double.class，Boolean.class，Float.class，Long.class，String.class
> ConfigUtil#public static <E> E getConfig(String configKey, E defaultValue, Class<E> clazz)