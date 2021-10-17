package com.amcjt.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jintao
 */
public class TxtScanner implements Scanner {

    private Pattern pattern = Pattern.compile("第(.+)章(.*)?");
    private final List<Chapter> chapters = new ArrayList<>();
    private List<String> lines = new ArrayList<>();
    private final Path path;
    private long size;

    public TxtScanner(String path) {
        this.path = Paths.get(path);
    }

    public TxtScanner(File file) {
        this.path = Paths.get(file.getAbsolutePath());
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void analysis() {
        try {

            this.lines = Files.readAllLines(path);
            for (int i = 0; i < this.lines.size(); i++) {
                String line = this.lines.get(i);
                this.size += line.length();
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    if (this.chapters.size() > 0) {
                        this.chapters.get(this.chapters.size() - 1).setEndPos(i - 1);
                    }
                    Chapter chapter = new Chapter();
                    chapter.setName(line);
                    chapter.setStartPos(i);
                    this.chapters.add(chapter);
                }
            }
            this.chapters.get(this.chapters.size() - 1).setEndPos(lines.size() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return path.getFileName().toString();
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public List<Chapter> getChapters() {
        return this.chapters;
    }

    @Override
    public Chapter getChapter(int number) {
        Chapter chapter = this.chapters.get(number);
        List<String> stringList = this.lines.subList(chapter.getStartPos(), chapter.getEndPos());
        chapter.setContent(String.join("\r\n", stringList));
        return chapter;
    }

    public static void main(String[] args) {
        TxtScanner txtScanner = new TxtScanner("/Users/jintao/Downloads/雪中悍刀行.txt");
        txtScanner.analysis();
        System.out.println(txtScanner.getSize());
    }
}
