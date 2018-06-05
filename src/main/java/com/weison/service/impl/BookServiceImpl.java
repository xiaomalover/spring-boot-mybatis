package com.weison.service.impl;

import com.weison.mapper.BookMapper;
import com.weison.model.Book;
import com.weison.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

	private Random random = new Random();

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


	/**
	 * 异步方法, 无返回值
	 * @param i 序号，方便展示
	 */
	@Override
	@Async
	public void executeAsyncTask(int i) {
		System.out.println("执行异步任务非阻塞任务：" + i);
		//插入book
		Book book = new Book();
		book.setName("异步线程" + i + "插入图书");
		book.setNumber(i);
		this.insert(book);
	}

	/**
	 * 异常调用返回Future
	 *
	 * @param i 序号，方便展示
	 * @return Future
	 * @throws InterruptedException 异常
	 */
	@Override
	@Async
	public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
		System.out.println("当前准备执行" + i);
		Thread.sleep(10 * random.nextInt(i));

		//插入book
		Book book = new Book();
		book.setName("异步线程，阻塞模式" + i + "插入图书");
		book.setNumber(i);
		this.insert(book);

		// Future接收返回值，这里是String类型，可以指明其他类型
		Future<String> future = new AsyncResult<>("成功执行异步任务(阻塞模式):" + i);

		return future;
	}

}
