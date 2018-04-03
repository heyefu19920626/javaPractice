package com.heyfu.test;

import org.junit.Test;

import java.io.File;

/**
 * Description:
 * 练习IO流
 *
 * @author heyefu
 * Create in: 2018-04-03
 * Time: 16:32
 **/
public class TestIo {

    /**
     * Description:
     * 遍历C:\Windows文件夹,并找出该目录中最大的与最小的文件
     *
     * @author heyefu 16:36 2018/4/3
     **/
    @Test
    public void traverseFile() {

        String filePath = "C:\\Windows";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(filePath + "文件不存在");
            return;
        }

        File[] fileList = file.listFiles();
        File minFile = null;
        File maxFile = null;
        String format = "文件名: %s  文件大小: %.8f %n";

        for (File f : fileList){
            if (minFile == null && f.length() > 0){
                minFile = f;
            }
            if (maxFile == null && f.length() > 0){
                maxFile = f;
            }
            if (f.length() > 0 && minFile.length() > f.length()){
                minFile = f;
            }
            if (f.length() > 0 && f.length() > maxFile.length()){
                maxFile = f;
            }
        }

        double minLength = minFile.length() / 1024.0 / 1024;
        double maxLength = maxFile.length() / 1024.0 / 1024;

        System.out.printf("总共有%d个文件%n", fileList.length);
        System.out.printf(format, minFile.getName(), minLength);
        System.out.printf(format, maxFile.getName(), maxLength);


    }

}
