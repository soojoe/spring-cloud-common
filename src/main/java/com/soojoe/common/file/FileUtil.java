package com.soojoe.common.file;

import java.io.File;

/**
 * 文件管理工具
 *
 * @author soojoe
 * @version 1.0.0
 * @date 2019/06/28 14:58
 */
public class FileUtil {

    /**
     * 强制删除文件夹，不管非空与否
     *
     * @param dir	传入的要删除的文件夹路径文件对象
     * @return true 成功；false 失败
     */
    public static boolean deleteDir(File dir) {
        if(!dir.exists()) {
            return true;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children != null){
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }

        // The directory is now empty so now it can be smoked
        return dir.delete();
    }

    /**
     * 强制删除文件夹，不管非空与否
     *
     * @param filename	传入的要删除的文件夹路径
     * @return true 成功；false 失败
     */
    public static boolean deleteFile(String filename) {
        return deleteFile(new File(filename));
    }
    /**
     * 强制删除文件
     *
     * @param file	要删除的文件对象
     * @return true 成功；false 失败
     */
    public static boolean deleteFile(File file) {
        if(!file.exists()) {
            return true;
        }
        return file.delete();
    }

    /**
     * 根据路径创建文件目录
     *
     * @param path
     */
    public static void mkdirsByLastOne(String path) {
        int index = path.lastIndexOf("/");
        String dir = path.substring(0, index);
        new File(dir).mkdirs();
    }
    public static void mkdirsByLastOne(File file) {
        mkdirsByLastOne(file.getPath());
    }

    public static boolean checkFileExists(String filePath) {
        return checkFileExists(new File(filePath));
    }
    public static boolean checkFileExists(File file) {
        if(file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getTimePathByTimestamp(Long timestamp) {
        String timePath = new java.text.SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(timestamp));
        return timePath;
    }
}

