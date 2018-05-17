package com.weison.service.impl;

import com.weison.mapper.BookMapper;
import com.weison.model.Book;
import com.weison.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private BookMapper bookMapper;

	@Override
	public void insert(Book book)
	{
		bookMapper.insert(book);
	}

	@Override
	public void update(Book book)
	{
		bookMapper.update(book);
	}

	@Override
	public void delete(Long bookId)
	{
		bookMapper.delete(bookId);
	}

	@Override
	public Book getById(long bookId) {
		return bookMapper.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookMapper.queryAll();
	}

}
