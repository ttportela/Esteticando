package ifpr.controle.bd.fabrica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ifpr.modelo.Cliente;

public class ClienteFabrica extends Fabrica<Cliente> {

	@Override
	public List<Cliente> listar() {
		return null;
	}

	@Override
	public boolean salvar(Cliente item) {
		return false;
	}

	@Override
	public boolean excluir(Cliente item) {
		return super.excluir("clientes", item);
	}

	@Override
	protected Cliente instanciar(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
