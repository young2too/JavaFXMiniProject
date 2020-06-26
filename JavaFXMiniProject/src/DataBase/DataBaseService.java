package DataBase;

public interface DataBaseService {
	public int insert(Member member);
	public boolean loginCheck(String id, String pw);
	public boolean overlapCheck(String id);
}
