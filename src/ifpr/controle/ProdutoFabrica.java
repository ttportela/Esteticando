package ifpr.controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.ProdutoPrateleira;

public class ProdutoFabrica {
	
	private static ArrayList<ProdutoPrateleira> BD = new ArrayList<ProdutoPrateleira>();
	
	public static void main(String[] args) {
		ProdutoFabrica f = new ProdutoFabrica();
		f.listar();
	}
	
	public List<ProdutoPrateleira> listar() {
		Connection con = Conexao.getInstancia().getCon();
		String sql = "SELECT * FROM produto_prateleira";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("nome"));
				System.out.println(rs.getDouble("preco"));
			}
			
			// Feche recursos
			rs.close();
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return BD;
	}
	
	public boolean salvar(ProdutoPrateleira obj) {
		if (BD.contains(obj)) {
			return true;
		} else {
			BD.add(obj);
			return true;
		}
	}
	
	public boolean excluir(ProdutoPrateleira obj) {
		if (BD.contains(obj)) {
			BD.remove(obj);
			return true;
		} else {
			return false;
		}
	}

}
