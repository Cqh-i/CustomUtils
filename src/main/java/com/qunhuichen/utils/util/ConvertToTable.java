package com.qunhuichen.utils.util;

import java.io.*;

/**
 * @author: Cqh-i
 * @create: 2020-03-21 12:03
 */
public class ConvertToTable {
    /**
     * 功能：将合适的数据(以空格分割)转换成Markdown表格数据
     * 步骤：
     * 读取txt文件的内容
     *  1: 先获得文件句柄
     *  2:获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     *  3: 读取到输入流后，需要读取生成字节流
     * 将数据转换为MarkDown表格类型数据
     * 写入testOut.txt文件中
     * 备注：需要考虑的是异常情况
     *
     * @param filePath 文件路径[需要读取的文件:如： D:\aa.txt]
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    public static void convertToTable(String filePath) throws IOException {
        String encoding = "UTF-8";
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.isFile() && file.exists()) {
            // 考虑到编码格式
            try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                 BufferedReader bufferedReader = new BufferedReader(read);
            ) {
                String lineTxt = null;
                boolean isFirstLine = true;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    // 指定分割字符
                    String delimeter = " ";
                    // 分割字符串
                    String[] temp = lineTxt.split(delimeter);
                    StringBuilder sb = new StringBuilder();
                    sb.append('|');
                    for (int i = 0; i < temp.length; i++) {
                        sb.append(temp[i]);
                        sb.append('|');
                    }
                    sb.append("\n");

                    File writeName = new File("F:\\testOut.txt");
                    //表头
                    if (isFirstLine) {
                        int len = temp.length;
                        sb.append('|');
                        for(int i = 0; i < len; i++){
                            sb.append("--|");
                        }
                        sb.append("\n");
                        if(!writeName.delete()) {
                            System.out.println("文件删除失败!");
                        }
                        isFirstLine = false;
                    }
                    writeName.createNewFile();
                    try (FileWriter writer = new FileWriter(writeName, true);
                         BufferedWriter out = new BufferedWriter(writer)
                    ) {
                        out.write(sb.toString());
                        out.flush(); // 把缓存区内容压入文件
                    }
                }
            }
        } else {
            System.out.println("找不到指定的文件");
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "F:\\test.txt";
        convertToTable(filePath);
        System.out.println("转换完成！");
    }
}
