package ex05_Anotaion;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component //("dao") // <= 아이디 (서비스에 있는 전역변수 이름이랑 같게 함)
@Repository
public class OracleDAO implements DAO{
	public OracleDAO() {
		System.out.println("오라클 생성자");
	}
	
	@Override
	public void prn() {
		System.out.println("오라클 메서드");
		
	}
}
