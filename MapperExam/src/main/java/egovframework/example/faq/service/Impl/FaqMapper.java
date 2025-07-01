/**
 * 
 */
package egovframework.example.faq.service.Impl;

import java.util.List;

import egovframework.example.common.Criteria;

/**
 * @author user
 *
 */
public interface FaqMapper {
	public List<?> selectFaqList(Criteria criteria); //전체조회
}
