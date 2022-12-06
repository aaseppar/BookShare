package ru.sepparalex.bookshare.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}