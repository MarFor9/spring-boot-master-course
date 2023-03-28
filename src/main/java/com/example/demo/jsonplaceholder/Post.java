package com.example.demo.jsonplaceholder;

import lombok.Data;

@Data
public class Post {
    private final Integer userId;
    private final Integer id;
    private final String titile;
    private final String body;
}
