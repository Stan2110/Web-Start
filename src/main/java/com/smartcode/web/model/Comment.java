package com.smartcode.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Comment {

    private Integer id;
    private String title;
    private String description;
}
