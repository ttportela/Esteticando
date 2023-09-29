package ifpr.controle;

import java.util.ArrayList;
import java.util.List;

import ifpr.modelo.ProdutoPrateleira;

public class ProdutoFabrica {
	
	private static ArrayList<ProdutoPrateleira> BD = new ArrayList<ProdutoPrateleira>();
	
	public List<ProdutoPrateleira> listar() {
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
