package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import javaDB.ConnectionFactory;
import modelo.LDL;
import modelo.Medico;
import modelo.Paciente;
import seguranca.Criptografia;

public class LDLDAO {
	
	private Connection con;
	MedicoDAO daoMed = new MedicoDAO();
	
	
	public LDLDAO() throws SQLException{
		this.con =  ConnectionFactory.getConnection();
	}
	
	
	public void adiciona(String resultado, int id_paciente, int id_medico , String chave) throws Exception {
		
		if (ProcuraMedicoEPaciente(id_paciente, id_medico) ) {
			SecretKey chave2 = Criptografia.criarChaveSecreta(chave);
			
			String sql = "INSERT INTO LDL (resultado, "
					+ "id_paciente, id_medico) VALUES (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Criptografia.criptografa(resultado, chave2));
			stmt.setInt(2, id_paciente);
			stmt.setInt(3, id_medico);
					
			stmt.execute();
			stmt.close();
		}
	}
	
	public List<LDL> getLista() throws SQLException{
		String sql = "SELECT a.id, a.resultado, m.id, m.nome, m.cpf, m.especialidade,"
				+ "p.id, p.nome, p.cpf FROM LDL a "
				+ "JOIN medico m ON a.id_medico = m.id "
				+ "JOIN paciente p ON a.id_paciente = p.id";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rset = stmt.executeQuery();
		
		List<LDL> exames = new ArrayList<LDL>();
		
		while (rset.next()) {
			Medico medico = new Medico(rset.getInt("m.id"),rset.getString("m.nome") ,
					rset.getString("m.cpf"), rset.getString("m.especialidade"));
			Paciente paciente = new Paciente(rset.getInt("p.id"),rset.getString("p.nome") ,
					rset.getString("p.cpf"));
			LDL exame = new LDL(rset.getInt("a.id"), rset.getString("a.resultado"), paciente, medico);
			exames.add(exame);
			
		}
		rset.close();
		stmt.close();
		
		return exames;
	}
	
	public LDL getLDLId(int id) throws SQLException {
		String sql = "SELECT a.id, a.resultado, m.id, m.nome, m.cpf, m.especialidade,"
				+ "p.id, p.nome, p.cpf FROM LDL a "
				+ "JOIN medico m ON a.id_medico = m.id "
				+ "JOIN paciente p ON a.id_paciente = p.id";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rset = stmt.executeQuery();
		
		if (rset.next()) {
			Medico medico = new Medico(rset.getInt("m.id"),rset.getString("m.nome") ,
					rset.getString("m.cpf"), rset.getString("m.especialidade"));
			Paciente paciente = new Paciente(rset.getInt("p.id"),rset.getString("p.nome") ,
					rset.getString("p.cpf"));
			LDL exame = new LDL(rset.getInt("a.id"), rset.getString("a.resultado"), paciente, medico);
			return exame;
		}
		return null;
	}
	
	public void atualiza(int id, String resultado, int id_paciente, int id_medico , String chave) throws Exception {
		
		LDL ac = getLDLId(id);
		
		if (ac != null && ProcuraMedicoEPaciente(id_paciente, id_medico)) {
			SecretKey chave2 = Criptografia.criarChaveSecreta(chave);
			
			String sql = "update LDL set resultado = ? , id_paciente = ? , id_paciente = ? where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Criptografia.criptografa(resultado, chave2));
			stmt.setInt(2, id_paciente);
			stmt.setInt(3, id_medico);
			stmt.setInt(4,id);
			stmt.execute();
			stmt.close();
		}	
	}
	
	public void deleta(int id) throws Exception {
		
		LDL ac = getLDLId(id);
		
		if (ac != null) {
			String sql = "delete from LDL where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		}
		
			
	}
	
	private boolean ProcuraMedicoEPaciente(int id_paciente, int id_medico) throws SQLException {
		MedicoDAO daoMed = new MedicoDAO();
		PacienteDAO daoPac = new PacienteDAO();
		
		return daoPac.getPacientebyId(id_paciente) && daoMed.getMedicobyId(id_medico);
	}

}
