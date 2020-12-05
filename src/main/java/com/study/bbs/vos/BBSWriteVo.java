package com.study.bbs.vos;

public class BBSWriteVo {
    private final String writer;
    private final String password;
    private final String title;
    private final String content;

    public BBSWriteVo(String writer, String password, String title, String content) {
        this.writer = writer;
        this.password = password;
        this.title = title;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public String getPassword() {
        return password;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}