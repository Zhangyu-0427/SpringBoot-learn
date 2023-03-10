package org.zzzzzz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzzzzz.controller.utils.R;
import org.zzzzzz.domain.Book;
import org.zzzzzz.service.IBookService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController2 {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R getAll() {
        return new R(true, bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        if(book.getName().equals("123")) {
            throw new IOException();
        }
        return new R(bookService.save(book));
    }

    @PutMapping
    public R update(@RequestBody Book book) {
        return new R(bookService.updateById(book));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        return new R(bookService.removeById(id));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true, bookService.getById(id));
    }

//    @GetMapping("/{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
//        IPage<Book> page = bookService.getPage(currentPage, pageSize);
//
//        if(currentPage > page.getPages()) {
//            page = bookService.getPage((int)page.getPages(), pageSize);
//        }
//
//        return new R(true, page);
//    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {
        IPage<Book> page = bookService.getPage(currentPage, pageSize, book);

        if(currentPage > page.getPages()) {
            page = bookService.getPage((int)page.getPages(), pageSize, book);
        }

        return new R(true, page);
    }

}
