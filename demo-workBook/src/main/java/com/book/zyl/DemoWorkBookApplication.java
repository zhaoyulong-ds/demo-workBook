package com.book.zyl;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoWorkBookApplication {

    public static void main(String[] args) {
        IChapterInterImpl  ChapterInterImpl = new IChapterInterImpl();
        List<Chapter> chapterList = ChapterInterImpl.getChapter("https://www.biquge.biz/11_11803/");
        WriterFile.writerFile(chapterList);
    }

}
