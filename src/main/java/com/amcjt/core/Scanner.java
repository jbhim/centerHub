package com.amcjt.core;

import java.util.List;

/**
 * 扫描器
 *
 * @author jintao
 */
public interface Scanner {

    /**
     * 解析
     */
    void analysis();

    /**
     * Book名称
     */
    String getName();

    /**
     * BookSize
     */
    long getSize();

    /**
     * 章节
     */
    List<Chapter> getChapters();

    /**
     * 章节
     */
    Chapter getChapter(int number);
}
