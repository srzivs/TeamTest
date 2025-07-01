/**
 * 
 */
package egovframework.example.faq.web;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import egovframework.example.common.Criteria;
import egovframework.example.faq.service.FaqService;
import lombok.extern.log4j.Log4j2;

/**
 * @author user
 *
 */
@Log4j2
@Controller
public class FaqController {
	//서비스 가져오기
		@Autowired
		private FaqService faqService;
	//전체 조회	
		@GetMapping("/faq/faq.do")
		public String faqs(@ModelAttribute Criteria criteria,
		         Model model) {
		//등차자동계산 클래스: PaginationInfo
		//현 페이지번호와 보일개수 필요 = Criteria
			PaginationInfo paginationInfo=new PaginationInfo();
			paginationInfo.setCurrentPageNo(criteria.getPageIndex());
			paginationInfo.setRecordCountPerPage(criteria.getPageUnit());
		//등차자동계산: getFirstRecordIndex
			criteria.setFirstIndex(paginationInfo.getFirstRecordIndex());

		//전체조회 서비스 메소드 실행		
			List<?> faqs=faqService.selectFaqList(criteria);
			log.info("테스트: "+faqs);
			model.addAttribute("emps",faqs);
			
			return "faq/faq_all";
}
		}
