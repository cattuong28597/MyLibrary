package controller;

import model.Book;
import service.BookService;
import service.BookServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createBook(request, response);
                break;
            case "edit":
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                break;
        }
    }

    public void listBooks(HttpServletRequest request, HttpServletResponse response) {
        List<Book> listBook = this.bookService.showAllBooks();
        request.setAttribute("bookList", listBook);

        RequestDispatcher dispatcher = request.getRequestDispatcher("book/listBook.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("bookDelete", book);
            dispatcher = request.getRequestDispatcher("book/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.bookService.deleteBook(id);
            try {
                response.sendRedirect("/books");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/createBook.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBook(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id_book");
        boolean isId = false;

        if (id == "") {
            request.setAttribute("errorID", "ID is empty");
        } else if (bookService.isCheckId(id)) {
            request.setAttribute("errorID", "Id is duplicate");
        } else if (bookService.isFormatId(id) == false) {
            request.setAttribute("errorID", "Format ID is not true");
        } else {
            isId = true;
        }

        String name = request.getParameter("name");
        String kind = request.getParameter("kind");
        String author = request.getParameter("author");
        String checkNumber = request.getParameter("quantity");
        int quantity ;

        if (checkNumber.matches("[0-9]+") == false){
            request.setAttribute("errorQuantity", "Quantity is not true");
        } else {
            quantity = Integer.parseInt(checkNumber);
            if (isId == false || name == "" || kind =="" || author == "" || quantity == 0){
                request.setAttribute("error", "Don't create");
            } else {
                Book newBook = new Book(id, name, kind, author,quantity);
                bookService.createBook(newBook);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("book/createBook.jsp");
        request.setAttribute("success", "New book was created");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
