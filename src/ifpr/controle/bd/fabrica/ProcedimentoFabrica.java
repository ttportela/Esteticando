package ifpr.controle.bd.fabrica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ifpr.modelo.Procedimento;

public class ProcedimentoFabrica extends Fabrica<Procedimento> {
	
	public List<Procedimento> listar() {
		return super.listar("SELECT * FROM procedimentos");
	}
	
	public boolean salvar(Procedimento item) {
		
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
		item.setDuracaoEstimada(rs.getInt("ducaracao"));
		return item;
	}

}
