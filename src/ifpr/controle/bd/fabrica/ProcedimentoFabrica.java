package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.Procedimento;

// Nesta fábrica eu usei a simplificação que fizemos com a Fábrica genérica.
public class ProcedimentoFabrica extends Fabrica<Procedimento> {
	
	public List<Procedimento> listar() {
		return super.listar("SELECT * FROM procedimentos");
	}
	
	public boolean salvar(Procedimento item) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql;
		if (item.ehNovo()) {
			sql = "INSERT INTO procedimentos "
					+ "(nome,descricao,preco,duracao) VALUES (?,?,?,?)";
		} else {
			sql = "UPDATE procedimentos "
					+ "SET nome=?, descricao=?, preco=?, duracao=? "
					+ "WHERE id=?";
		}
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, item.getNome());
			stmt.setString(2, item.getDescricao());
			stmt.setDouble(3, item.getPreco());
			stmt.setInt(4, item.getDuracaoEstimada());
			
			if (!item.ehNovo())
				stmt.setInt(5, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean excluir(Procedimento item) {
		return super.excluir("procedimentos", item);
	}

	@Override
	protected Procedimento instanciar(ResultSet rs) throws SQLException {
		Procedimento item = new Procedimento();
		item.setId(rs.getInt("id"));
		item.setNome(rs.getString("nome"));
		item.setDescricao(rs.getString("descricao"));
		item.setPreco(rs.getDouble("preco"));
		item.setDuracaoEstimada(rs.getInt("duracao"));
		return item;
	}

}
