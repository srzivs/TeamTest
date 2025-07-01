/**
 * 
 */
package egovframework.example.auth.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.auth.service.MemberService;
import egovframework.example.auth.service.MemberVO;

/**
 * @author user
 *
 */
@Service
public class MemberServiceimpl extends EgovAbstractServiceImpl implements MemberService {

@Autowired
private MemberMapper memberMapper;
//로그인
	@Override
	public MemberVO authenticate(MemberVO loginVO) throws Exception {
	//DB에 사용자가 있는지 확인
	MemberVO memberVO=memberMapper.authenticate(loginVO);
	//회원이 없으면 회원이 없습니다. (예외처리: 전자정부용)
	if(memberVO==null) throw processException("errors.login");
	//회원이 있으면 암호체크 (틀리면 예외처리)
	if(memberVO!=null) {
		//사용법 BCrypt.checkpw(화면유저암호,유저암호); 같으면 true
		boolean isMatchedPassword = BCrypt.checkpw(loginVO.getPassword(), 
			                                      memberVO.getPassword());
		if(isMatchedPassword==false) throw processException("errors.login");
	}
	//모두 통과하면 마지막에 memberVO 리턴 
	return memberVO;
	}
//회원가입
	@Override
	public void register(MemberVO memberVO) throws Exception {
	//회원정보가 있는지 확인 
	MemberVO cmemberVO=memberMapper.authenticate(memberVO);
	//있으면 예외처리
	if(cmemberVO != null) throw processException("errors.register");
	//입력된 암호를 해싱암호화 처리한다
	String hashedPassword = BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt());
	//암호를 memberVO에 저장. setter
	memberVO.setPassword(hashedPassword);
	//db저장
	memberMapper.register(memberVO);
	
}



}
