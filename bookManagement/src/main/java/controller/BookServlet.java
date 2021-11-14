package controller;

import model.Book;
import model.Customer;
import service.BookService;
import service.BookServiceImpl;
import service.CustomerService;
import service.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/books")
public class BookServlet extends HttpServlet {

    BookService bookService = new BookServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();

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
            case "search":
                showSearchForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "lend":
                showLendForm(request, response);
                break;
            case "showCustomer":
                showCustomer(request,response);
                break;
            case "deleteCustomer":
                deleteCustomer(request,response);
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
            case "search":
                searchBook(request, response);
                break;
            case "edit":
                editBook(request,response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            case "lend":
                lendBook(request, response);
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
        boolean checkBookExist = false;
        List<Customer> listCustomer = this.customerService.showCustomers();
        for (Customer c: listCustomer){
            if (id == c.getIdBook()) {
                checkBookExist = true;
                break;
            }
        }
        RequestDispatcher dispatcher;
        if (checkBookExist) {
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

    public void showSearchForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/search.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBook(HttpServletRequest request, HttpServletResponse response) {
        String value = request.getParameter("value");
        String symSearch = request.getParameter("symSearch");

        List<Book> books = this.bookService.searchBook(value, symSearch);

        RequestDispatcher dispatcher;

        if (books == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("bookList", books);
            dispatcher = request.getRequestDispatcher("book/search.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("bookEdit", book);
            dispatcher = request.getRequestDispatcher("book/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editBook(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);
        RequestDispatcher dispatcher = null;
        if (book == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            String name = request.getParameter("name");
            String kind = request.getParameter("kind");
            String author = request.getParameter("author");
            String checkNumber = request.getParameter("quantity");
            int quantity ;

            if (checkNumber.matches("[0-9]+") == false){
                request.setAttribute("errorQuantity", "Quantity is not true");
                request.setAttribute("errorEdit", "Can't update");
            } else {
                quantity = Integer.parseInt(checkNumber);
                if (name == "" || kind =="" || author == "" || quantity == 0){
                    request.setAttribute("errorEdit", "Can't update");
                } else {
                    Book newBook = new Book(id, name, kind, author,quantity);
                    this.bookService.edit(newBook);
                    request.setAttribute("successEdit", "Edit success!");
                }
            }
            dispatcher = request.getRequestDispatcher("book/edit.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showCustomer(HttpServletRequest request, HttpServletResponse response){
        List<Customer> listCustomer = this.customerService.showCustomers();
        request.setAttribute("listCustomer", listCustomer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("book/listCustomer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLendForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/lendBook.jsp");
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);

        request.setAttribute("bookLend", book);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lendBook(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Book book = this.bookService.findById(id);
        RequestDispatcher dispatcher = null;

        request.setAttribute("bookLend", book);

        String nameCustomer = request.getParameter("nameCustomer");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone");
        String mail = request.getParameter("mail");
        String checkAmount = request.getParameter("amount");
        int maxAmount = book.getQuantity();
        int amount;

        if (checkAmount.matches("[0-9]+") == false){
            request.setAttribute("errorAmount", "Amount is not true");
            request.setAttribute("errorLend", "Can't lend");
        } else {
            amount = Integer.parseInt(checkAmount);
            if (nameCustomer == "" || address =="" || phoneNumber == "" || amount <= 0 || amount > maxAmount){
                request.setAttribute("errorLend", "Can't lend");
            } else {
                Customer newCustomer = new Customer(nameCustomer, address, phoneNumber, mail, id,amount);
                customerService.createCustomer(newCustomer);
                request.setAttribute("successLend", "Lend success!");
                try {
                    response.sendRedirect("/books?action=showCustomer");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher dispatcher;
        customerService.deleteCustomer(id);
        try {
            response.sendRedirect("/books?action=showCustomer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
