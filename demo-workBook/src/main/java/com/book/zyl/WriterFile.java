package com.book.zyl;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoYuLong on 2022/1/24 17:45
 */
public class WriterFile {

    public static void writerFile(List<Chapter> chapterList) {
        // 根据小说存放位置创建file对象
        File file = new File("D:\\File\\大王饶命.txt");
        String content = "";
        for (int i = 0; i < chapterList.size(); i++) {
                System.out.println("第" + i + "章开始下载。。。");
                content += chapterList.get(i).getTitle()+"\t"+chapterList.get(i).getContent() + "\n";
                System.out.println("第" + i + "章下载完成.........");
        }
        try {
            // 创建输入流
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            // 把小说内容写入文件
            System.out.println(content);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("下载失败");
            e.printStackTrace();
        }
    }

}
