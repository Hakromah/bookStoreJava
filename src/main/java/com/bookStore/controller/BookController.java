package com.bookStore.controller;

import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;
import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    //creat BookService Object & pass it to addBooks method that will immplements save action
    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    // fetch data from databases and send it to Available book list
    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = service.getAllBook();
        //ModelAndView m = new ModelAndView();
        //m.setViewName("availableBooks");
        //m.addObject("book",list);
        //return m;
        return new ModelAndView("availableBooks", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book", list);
        return "myBooks";
    }

    //Let save selected book from available book to MyBookList
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b = service.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    // Edit book
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book b = service.getBookById(id);
        model.addAttribute("book", b);
        return "bookEdit";
    }

    //Delete the book
    @RequestMapping("/deleteAvailableList/{id}")
    public String deleteAvailableList(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/available_books";
    }

}



















