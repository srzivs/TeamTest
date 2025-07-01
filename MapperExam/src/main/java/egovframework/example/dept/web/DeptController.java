/**
 * 
 */
package egovframework.example.dept.web;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.common.Criteria;
import egovframework.example.dept.service.DeptService;
import egovframework.example.dept.service.DeptVO;
import lombok.extern.log4j.Log4j2;

/**
 * @author user
 *
 */
@Log4j2
@Controller
public class DeptController {
//서비스 가져오기
	@Autowired
	private DeptService deptService;
//전체조회
	@GetMapping("/dept/dept.do")
	public String depts(@ModelAttribute Criteria criteria,
	         Model model) {
//등차자동계산 클래스: PaginationInfo
	//현 페이지번호와 보일개수 필요 = Criteria
	PaginationInfo paginationInfo=new PaginationInfo();
	paginationInfo.setCurrentPageNo(criteria.getPageIndex());
	paginationInfo.setRecordCountPerPage(criteria.getPageUnit());
//등차자동계산: getFirstRecordIndex
	criteria.setFirstIndex(paginationInfo.getFirstRecordIndex());
	
//전체조회 서비스 메소드 실행
	List<?> depts=deptService.selectDeptList(criteria);
	log.info("테스트: "+depts);
	model.addAttribute("depts",depts);	

//페이지 번호 그리기: 페이지 플러그인. 전체 테이블 행 개수
	int totCnt=deptService.selectDeptListTotCnt(criteria);
	paginationInfo.setTotalRecordCount(totCnt);
	log.info("테스트2: "+totCnt);
	
//페이지 모든 정보
	model.addAttribute("paginationInfo",paginationInfo);
	
	return "dept/dept_all";
	}
//추가페이지 열기
	@GetMapping("/dept/addition.do")
	public String createDeptView() {
		return "dept/add_dept";
	}
//저장 버튼 클릭시
	@PostMapping("/dept/add.do")
	public String insert(@ModelAttribute DeptVO deptVO) {
	//내용 확인	
		log.info("테스트2: " + deptVO);
	//insert 실행
		deptService.insert(deptVO);
		
		return "redirect:/dept/dept.do";
	}
//수정페이지 열기(상세조회)
	@GetMapping("/dept/edition.do")
	public String updateDeptView(@RequestParam int dno, Model model) {
	//서비스의 상세조회
		DeptVO deptVO=deptService.selectDept(dno);
		model.addAttribute("deptVO",deptVO);
		return "dept/update_dept";
	}
//수정: 버튼 클릭시 실행
	@PostMapping("/dept/edit.do")
	public String update(@RequestParam int dno,
			@ModelAttribute DeptVO deptVO) {
	//수정 실행
		deptService.update(deptVO);
		
		return "redirect:/dept/dept.do";
	}
//삭제
	@PostMapping("/dept/delete.do")
	public String delete(@ModelAttribute DeptVO deptVO) {
	//삭제실행
		deptService.delete(deptVO);
		
		return "redirect:/dept/dept.do";
	}
}



