package com.weison.mapper;

import com.weison.model.Book;
import java.util.List;

public interface BookMapper {

    /**
     * 通过ID查询单本图书
     *
     * @param id 主键
     * @return Book
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
     * @param book 图书
     */
    void insert(Book book);

    /**
     * 修改图书
     * @param book 图书
     */
    void update(Book book);

    /**
     * 删除图书
     * @param bookId 图书主键
     */
    void delete(Long bookId);
}
