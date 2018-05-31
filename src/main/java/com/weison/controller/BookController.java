package com.weison.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weison.exception.MyException;
import com.weison.model.Book;
import com.weison.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book") //给此控制器所有请求加上/book的前辍
public class BookController {

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
	public String insert(@Valid Book book, BindingResult bindingResult) throws MyException {
		if(bindingResult.hasErrors()){
            throw new MyException(bindingResult.getFieldError().getDefaultMessage());
		}

		bookService.insert(book);
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(
			@RequestParam("bookId") Long bookId,
			@RequestParam(value = "page", required = false) String page
	) {
		bookService.delete(bookId);
		return "redirect:/book/list?page=" + page;
	}

	@RequestMapping(value = "/update-form", method = RequestMethod.GET)
	public String update(
			@RequestParam("bookId") Long bookId,
			Model model,
			@RequestParam(value = "page", required = false) String page
	) {
		Book book = bookService.getById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("page", page);
		return "form";
	}

	@RequestMapping("/update")
	public String update(
			Book book,
			@RequestParam(value = "page", required = false) String page
	) {
		bookService.update(book);
		return "redirect:/book/list?page=" + page;
	}
}
