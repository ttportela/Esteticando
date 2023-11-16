package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.DataObject;
import ifpr.modelo.Procedimento;
import ifpr.modelo.ProdutoPrateleira;

public abstract class Fabrica<K extends DataObject> {
	
	public abstract List<K> listar();
	
	public abstract boolean salvar(K item);
	
	public abstract boolean excluir(K item);
	
	protected abstract K instanciar(ResultSet rs) throws SQLException;
	
	public List<K> listar(String sql) {

		ArrayList<K> lista = new ArrayList<>();
		
		Connection con = Conexao.getInstancia().getCon();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				lista.add(instanciar(rs));
			}
			
			// Feche recursos
			rs.close();
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}

	protected boolean excluir(String tabela, K item) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql = "DELETE FROM "+tabela+" WHERE id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}
