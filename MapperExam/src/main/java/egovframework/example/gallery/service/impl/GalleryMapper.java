/**
 * 
 */
package egovframework.example.gallery.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.common.Criteria;
import egovframework.example.fileDb.service.FileDbVO;
import egovframework.example.gallery.service.GalleryVO;

/**
 * @author user
 *
 */
@Mapper
public interface GalleryMapper {
	public List<?> selectGalleryList (Criteria criteria); //전체조회
	public int selectGalleryListCnt (Criteria criteria); //총 개수 구하기
	public int insert(GalleryVO galleryVO);               //insert
	public GalleryVO selectGallery(String uuid);          //상세조회
	public int delete(String uuid);                     //삭제 
}
