/**
 * 
 */
package egovframework.example.fileDb.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import egovframework.example.common.Criteria;
import egovframework.example.fileDb.service.FileDbService;
import egovframework.example.fileDb.service.FileDbVO;

/**
 * @author user
 *
 */
@Service
public class FileDbServiceimpl implements FileDbService {
	@Autowired
	FileDbMapper fileDbMapper;

//전체조회
	@Override
	public List<?> selectFileDbList(Criteria criteria) {
		// TODO Auto-generated method stub
		return fileDbMapper.selectFileDbList(criteria);
	}
//총개수
	@Override
	public int selectFileDbListCnt(Criteria criteria) {
		// TODO Auto-generated method stub
		return fileDbMapper.selectFileDbListCnt(criteria);
	}
//추가 (테스트는 안 함)
	@Override
	public int insert(FileDbVO fileDbVO) {
//      1) uuid(기본키)만들기  
		String newUuid=UUID.randomUUID().toString();
//      2)다운로드 url만들기
		String downloadURL=generateDownloadUrl(newUuid);
//      3)FileDbVO에 1,2 저장(setter)
		fileDbVO.setUuid(newUuid); 
		fileDbVO.setFileUrl(downloadURL);
//      4) DB insert(fileDbVO)		
		return fileDbMapper.insert(fileDbVO);
	}
//다운로드 url을 만들어주는 메소드	(2))
	public String generateDownloadUrl(String uuid) {
		//url 만드는 클래스_ServletUriComponentsBuilder
	    return ServletUriComponentsBuilder      
	    .fromCurrentContextPath()       //기본주소
	    .path("/fileDb/download.do")    //경로
	    .query("uuid="+uuid)            //쿼리스트링 ?...
	    .toUriString();  //위 글 조합_http://localhost/fileDb/download.do?uuid=uuid값
	}
//상세조회
	@Override
	public FileDbVO selectFileDb(String uuid) {
		// TODO Auto-generated method stub
		return fileDbMapper.selectFileDb(uuid);
	}
//삭제	
	@Override
	public int delete(String uuid) {
		// TODO Auto-generated method stub
		return fileDbMapper.delete(uuid);
	}
	
	
	
}
