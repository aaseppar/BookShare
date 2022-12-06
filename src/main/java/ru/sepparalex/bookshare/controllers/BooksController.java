package ru.sepparalex.bookshare.controllers;
import ru.sepparalex.bookshare.dao.BookDAO;
import ru.sepparalex.bookshare.models.Book;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Здесь описывает контроллер, который манипулирует сущностью Books с помощью CRUD операций

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookDAO bookDAO;

    //Отображение всех книг
    @GetMapping
    public String index(Model model) {
        List<Book> bookList = bookDAO.allBooks();
        //После добавления списка в модель он будет виден в нашем view
        model.addAttribute("books", bookList);
        return "books/index";
    }

    //Будут обрабатываться ссылки вида:
    // /books/1
    // /books/2 и так далее....
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "books/show";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    //@ModelAttribute нам даёт готовый объект book из нашего представления (book/new.html)

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "books/edit";
    }
    //Patch
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }
}
