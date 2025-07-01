/**
 * 
 */
package egovframework.example.gallery.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.common.Criteria;
import egovframework.example.gallery.service.GalleryService;
import egovframework.example.gallery.service.GalleryVO;

/**
 * @author user
 *
 */
@Service
public class GalleryServiceimpl implements GalleryService{
	@Autowired
	GalleryMapper gallerymapper;
//전체조회	
	@Override
	public List<?> selectGalleryList(Criteria criteria) {
		// TODO Auto-generated method stub
		return gallerymapper.selectGalleryList(criteria);
	}
//총개수
	@Override
	public int selectGalleryListCnt(Criteria criteria) {
		// TODO Auto-generated method stub
		return gallerymapper.selectGalleryListCnt(criteria);
	}
//추가	
	@Override
	public int insert(GalleryVO galleryVO) {
		/*
		 * // 1) uuid(기본키)만들기 String newUuid=UUID.randomUUID().toString(); // 2)다운로드
		 * url만들기 String downloadURL=generateDownloadUrl(newUuid); // 3)FileDbVO에 1,2
		 * 저장(setter) GalleryVO.setUuid(newUuid); GalleryVO.setFileUrl(downloadURL); //
		 * 4) DB insert(GalleryVO)
		 */		return gallerymapper.insert(galleryVO);
	}
//상세조회	
	@Override
	public GalleryVO selectGallery(String uuid) {
		// TODO Auto-generated method stub
		return gallerymapper.selectGallery(uuid);
	}
//삭제	
	@Override
	public int delete(String uuid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
}
