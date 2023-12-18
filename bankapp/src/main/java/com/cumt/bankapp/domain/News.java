package com.cumt.bankapp.domain;

import lombok.Data;

/**
 * @author 李易蔚
 * @version 1.0
 */

@SuppressWarnings({"all"})
@Data
public class News {
    private String id;
    private String title;
    private String content;
    private String image;
    private String author;
    private String watch;
    private String userId;
    private String likes;
    private String like;

}
