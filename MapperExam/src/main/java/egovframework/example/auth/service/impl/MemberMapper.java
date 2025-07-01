/**
 * 
 */
package egovframework.example.auth.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.auth.service.MemberVO;

/**
 * @author user
 *
 */
@Mapper
public interface MemberMapper {
	public MemberVO authenticate(MemberVO memberVO); //로그인
	public void register(MemberVO memberVO);         //회원가입
}
