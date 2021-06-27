package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import models.Produto;
import services.Services;

public class Repository {
	
	private Services service;
	private Connection conn;
	
	public Repository() {
		this.conn = ConnectionFactory.getConnection();
	}
	
	public List<Produto> encontrar() {
		
		service = new Services();
		
		PreparedStatement stmt;
		List<Produto> produtos = new ArrayList<Produto>();
		
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
		
		return produtos;
	}
	
	
}