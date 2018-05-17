package com.weison.mapper;

import com.weison.model.Book;

import java.util.List;

public interface BookMapper {

    /**
     * 通过ID查询单本图书
     *
     * @param id
     * @return long
     */
    Book queryById(long id);

    /**
     * 查询所有图书
     *
     * @return Book
     */
    List<Book> queryAll();

    /**
     * 新增图书
     * @param book
     */
    void insert(Book book);

    /**
     * 修改图书
     * @param book
     */
    void update(Book book);

    /**
     * 删除图书
     * @param bookId
     */
    void delete(Long bookId);

    /**
     * 减少馆藏数量
     *
     * @param bookId
     * @return 如果影响行数等于>1，表示更新的记录行数
     */
    int reduceNumber(long bookId);
}