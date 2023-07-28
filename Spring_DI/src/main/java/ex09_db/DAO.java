package ex09_db;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class DAO {
	// (스프링에서 사용) 실제 사용할 클래스 -> SqlSessionTemplate
	// 클래스를 자료형으로 되어있으면 무조건 객체로 만들어라 xml(root-context에서)
	// jsp 에서 사용한 SqlSession과 사용법이 같다.
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<VO> getList(){
		List<VO> list = sqlSessionTemplate.selectList("members.list");
		return list;
	}
	
}
