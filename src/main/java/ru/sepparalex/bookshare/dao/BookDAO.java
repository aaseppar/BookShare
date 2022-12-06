package ru.sepparalex.bookshare.dao;

import ru.sepparalex.bookshare.models.Book;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\Java_Learn\\HomeWorks\\HomeWork_111222\\BookShare\\database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Вернуть список всех книг
    //READ
    public List<Book> allBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM BOOK";
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                int bookId = set.getInt("id");
                String author = set.getString("author");
                String name = set.getString("name");
                String genre = set.getString("genre");
                books.add(new Book(bookId, author,name, genre));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }
    // Вернуть книгу по id
    //READ
    public Book getBook(int id) {
        try {
            String query = "SELECT * FROM BOOK WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int bookID = set.getInt("id");
                String author = set.getString("author");
                String name = set.getString("name");
                String genre = set.getString("genre");
                return new Book(bookID, author, name,genre);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //CREATE
    // Добавляем новую книгу
    public void addBook(Book book) {
        try {
            String query = "INSERT INTO BOOK(author,name,genre) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getName());
            statement.setString(3, book.getGenre());
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    //UPDATE Book
    //
    public void updateBook(int id, Book updatedBook) {
        try {
            String query = "UPDATE BOOK SET author = ?, name = ?, genre = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedBook.getAuthor());
            statement.setString(2, updatedBook.getName());
            statement.setString(3, updatedBook.getGenre());
            statement.setInt(4, id);
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
