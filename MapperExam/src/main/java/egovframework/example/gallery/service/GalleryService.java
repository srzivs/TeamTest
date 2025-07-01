/**
 * 
 */
package egovframework.example.gallery.service;

import java.util.List;

import egovframework.example.common.Criteria;

/**
 * @author user
 *
 */
public interface GalleryService {
	List<?> selectGalleryList (Criteria criteria); //전체조회
	int selectGalleryListCnt (Criteria criteria); //총 개수 구하기
	int insert(GalleryVO galleryVO);               //insert
	GalleryVO selectGallery(String uuid);          //상세조회
	int delete(String uuid);                     //삭제 
}
