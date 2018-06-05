package com.weison.service;

import com.weison.model.Book;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface BookService {

	/**
	 * 查询一本图书
	 * 
	 * @param bookId
	 * @return
	 */
	Book getById(long bookId);

	/**
	 * 查询所有图书
	 * 
	 * @return
	 */
	List<Book> getList();

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
	 * 用于测试多线程-批量插入-非阻塞模式
	 * @param i
	 */
	void executeAsyncTask(int i);

	/**
	 * 用于测试多线程-批量插入-阻塞模式
	 * @param i
	 */
	Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException;
}
