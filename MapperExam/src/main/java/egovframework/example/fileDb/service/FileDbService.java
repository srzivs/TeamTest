/**
 * 
 */
package egovframework.example.fileDb.service;

import java.util.List;

import egovframework.example.common.Criteria;

/**
 * @author user
 *
 */
public interface FileDbService {
	List<?> selectFileDbList(Criteria criteria); //전체조회
	int selectFileDbListCnt (Criteria criteria); //총 개수 구하기
	int insert(FileDbVO fileDbVO);               //insert
	FileDbVO selectFileDb(String uuid);          //상세조회
	int delete(String uuid);                     //삭제 
}
