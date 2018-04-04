package com.heyfu.practice.io;

import java.io.File;

/**
 * Description:
 * <br>遍历
 *
 * @author heyefu
 * Create in: 2018-04-04
 * Time: 11:09
 **/
public class Travserse {

    /**
     * Description:
     * <br>找出文件列表files及其子文件夹中的最大文件和不等于0的最小文件
     *
     * @param files   文件列表
     * @param maxFile 最大文件
     * @param minFile 最小文件
     * @param travSon 是否遍历子文件夹
     * @return java.io.File[]
     * @author heyefu 13:13 2018/4/4
     **/
    public static File[] traversrFileList(File[] files, File maxFile, File minFile, Boolean travSon) {
        if (files == null || files.length == 0) {
            System.out.println("文件列表为空!");
            return null;
        }
        for (File f : files) {
//            初始化最大的文件和最小的文件
            if (f.length() > 0 && maxFile == null) {
                maxFile = f;
            }
            if (f.length() > 0 && minFile == null) {
                minFile = f;
            }
//            如果该文件是文件夹且要求遍历子文件夹,则继续遍历
            if (travSon && f.isDirectory()) {
                File[] fileList = f.listFiles();
                File[] maxMin = traversrFileList(fileList, maxFile, minFile, travSon);
                if (maxMin != null && maxFile.length() < maxMin[0].length()) {
                    maxFile = maxMin[0];
                }
                if (maxMin != null && minFile.length() > maxMin[1].length()) {
                    minFile = maxMin[1];
                }
                continue;
            }
//            判断最大的文件和最小文件
            if (maxFile != null && f.length() > 0 && maxFile.length() < f.length()) {
                maxFile = f;
            }
            if (minFile != null && f.length() > 0 && minFile.length() > f.length()) {
                minFile = f;
            }
        }

        File[] maxMin = {maxFile, minFile};
        return maxMin;
    }


    /**
     * Description:
     * <br>遍历文件列表找出最大文件及不等于0最小文件
     *
     * @param files 文件列表
     * @return java.io.File[]
     * @author heyefu 13:16 2018/4/4
     **/
    public static File[] getMaxAndMinFile(File[] files) {
        return getMaxAndMinFile(files, false);
    }

    /**
     * Description:
     * <br>遍历文件列表及其子文件夹找出最大文件及不等于0的最小文件
     *
     * @param files   文件列表
     * @param travSon 是否遍历子文件夹
     * @return java.io.File[]
     * @author heyefu 13:18 2018/4/4
     **/
    public static File[] getMaxAndMinFile(File[] files, Boolean travSon) {
        File maxFile = null;
        File minFile = null;
        return traversrFileList(files, maxFile, minFile, travSon);
    }

}
