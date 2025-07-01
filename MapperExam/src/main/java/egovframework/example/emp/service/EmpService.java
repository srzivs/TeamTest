/**
 * 
 */
package egovframework.example.emp.service;

import java.util.List;

import egovframework.example.common.Criteria;

/**
 * @author user
 *
 */
public interface EmpService {
	List<?> selectEmpList(Criteria criteria); //전체조회
	int selectEmpListTotCnt(Criteria criteria); //총 개수 구하기
	int insert(EmpVO empVO); //추가
	EmpVO selectEmp(int eno); //조회
	int update(EmpVO empVO); //수정
	int delete(EmpVO empVO);   //delete
}
