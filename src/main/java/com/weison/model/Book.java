package com.weison.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Book {
    private Long bookId;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotNull(message = "库存不能为空")
    private Integer number;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}