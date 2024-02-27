package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

public interface BookDao {

	public int insert(Map<String, Object> map);

	public Map<String, Object> selectBook(Map<String, Object> map);

	public int update(Map<String, Object> map);

	public int delete(Map<String, Object> map);

	public List<Map<String, Object>> selectBookList(Map<String, Object> map);

}
