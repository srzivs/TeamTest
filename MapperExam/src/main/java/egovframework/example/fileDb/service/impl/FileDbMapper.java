/**
 * 
 */
package egovframework.example.fileDb.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.common.Criteria;
import egovframework.example.fileDb.service.FileDbVO;

/**
 * @author user
 *
 */
@Mapper
public interface FileDbMapper {
	public List<?> selectFileDbList(Criteria criteria); //전체조회
	public int selectFileDbListCnt (Criteria criteria); //총 개수 구하기
	public int insert(FileDbVO fileDbVO);               //insert
	public FileDbVO selectFileDb(String uuid);          //상세조회
	public int delete(String uuid);                     //삭제 
}
