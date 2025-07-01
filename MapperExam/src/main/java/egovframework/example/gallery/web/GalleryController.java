/**
 * 
 */
package egovframework.example.gallery.web;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.common.Criteria;
import egovframework.example.fileDb.service.FileDbVO;
import egovframework.example.gallery.service.GalleryService;
import egovframework.example.gallery.service.GalleryVO;
import lombok.extern.log4j.Log4j2;

/**
 * @author user
 *
 */
@Log4j2
@Controller
public class GalleryController {
//서비스 가져오기	
	@Autowired
	private GalleryService galleryService;
//전체조회
	@GetMapping("/gallery/gallery.do")
	public String selectGalleryList(@ModelAttribute Criteria criteria,
	         Model model) {
//등차자동계산 클래스: PaginationInfo
	//현 페이지번호와 보일개수 필요 = Criteria
	PaginationInfo paginationInfo=new PaginationInfo();
	paginationInfo.setCurrentPageNo(criteria.getPageIndex());
	paginationInfo.setRecordCountPerPage(criteria.getPageUnit());
//등차자동계산: getFirstRecordIndex
	criteria.setFirstIndex(paginationInfo.getFirstRecordIndex());
		
//전체조회 서비스 메소드 실행
	List<?> gallerys=galleryService.selectGalleryList(criteria);
	log.info("테스트: "+gallerys);
	model.addAttribute("gallerys",gallerys);	

//페이지 번호 그리기: 페이지 플러그인. 전체 테이블 행 개수
	int totCnt=galleryService.selectGalleryListCnt(criteria);
	paginationInfo.setTotalRecordCount(totCnt);
	log.info("테스트2: "+totCnt);
	
//페이지 모든 정보 paginationInfo
	model.addAttribute("paginationInfo",paginationInfo);
	return "gallery/gallery_all";
	}
	
//추가페이지 열기
	@GetMapping("/gallery/addition.do")
	public String createGalleryView() {
		return "gallery/add_gallery";
	}
//추가: 업로드
//@RequestParam(required = false)_첨부파일없어도 에러 발생 안하게 하는 옵션
	@PostMapping("/gallery/add.do")
	public String insert(@RequestParam(defaultValue = "") String fileTitle,
			@RequestParam(defaultValue = "") String fileContent,
			@RequestParam(required = false) MultipartFile image) throws Exception {
		GalleryVO galleryVO =new GalleryVO(fileTitle,fileContent,image.getBytes());
		galleryService.insert(galleryVO);
		return "redirect:/gallery/gallery.do";
}
//다운로드 메소드 url을 실행하면 이 메소드가 첨부파일을 전달해준다.	
	
//삭제 (삭제 후 전체조회 페이지 열기)
}
