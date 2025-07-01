/**
 * 
 */
package egovframework.example.fileDb.web;

import java.util.List;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import egovframework.example.common.Criteria;
import egovframework.example.fileDb.service.FileDbService;
import egovframework.example.fileDb.service.FileDbVO;
import lombok.extern.log4j.Log4j2;

/**
 * @author user
 *
 */
@Log4j2
@Controller
public class FileDbController {
//서비스 가져오기	
	@Autowired
	private FileDbService fileDbService;
//전체조회
	@GetMapping("/fileDb/fileDb.do")
	public String selectFileDbList(@ModelAttribute Criteria criteria,
	         Model model) {
//등차자동계산 클래스: PaginationInfo
	//현 페이지번호와 보일개수 필요 = Criteria
	PaginationInfo paginationInfo=new PaginationInfo();
	paginationInfo.setCurrentPageNo(criteria.getPageIndex());
	paginationInfo.setRecordCountPerPage(criteria.getPageUnit());
//등차자동계산: getFirstRecordIndex
	criteria.setFirstIndex(paginationInfo.getFirstRecordIndex());
	
//전체조회 서비스 메소드 실행
	List<?> fileDbs=fileDbService.selectFileDbList(criteria);
	log.info("테스트: "+fileDbs);
	model.addAttribute("fileDbs",fileDbs);	

//페이지 번호 그리기: 페이지 플러그인. 전체 테이블 행 개수
	int totCnt=fileDbService.selectFileDbListCnt(criteria);
	paginationInfo.setTotalRecordCount(totCnt);
	log.info("테스트2: "+totCnt);
	
//페이지 모든 정보
	model.addAttribute("paginationInfo",paginationInfo);
	return "fileDb/fileDb_all";
	}
	
//추가페이지 열기
	@GetMapping("/fileDb/addition.do")
	public String createFileDbView() {
		return "fileDb/add_fileDb";
	}
	
//추가: 업로드
//@RequestParam(required = false)_첨부파일없어도 에러 발생 안하게 하는 옵션
	@PostMapping("/fileDb/add.do")
	public String insert(@RequestParam(defaultValue = "") String fileTitle,
			@RequestParam(defaultValue = "") String fileContent,
			@RequestParam(required = false) MultipartFile image) throws Exception {
		FileDbVO fileDbVO =new FileDbVO(fileTitle,fileContent,image.getBytes());
		fileDbService.insert(fileDbVO);
		return "redirect:/fileDb/fileDb.do";
	}

//다운로드 메소드 url을 실행하면 이 메소드가 첨부파일을 전달해준다.
	@GetMapping("/fileDb/download.do")
	@ResponseBody
	public ResponseEntity<byte[]> fileDownload(
			@RequestParam(defaultValue = "") String uuid) {
//	1)상세조회:첨부파일 가져오기
		FileDbVO fileDbVO =fileDbService.selectFileDb(uuid);
//  2)첨부파일 보낼 때_알려줘야함, 첨부파일 문서 형식 지정 = 헤더에 알려주기
		HttpHeaders headers=new HttpHeaders();
        //2-1)첨부파일 보낸다
		headers.setContentDispositionFormData("attachment", fileDbVO.getUuid()); 
		//2-2)첨부파일 문서 형식
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

//new ResponseEntity<byte[]>(데이터, 헤더(생략가능), 신호)		
		return new ResponseEntity<byte[]>(
								fileDbVO.getFileData(), headers, HttpStatus.OK);
	}
//삭제 (삭제 후 전체조회 페이지 열기)
	@PostMapping("/fileDb/delete.do")
	public String delete(@RequestParam(defaultValue = "") String uuid) {
		fileDbService.delete(uuid);
		return "redirect:/fileDb/fileDb.do";
	}
	
	
}




