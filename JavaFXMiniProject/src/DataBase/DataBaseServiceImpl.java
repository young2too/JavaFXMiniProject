package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseServiceImpl implements DataBaseService {
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//final static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:XE";//VM웨어 IP DB
	final static String URL = "jdbc:oracle:thin:@localhost:1521:XE"; //제출용 URL
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
			ps.setString(1, member.getId());
			ps.setString(2, member.getPw());
			ps.setString(3, member.getName());
			ps.setString(4, member.getGender());
			ps.setString(5, member.getAge());
			ps.setInt(6, member.getLike());
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
		

		return result;
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
				return false;//결과가 없다면 중복되지 않음
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

}
