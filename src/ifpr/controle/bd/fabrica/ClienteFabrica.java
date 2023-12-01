package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.Cliente;

public class ClienteFabrica extends Fabrica<Cliente> {

	@Override
	public List<Cliente> listar() {
		return super.listar("SELECT * FROM clientes");
	}

	@Override
	public boolean salvar(Cliente item) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql;
		if (item.ehNovo()) {
			sql = "INSERT INTO clientes "
					+ "(nome,cpf,email,telefone,endereco,dt_nascimento,"
					+ "tratamento,observacoes) VALUES (?,?,?,?,?,?,?,?)";
		} else {
			sql = "UPDATE clientes "
					+ "SET nome=?, cpf=?, email=?, telefone=? "
					+ "endereco=?, dt_nascimento=?, tratamento=?, observacoes=? "
					+ "WHERE id=?";
		}
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getNome());
			stmt.setString(2, item.getCpf());
			stmt.setString(3, item.getEmail());
			stmt.setString(4, item.getTelefone());
			stmt.setString(5, item.getEndereco());
			
			// Aqui preciso converter para uma Date do sql:
			stmt.setDate(6, Date.valueOf(item.getDataNascimento()));
			
			stmt.setString(7, item.getTratamento());
			stmt.setString(8, item.getObservacoes());
			
			if (!item.ehNovo())
				stmt.setInt(9, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean excluir(Cliente item) {
		return super.excluir("clientes", item);
	}

	@Override
	protected Cliente instanciar(ResultSet rs) throws SQLException {
		return instanciar(rs, "");
	}
	
	protected Cliente instanciar(ResultSet rs, String prefixo) throws SQLException {
		Cliente item = new Cliente();
		item.setId(rs.getInt(prefixo+"id"));
		item.setNome(rs.getString(prefixo+"nome"));
		item.setCpf(rs.getString(prefixo+"cpf"));
		item.setEmail(rs.getString(prefixo+"email"));
		item.setTelefone(rs.getString(prefixo+"telefone"));
		item.setEndereco(rs.getString(prefixo+"endereco"));
		item.setDataNascimento(rs.getDate(prefixo+"dt_nascimento").toLocalDate());
		item.setTratamento(rs.getString(prefixo+"tratamento"));
		item.setObservacoes(rs.getString(prefixo+"observacoes"));
		return item;
	}

	public Cliente busca(int id) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql = "SELECT * FROM clientes WHERE id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return instanciar(rs);
			}
			
			// Feche recursos
			rs.close();
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return null;
	}

}
