/**
 * 
 */
package egovframework.example.auth.service;

import egovframework.example.common.Criteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 * vo=테이블
 * EMAIL	VARCHAR2(1000 BYTE)
PASSWORD	VARCHAR2(1000 BYTE)
NAME	VARCHAR2(1000 BYTE)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberVO extends Criteria{
	private String email; //기본키
	private String password; //암호
	private String name; //유저명
	
}
