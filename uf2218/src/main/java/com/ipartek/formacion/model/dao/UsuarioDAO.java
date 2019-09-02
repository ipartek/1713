package com.ipartek.formacion.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.model.ConnectionManager;
import com.ipartek.formacion.model.pojo.Usuario;
import com.ipartek.formacion.model.pojo.Video;

public class UsuarioDAO {

	private static UsuarioDAO INSTANCE = null;
	

	private UsuarioDAO() {
		super();
	}

	public static synchronized UsuarioDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAO();
		}
		return INSTANCE;
	}

	/**
	 * Comprueba si existe el usuario en la BD. Lo busca por nombre y contraseña.
	 * 
	 * @param nombre
	 * @param contrasena
	 * @return Usuario con datos si existe. NULL en caso de no existir.
	 */
	public Usuario existe(String nombre, String contrasena) {

		Usuario usuario = null;

		String sql = " SELECT id, nombre, contrasena FROM usuario WHERE nombre = ? AND contrasena = ?;";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);) {

			// Sustituir ? por parámetros.
			pst.setString(1, nombre);
			pst.setString(2, contrasena);

			// Ejecutar sentencia y obtener resultado.
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					usuario = new Usuario();

					usuario.setId(rs.getInt("id"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setContrasena(rs.getString("contrasena"));

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}
	
	public ArrayList<Usuario> getAll() {
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT id, nombre, contrasena FROM usuario ORDER BY id DESC LIMIT 500";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				/*
				Video v = new Video();
				v.setId( rs.getInt("id") );
				v.setNombre( rs.getString("nombre"));
				v.setCodigo( rs.getString("codigo"));
				*/
				lista.add( mapper(rs) );
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return lista;
	}
	
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario u = new Usuario();
		u.setId(rs.getInt("id"));
		u.setNombre(rs.getString("nombre"));
		u.setContrasena(rs.getString("contrasena"));
		return u;
	}

}
