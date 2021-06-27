package servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ConnectionFactory;
import models.Produto;
import services.Services;

@WebServlet(" ")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		ServletContext contexto = req.getServletContext();
		
		Connection conn = ConnectionFactory.getConnection();
		Services service = new Services();
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement("SELECT * FROM tb_product");
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				Produto produto = service.instanciarProduto(result);
				produtos.add(produto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		contexto.setAttribute("produtos", produtos);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Index.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	
}