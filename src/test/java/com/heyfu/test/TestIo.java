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
    public void traverseFile(String filePath) {

        File file = new File(filePath);
        if (!file.exists()){
            System.out.println(filePath + "文件不存在");
            return;
        }
        File[] fileList = file.listFiles();

    }

}
