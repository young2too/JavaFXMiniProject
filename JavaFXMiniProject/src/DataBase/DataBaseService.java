package DataBase;

import java.util.ArrayList;

public interface DataBaseService {
	public int insert(Member member);
	public boolean loginCheck(String id, String pw);
	public boolean overlapCheck(String id);
	
	public ArrayList<Member> select();
	public Member SearchMemberByID(String id);
	
	public boolean excuteSql(String sql);
	public int calcRankByID(String id);
	
	public boolean commit();
	
}
