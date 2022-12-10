package ru.sepparalex.bookshare.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
// Was added Lombok Annotations
@Getter
@Setter
@ToString
public class Book {
    private int id;                 //id книги
    @NotEmpty(message = "Название книги не может быть пустым!")
    @Size(min = 2, max = 100, message = "Название книги должно быть длиной от 2 до 100 символов!")
    @Pattern(regexp="[А-Яа-я\\s]+", message = "Название книги должно состоять только из русских букв!")
    private String name;            //Название книги

    @NotEmpty(message = "Книга не может быть без автора!")
    @Size(min = 2, max = 100, message = "Имя автора книги должно быть длиной от 2 до 100 символов!")
    @Pattern(regexp="[А-Яа-я\\s]+", message = "Имя автора книги должно состоять только из русских букв!")
    private String author;            //Автор книги

    @NotEmpty(message = "Жанр не может быть пустым!")
    @Size(min = 5, max = 100, message = "Жанр должен быть длиной от 20 до 100 символов!")
    private String genre;     //Жанр книги

    public Book() {

    }

    public Book(int id, String author, String name,String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre=genre;

    }
}