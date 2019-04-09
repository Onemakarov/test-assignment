package com.makarov.testassignment.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsDto {

    private Long id;

    private String title;

    private String content;

    private String publicationDate;

    private List<CategoryDto> categoryList;
}
