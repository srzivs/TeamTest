/**
 * 
 */
package egovframework.example.faq.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.common.Criteria;
import egovframework.example.faq.service.FaqService;

/**
 * @author user
 *
 */
@Service
public class FaqServiceImpl implements FaqService{
//mapper가져오기
	@Autowired
	private FaqMapper faqMapper;
//전체조회
	@Override
	public List<?> selectFaqList(Criteria criteria) {
		// TODO Auto-generated method stub
		return faqMapper.selectFaqList(criteria);
		}
}
