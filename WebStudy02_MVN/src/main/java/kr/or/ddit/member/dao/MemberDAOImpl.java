package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.common.exception.PersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		
		String memId = inputData.getMemId();
		String memPass = inputData.getMemPass();
		StringBuffer sql = new StringBuffer();
		sql.append(" select mem_id, mem_pass, mem_name, ");
		sql.append(" mem_hp, mem_mail ");
		sql.append(" from member      ");
		sql.append(" where mem_id= ? and mem_pass= ? ");
		try(
			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			System.out.println(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			
			ResultSet rs = pstmt.executeQuery();
			MemberVO vo = null;
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMemId(rs.getString("MEM_ID"));
				vo.setMemPass(rs.getString("MEM_PASS"));
				vo.setMemName(rs.getString("MEM_NAME"));
				vo.setMemHp(rs.getString("MEM_HP"));
				vo.setMemMail(rs.getString("MEM_MAIL"));
			}
			return vo;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}
