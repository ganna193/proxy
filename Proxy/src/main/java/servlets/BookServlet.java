package servlets;

import core.BookDao;
import db.DatabaseConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.Book;
import core.BookDaoImpl;
import db.*;

public class BookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;   
	 public BookServlet() {
	        super();
	       	    }
	 public void init() {
	        // Retrieve or construct DatabaseConfig with appropriate credentials
	        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/bookstore", "root", "P@$$w0rd");
	        databaseConnection dbConnection = new MySqlDatabaseConnection(config);
	        bookDao = new BookDaoImpl(dbConnection);
	    }
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            List<Book> books = bookDao.getAllBooks();
	            request.setAttribute("books", books);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("Books.jsp");
	            dispatcher.forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException("Database access error occurred", e);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}


}
