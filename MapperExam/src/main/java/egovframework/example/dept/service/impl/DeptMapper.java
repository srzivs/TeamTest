/**
 * 
 */
package egovframework.example.dept.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.common.Criteria;
import egovframework.example.dept.service.DeptVO;

/**
 * @author user
 * mybatis: 인터페이스, xml: sql문 작성
 * sql 작성할 인터페이스
 */
@Mapper
public interface DeptMapper {
	public List<?> selectDeptList(Criteria criteria); //전체조회
	public int selectDeptListTotCnt(Criteria criteria); //총 개수 구하기
	public int insert(DeptVO deptVO);                   //부서 insert
	public DeptVO selectDept(int dno);                 //상세조회
	public int update(DeptVO deptVO);                  //update 
	public int delete(DeptVO deptVO);                 //delete
}
