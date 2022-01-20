/**
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 *
 * @author Wellington
 */
public class DAO {
	/*
	 * Múdulo de Conexão
	 */

	/** The driver. */
	// Parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "123qweasd";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	/*
	 * CRUD CREATE
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO contatos (nome,fone,email) VALUES (?,?,?)";

		try {
			// Abrir conexão
			Connection con = conectar();

			// Preparar a query para execução
			PreparedStatement pst = con.prepareStatement(create);

			// Substituir Parametors pelo Conteudo das variaves JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();

			// Encerrar a conexão com o banco
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * CRUD READ
	 */

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {

		// Criando Objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();

		String read = "SELECT * FROM contatos ORDER BY nome";

		try {
			// Abrir conexão
			Connection con = conectar();

			// Preparar a query para execução
			PreparedStatement pst = con.prepareStatement(read);

			// Armazenar temporariamente os dados obtidos pelo pst
			ResultSet rs = pst.executeQuery();

			// O laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// Variaveis de apoio que recebem os dados
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/*
	 * CRUD UPDATE
	 */
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	// Selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "SELECT * FROM contatos WHERE idcon = ?";

		try {
			// Abrir conexão
			Connection con = conectar();

			// Preparar a query para execução
			PreparedStatement pst = con.prepareStatement(read2);

			pst.setString(1, contato.getIdcon());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	/*
	 * Editar o contato
	 */
	public void alterarContato(JavaBeans contato) {
		String update = "UPDATE contatos SET nome=?, fone=?, email=? WHERE idcon=?";

		try {			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			
			pst.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	/*
	 * Deletar contato
	 * */
	public void deletarContato(JavaBeans contato) {
		String delete = "DELETE FROM contatos WHERE idcon=?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			
			pst.setString(1, contato.getIdcon());
			
			pst.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
