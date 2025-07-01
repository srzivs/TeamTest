/**
 * 
 */
package egovframework.example.auth.service;

/**
 * @author user
 *
 */
public interface MemberService {
	MemberVO authenticate(MemberVO loginVO) throws Exception; //로그인
	void register(MemberVO memberVO) throws Exception;        //회원가입
}
