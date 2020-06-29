package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseServiceImpl implements DataBaseService {
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	// final static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:XE";//VM웨어
	// IP DB
	final static String URL = "jdbc:oracle:thin:@localhost:1521:XE"; // 제출용 URL
	final static String USER = "system";
	final static String PASS = "oracle";

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("드라이버 등록 실패");
		}
	}

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		conn = null;
		int res = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "insert into member values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getID());
			ps.setString(2, member.getPW());
			ps.setString(3, member.getNickName());
			ps.setString(4, member.getQuiz());
			ps.setString(5, member.getAnswer());
			ps.setInt(6, member.getScore());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean loginCheck(String id, String pw) {
		// TODO Auto-generated method stub
		boolean result = true;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "select * from member where id=? and pw=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();

			while (!rs.next()) {
				return false;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return true;
	}

	@Override
	public boolean overlapCheck(String id) {
		// TODO Auto-generated method stub
		boolean result = true;

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "select * from member where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (!rs.next()) {
				return false;// 결과가 없다면 중복되지 않음
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	@Override
	public ArrayList<Member> select() {
		ArrayList<Member> resultList = new ArrayList<Member>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "select * from member";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Member m = new Member();
				m.setID(rs.getString("id"));
				m.setPW(rs.getString("pw"));
				m.setNickName(rs.getString("nickname"));
				m.setQuiz(rs.getString("quiz"));
				m.setAnswer(rs.getString("answer"));
				m.setScore(rs.getInt("score"));

				resultList.add(m);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// TODO Auto-generated method stub
		return resultList;
	}

	@Override
	public Member SearchMemberByID(String id) {
		// TODO Auto-generated method stub
		Member m = new Member();

		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "select * from member where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				m.setID(rs.getString("id"));
				m.setPW(rs.getString("pw"));
				m.setNickName(rs.getString("nickname"));
				m.setQuiz(rs.getString("quiz"));
				m.setAnswer(rs.getString("answer"));
				m.setScore(rs.getInt("score"));

				return m;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return m;
	}

	@Override
	public boolean excuteSql(String sql) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			if(result == 0) {
				return false;
			}
			else {
				return true;
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
