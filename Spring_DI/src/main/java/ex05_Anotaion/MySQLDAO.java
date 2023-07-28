package ex05_Anotaion;

import org.springframework.stereotype.Service;

@Service
public class MySQLDAO implements DAO{
	public MySQLDAO() {
		System.out.println("MySQL 생성자");
	}
	
	@Override
	public void prn() {
		System.out.println("MySQL 메서드");
	}
}
