/**
 * 
 */
package egovframework.example.auth.web;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import egovframework.example.auth.service.MemberService;
import egovframework.example.auth.service.MemberVO;

/**
 * @author user
 *
 */
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

//로그인 화면 열기	
	@GetMapping("/login.do")
	public String loginView() {
		return "auth/login";
	}
//로그인 처리=조회. 암호가 주소창에 표시되지않게하려고 post사용.
	@PostMapping("/loginProcess.do")   //입력수정삭제
	public String login(HttpSession session, @ModelAttribute MemberVO
			loginVO)throws Exception {
		//입력된 유저가 있는지 확인
		MemberVO memberVO=memberService.authenticate(loginVO);
		//세션 만들어서 일시로 유저저장 세션.setAttribute("키",값); 재시작이나 브라우저 종료시 사라짐
		session.setAttribute("memberVO", memberVO);
		//보안코딩: 사이트 위조 공격 방어
		session.setAttribute("CSRF_TOKEN",UUID.randomUUID().toString());
		//이동할 페이지로 통과
		return "redirect:/";
	}
//로그아웃
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
//세션삭제
		session.invalidate();
		return "redirect:/login.do";
	}
//회원가입페이지 열기
	@GetMapping("/register.do")
	public String registerView() {
		return "auth/register";
	}
//회원가입 처리
	@PostMapping("/register/addition.do")
	public String register(Model model, @ModelAttribute MemberVO
			memberVO) throws Exception{
	//서비스의 회원가입 메소드 실행
		memberService.register(memberVO);
	//성공메시지
		model.addAttribute("msg","회원가입을 성공했습니다.");
		
		return "auth/register";
	}
}
