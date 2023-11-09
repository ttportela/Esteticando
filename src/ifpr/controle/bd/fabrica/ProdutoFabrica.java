package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.ProdutoPrateleira;

public class ProdutoFabrica {
	
	public List<ProdutoPrateleira> listar() {

		ArrayList<ProdutoPrateleira> lista = new ArrayList<>();
		
		Connection con = Conexao.getInstancia().getCon();
		String sql = "SELECT * FROM produto_prateleira";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ProdutoPrateleira item = new ProdutoPrateleira();
				item.setId(rs.getInt("id"));
				item.setNome(rs.getString("nome"));
				item.setDescricao(rs.getString("descricao"));
				item.setPreco(rs.getDouble("preco"));
				item.setQuantidadeDisponivel(rs.getInt("qtd_disponivel"));
				lista.add(item);
			}
			
			// Feche recursos
			rs.close();
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}
	
	public boolean salvar(ProdutoPrateleira obj) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql;
		if (obj.ehNovo()) {
			sql = "INSERT INTO produto_prateleira "
					+ "(nome,descricao,preco,qtd_disponivel) VALUES (?,?,?,?)";
		} else {
			sql = "UPDATE produto_prateleira "
					+ "SET nome=?, descricao=?, preco=?, qtd_disponivel=? "
					+ "WHERE id=?";
		}
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getDescricao());
			stmt.setDouble(3, obj.getPreco());
			stmt.setInt(4, obj.getQuantidadeDisponivel());
			
			if (!obj.ehNovo())
				stmt.setInt(5, obj.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean excluir(ProdutoPrateleira obj) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql = "DELETE FROM produto_prateleira WHERE id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, obj.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
