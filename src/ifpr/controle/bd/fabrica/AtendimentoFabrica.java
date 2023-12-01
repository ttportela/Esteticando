package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.Atendimento;
import ifpr.modelo.Cliente;
import ifpr.modelo.Procedimento;
import ifpr.modelo.Produto;
import ifpr.modelo.ProdutoPrateleira;
import ifpr.modelo.Profissional;

public class AtendimentoFabrica extends Fabrica<Atendimento> {

	@Override
	public List<Atendimento> listar() {
		ArrayList<Atendimento> lista = new ArrayList<>();
		
		Connection con = Conexao.getInstancia().getCon();
		
		// Eu só listo os dados da tabela atendimentos, não listo os produtos agora
		String sql = "SELECT *, clientes.nome AS cli_nome, "
				+ "profissionais.nome AS pro_nome FROM atendimentos "
				+ "INNER JOIN clientes ON clientes.id = atendimentos.id_cliente "
				+ "INNER JOIN profissionais ON profissionais.id = atendimentos.id_profissional "
				+ "ORDER BY atendimentos.data DESC";
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
	
	public List<Produto> listarProdutos(Atendimento item) {
		ArrayList<Produto> lista = new ArrayList<>();
		
		// Eu vou usar as fábricas específicas para instanciar os objetos:
		ProdutoFabrica pf = new ProdutoFabrica();
		ProcedimentoFabrica prf = new ProcedimentoFabrica();
		
		Connection con = Conexao.getInstancia().getCon();
		
		try {
			// Eu só listo os dados da tabela atendimentos, não listo os produtos agora
			String sql = "SELECT *, produto_prateleira.* FROM atendimento_produto "
					+ "INNER JOIN produto_prateleira ON produto_prateleira.id = atendimento_produto.id_produto "
					+ "WHERE atendimento_produto.id_atendimento = ?";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				lista.add(pf.instanciar(rs));
			}
			
			// Feche recursos
			rs.close();
			stmt.close();
			
			sql = "SELECT *, procedimentos.* FROM atendimento_procedimento "
					+ "INNER JOIN procedimentos ON procedimentos.id = atendimento_procedimento.id_procedimento "
					+ "WHERE atendimento_procedimento.id_atendimento = ?";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				lista.add(prf.instanciar(rs));
			}
			
			// Feche recursos
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return lista;
	}

	@Override
	public boolean salvar(Atendimento item) {		
		// Como aqui vamos trabalhar com varias tabelas ao mesmo tempo, 
		// temos que fazer tudo em uma só transação:
		Connection con = Conexao.getInstancia().getCon();
		try {
			con.setAutoCommit(false);
		
			String sql;
			if (item.ehNovo()) {
				// No insert vou retornar o ID para associar com os itens 
				sql = "INSERT INTO atendimentos "
						+ "(id_cliente,id_profissional,data,observacoes)"
						+ " VALUES (?,?,?,?) RETURNING id";
			} else {
				sql = "UPDATE atendimentos "
						+ "SET id_cliente=?, id_profissional=?, data=?, observacoes=? "
						+ "WHERE id=?";
			}
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getCliente().getId());
			stmt.setInt(2, item.getProfissional().getId());

			// Aqui preciso converter para uma Date do sql:
			stmt.setDate(3, Date.valueOf(item.getData()));
			
			stmt.setString(4, item.getObservacoes());
			
			if (!item.ehNovo()) {
				stmt.setInt(5, item.getId());
				stmt.executeUpdate();
			} else {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				item.setId(rs.getInt("id"));
				
				// Vamos salvar os itens do atendimento, mas apenas se for um novo atendimento
				// primeiro na tabela de relação com produtos
				sql = "INSERT INTO atendimento_produto "
						+ "(id_atendimento,id_produto) VALUES (?,?)";
				stmt = con.prepareStatement(sql);
				
				for (Produto i : item.getItens()) {
					if (i instanceof ProdutoPrateleira) {
						stmt.setInt(1, item.getId());
						stmt.setInt(2, i.getId());
						stmt.addBatch();
					}
				}
				// executa os inserts:
				stmt.executeBatch();
				
				// segundo na tabela de relação com procedimentos
				sql = "INSERT INTO atendimento_procedimento "
						+ "(id_atendimento,id_procedimento) VALUES (?,?)";
				stmt = con.prepareStatement(sql);
				
				for (Produto i : item.getItens()) {
					if (i instanceof Procedimento) {
						stmt.setInt(1, item.getId());
						stmt.setInt(2, i.getId());
						stmt.addBatch();
					}
				}
				
				// executa os inserts:
				stmt.executeBatch();
			}
			
			// Se deu tudo certo, vamos confimar as operações no banco:
			con.commit();
			con.setAutoCommit(true);
			
		} catch (SQLException e) {
			// Se acontecer algum erro, devemos voltar o BD ao estado anterior:
			try {
				con.rollback();
			} catch (SQLException e1) {	
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean excluir(Atendimento item) {
		// Como aqui vamos trabalhar com varias tabelas ao mesmo tempo, 
		// temos que fazer tudo em uma só transação:
		Connection con = Conexao.getInstancia().getCon();
		try {
			con.setAutoCommit(false);
			
			String sql = "DELETE FROM atendimento_produto WHERE id_atendimento=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());

			stmt.executeUpdate();
			
			sql = "DELETE FROM atendimento_procedimento WHERE id_atendimento=?";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());

			stmt.executeUpdate();
			
			sql = "DELETE FROM atendimentos WHERE id=?";

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getId());

			stmt.executeUpdate();
			
			// Se deu tudo certo, vamos confimar as operações no banco:
			con.commit();
			con.setAutoCommit(true);
				
		} catch (SQLException e) {
			// Se acontecer algum erro, devemos voltar o BD ao estado anterior:
			try {
				con.rollback();
			} catch (SQLException e1) {	
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			return false;
		}
		return super.excluir("atendimentos", item);
	}

	@Override
	protected Atendimento instanciar(ResultSet rs) throws SQLException {
		Atendimento item = new Atendimento();
		item.setId(rs.getInt("id"));
		item.setData(rs.getDate("data").toLocalDate());
		item.setObservacoes(rs.getString("observacoes"));
		
		// Para listar os atendimentos, nós não precisamos de todos
		// os dados de clientes e profissionais, apenas o id e nome.
		Cliente c = new Cliente();
		c.setId(rs.getInt("id_cliente"));
		c.setNome(rs.getString("cli_nome"));
		item.setCliente(c);
		
		Profissional p = new Profissional();
		p.setId(rs.getInt("id_profissional"));
		p.setNome(rs.getString("pro_nome"));
		item.setProfissional(p);
		
		/* OU Você poderia buscar o cliente e profisional completo do BD...
		// Criamos um método para buscar o cliente deste atendimento:
		ClienteFabrica fabCli = new ClienteFabrica();
		Cliente c = fabCli.busca(rs.getInt("id_cliente"));
		item.setCliente(c);
		
		// Criamos um método para buscar o profissional deste atendimento:
		ProfissionalFabrica fabPro = new ProfissionalFabrica();
		Profissional p = fabPro.busca(rs.getInt("id_profissional"));
		item.setProfissional(p);
		*/
		
		// Agora vamos listar os itens vendidos no atendimento:
		
				
		return item;
	}

}
