package ifpr.controle.bd.fabrica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.Agendamento;
import ifpr.modelo.Cliente;
import ifpr.modelo.Profissional;

public class AgendamentoFabrica extends Fabrica<Agendamento> {

	@Override
	public List<Agendamento> listar() {
		ArrayList<Agendamento> lista = new ArrayList<>();
		
		Connection con = Conexao.getInstancia().getCon();
		
		String sql = "SELECT *, clientes.nome AS cli_nome, "
				+ "profissionais.nome AS pro_nome FROM agendamentos "
				+ "INNER JOIN clientes ON clientes.id = agendamentos.id_cliente "
				+ "INNER JOIN profissionais ON profissionais.id = agendamentos.id_profissional "
				+ "ORDER BY agendamentos.data ASC";
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

	@Override
	public boolean salvar(Agendamento item) {		
		// Como aqui vamos trabalhar com varias tabelas ao mesmo tempo, 
		// temos que fazer tudo em uma só transação:
		Connection con = Conexao.getInstancia().getCon();		
		String sql;
		if (item.ehNovo()) {
			sql = "INSERT INTO agendamentos "
					+ "(id_cliente,id_profissional,data) VALUES (?,?,?)";
		} else {
			sql = "UPDATE agendamentos "
					+ "SET id_cliente=?, id_profissional=?, data=? "
					+ "WHERE id=?";
		}
		
		try {		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, item.getCliente().getId());
			stmt.setInt(2, item.getProfissional().getId());

			// Aqui preciso converter para uma Date do sql:
			stmt.setDate(3, Date.valueOf(item.getData()));
			
			if (!item.ehNovo())
				stmt.setInt(4, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean excluir(Agendamento item) {
		return super.excluir("agendamentos", item);
	}

	@Override
	protected Agendamento instanciar(ResultSet rs) throws SQLException {
		Agendamento item = new Agendamento();
		item.setId(rs.getInt("id"));
		item.setData(rs.getDate("data").toLocalDate());
		
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
				
		return item;
	}

}
