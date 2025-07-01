package egovframework.example.faq.service;

import java.util.List;

import egovframework.example.common.Criteria;

public interface FaqService {
	List<?> selectFaqList(Criteria criteria); //전체조회
}
