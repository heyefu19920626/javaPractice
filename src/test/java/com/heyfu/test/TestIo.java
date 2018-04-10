package com.heyfu.test;

import com.heyfu.practice.io.Travserse;
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
        String filePath = "C:\\Users\\Lenovo\\Desktop\\webpackTest";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println(filePath + "文件不存在");
            return;
        }
        File[] fileList = file.listFiles();
        File[] max_min = Travserse.getMaxAndMinFile(fileList, true);
        System.out.printf("最大文件名:%s, 文件大小:%d, 文件路径:%s %n", max_min[0].getName(), max_min[0].length(), max_min[0].getAbsolutePath());
        System.out.printf("最小文件名:%s, 文件大小:%d, 文件路径:%s %n", max_min[1].getName(), max_min[1].length(), max_min[1].getAbsolutePath());
    }

    /**
     * Description:
     * 练习流
     *
     * @author heyefu 13:24 2018/4/5
     **/
    @Test
    public void practiceStream() {



    }

}
