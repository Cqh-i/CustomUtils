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