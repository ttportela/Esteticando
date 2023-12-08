package ifpr.controle.bd.fabrica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ifpr.controle.bd.Conexao;
import ifpr.modelo.Profissional;

public class ProfissionalFabrica extends Fabrica<Profissional> {

	@Override
	public List<Profissional> listar() {
		return super.listar("SELECT * FROM profissionais");
	}

	@Override
	public boolean salvar(Profissional item) {
		Connection con = Conexao.getInstancia().getCon();
		
		// Vamos ver se precisa salvar a senha:
		boolean salvaSenha = false;
		if ( item.ehNovo() || (!item.ehNovo() && item.getSenha().length() > 0) ) {
			item.setSenha( criaHashSenha(item.getSenha()) );
			salvaSenha = true;
		}
		
		String sql;
		if (item.ehNovo()) { // Sempre salva a senha para novos registros:
			sql = "INSERT INTO profissionais "
				+ "(nome,cpf,email,telefone,endereco,dt_nascimento,"
				+ "especialidade,senha) VALUES (?,?,?,?,?,?,?,?)";
		} else {
			if (salvaSenha)
				sql = "UPDATE profissionais "
					+ "SET nome=?, cpf=?, email=?, telefone=?, "
					+ "endereco=?, dt_nascimento=?, especialidade=?, senha=? "
					+ "WHERE id=?";
			else
				sql = "UPDATE profissionais "
					+ "SET nome=?, cpf=?, email=?, telefone=?, "
					+ "endereco=?, dt_nascimento=?, especialidade=? "
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
			
			stmt.setString(7, item.getEspecialidade());
			
			// Se precisa salvar a senha o parametro 8 é a senha
			if (salvaSenha)
				stmt.setString(8, item.getSenha());
			
			// No caso do UPDADE quando salvamos a senha o ID vira o parâmetro 9:
			if (!item.ehNovo() && !salvaSenha)
				stmt.setInt(8, item.getId());
			else if (!item.ehNovo() && salvaSenha)
				stmt.setInt(9, item.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String criaHashSenha(String senha) {
		// Aqui codificamos a senha com md5 (não pode ser descriptografada),
		// Precisamos tomar cuidado quando for alteração do cadastro:
		try {
			// Algoritmo de criptografia com Message Digest 5:
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(senha.getBytes());
		    byte[] digest = md.digest();
		    String hash = new String(digest).toUpperCase();
		    
		    return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean excluir(Profissional item) {
		return super.excluir("profissionais", item);
	}

	public Profissional busca(int id) {
		Connection con = Conexao.getInstancia().getCon();
		
		String sql = "SELECT * FROM profissionais WHERE id=?";
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

	@Override
	protected Profissional instanciar(ResultSet rs) throws SQLException {
		Profissional item = new Profissional();
		item.setId(rs.getInt("id"));
		item.setNome(rs.getString("nome"));
		item.setCpf(rs.getString("cpf"));
		item.setEmail(rs.getString("email"));
		item.setTelefone(rs.getString("telefone"));
		item.setEndereco(rs.getString("endereco"));
		item.setDataNascimento(rs.getDate("dt_nascimento").toLocalDate());
		item.setEspecialidade(rs.getString("especialidade"));
		// A senha eu busco o hash, não pode salvar isso no banco de dados depois
		item.setSenha(rs.getString("senha"));
		return item;
	}

}
