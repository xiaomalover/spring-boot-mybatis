package com.weison.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weison.model.Book;
import com.weison.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /seckill/list
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(@RequestParam(required=true,defaultValue="1") Integer page, Model model) {

		PageHelper.startPage(page, 5);
		List<Book> list = bookService.getList();
		PageInfo<Book> p = new PageInfo<Book>(list);

		model.addAttribute("page", p);
		model.addAttribute("list", list);
		// list.html + model = ModelAndView
		return "list";// WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "detail";
	}

	@RequestMapping("/insert")
	public String insert(Book book) {
		bookService.insert(book);
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("bookId") Long bookId) {
		bookService.delete(bookId);
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/update-form", method = RequestMethod.GET)
	public String update(@RequestParam("bookId") Long bookId, Model model) {
		Book book = bookService.getById(bookId);
		model.addAttribute("book", book);
		return "form";
	}

	@RequestMapping("/update")
	public String update(Book book) {
		bookService.update(book);
		return "redirect:/book/list";
	}
}
