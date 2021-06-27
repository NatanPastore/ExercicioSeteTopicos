package controller;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import models.Produto;
import services.Services;

public class Main{

	public static void main(String[] args) throws SQLException {

		PreparedStatement stmt;
		Connection conn = ConnectionFactory.getConnection();
		Services service = new Services();
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			
			stmt = conn.prepareStatement("SELECT * FROM tb_product");
			ResultSet resultado = stmt.executeQuery();
			
			while (resultado.next()) {
				
				Produto produto = service.instanciarProduto(resultado);
				produtos.add(produto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
		
	}
}