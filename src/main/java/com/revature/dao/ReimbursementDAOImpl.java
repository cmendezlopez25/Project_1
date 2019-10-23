package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private Connection conn = ConnectionFactory.getConnection();
	private String schema = "proj1_release";
	{
		try {
			conn.setSchema(schema);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String createSql = "insert into reimbursement(id, type, amount, status, datecreated, datelastmodified, \"owner\") values(?, ?, ?, ?, ?, ?, ?)";
	private String readOneSql = "select * from reimbursement where id=?";
	private String updateSql = "update reimbursement set amount=?, status=?, datelastmodified=? where id=?";
	private String deleteSql = "delete from reimbursement where id=?";
	private String readAllSql = "select * from reimbursement";
	private String readByUserSql = "select * from reimbursement where \"owner\"=?";
	private String readForSupervisorSql = "select * from reimbursement where \"owner\" in (select username from users where supervisor=?)";

	@Override
	public boolean createReimbursement(Reimbursement reimburse) {
		if (reimburse == null) {
			throw new NullPointerException();
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(createSql);
			stmt.setInt(1, reimburse.getId());
			stmt.setString(2, reimburse.getType().name());
			stmt.setDouble(3, reimburse.getAmount());
			stmt.setString(4, reimburse.getStatus().name());
			stmt.setDate(5, Date.valueOf(reimburse.getDateCreated()));
			stmt.setDate(6, Date.valueOf(reimburse.getDateLastModified()));
			stmt.setString(7, reimburse.getOwnerUserName());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		Reimbursement reimburse = null;

		try {
			PreparedStatement stmt = conn.prepareStatement(readOneSql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				reimburse = new Reimbursement();
				reimburse.setId(rs.getInt(1));
				reimburse.setType(Reimbursement.ReimbursementType.valueOf(rs.getString(2)));
				reimburse.setAmount(rs.getDouble(3));
				reimburse.setStatus(Reimbursement.ReimbursementStatus.valueOf(rs.getString(4)));
				reimburse.setDateCreated(rs.getDate(5).toLocalDate());
				reimburse.setDateLastModified(rs.getDate(6).toLocalDate());
				reimburse.setOwnerUserName(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			reimburse = null;
		}
		return reimburse;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimburse) {
		if (reimburse == null) {
			throw new NullPointerException();
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(updateSql);
			stmt.setDouble(1, reimburse.getAmount());
			stmt.setString(2, reimburse.getStatus().name());
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setInt(4, reimburse.getId());
			int result = stmt.executeUpdate();
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteReimbursementById(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		try {
			ResultSet rs = conn.prepareStatement(readAllSql).executeQuery();
			while (rs != null && rs.next()) {
				Reimbursement reimburse = new Reimbursement();
				reimburse.setId(rs.getInt(1));
				reimburse.setType(Reimbursement.ReimbursementType.valueOf(rs.getString(2)));
				reimburse.setAmount(rs.getDouble(3));
				reimburse.setStatus(Reimbursement.ReimbursementStatus.valueOf(rs.getString(4)));
				reimburse.setDateCreated(rs.getDate(5).toLocalDate());
				reimburse.setDateLastModified(rs.getDate(6).toLocalDate());
				reimburse.setOwnerUserName(rs.getString(7));
				reimburseList.add(reimburse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsByUser(User user) {
		if (user == null) {
			throw new NullPointerException();
		}

		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		try {
			PreparedStatement stmt = conn.prepareStatement(readByUserSql);
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Reimbursement reimburse = new Reimbursement();
				reimburse.setId(rs.getInt(1));
				reimburse.setType(Reimbursement.ReimbursementType.valueOf(rs.getString(2)));
				reimburse.setAmount(rs.getDouble(3));
				reimburse.setStatus(Reimbursement.ReimbursementStatus.valueOf(rs.getString(4)));
				reimburse.setDateCreated(rs.getDate(5).toLocalDate());
				reimburse.setDateLastModified(rs.getDate(6).toLocalDate());
				reimburse.setOwnerUserName(rs.getString(7));
				reimburseList.add(reimburse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}

	@Override
	public List<Reimbursement> getAllReimbursementsForSupervisor(User user) {
		if (user == null) {
			throw new NullPointerException();
		}
		
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		try {
			PreparedStatement stmt = conn.prepareStatement(readForSupervisorSql);
			stmt.setString(1, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			while (rs != null && rs.next()) {
				Reimbursement reimburse = new Reimbursement();
				reimburse.setId(rs.getInt(1));
				reimburse.setType(Reimbursement.ReimbursementType.valueOf(rs.getString(2)));
				reimburse.setAmount(rs.getDouble(3));
				reimburse.setStatus(Reimbursement.ReimbursementStatus.valueOf(rs.getString(4)));
				reimburse.setDateCreated(rs.getDate(5).toLocalDate());
				reimburse.setDateLastModified(rs.getDate(6).toLocalDate());
				reimburse.setOwnerUserName(rs.getString(7));
				reimburseList.add(reimburse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimburseList;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
