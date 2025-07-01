/**
 * 
 */
package egovframework.example.dept.service;

import egovframework.example.common.Criteria;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 * DB 부서테이블 정보를 임시저장하는 클래스
 * 카멜표기법
 * 자바필드==테이블 컬럼
 * 메소드: getter setter 생서자들(모든필드1, 모두없는1) -> 롬북어노테이션으로 사용
 * 부가기능: ToString() 오버라이딩, Equals, HashCode
 * @EqualsAndHashCode(callSuper = false) 상속할때 부모필드 제외
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class DeptVO extends Criteria {
	private int dno; //기본키 시퀀스
	private String dname; 
	private String loc; 

}
