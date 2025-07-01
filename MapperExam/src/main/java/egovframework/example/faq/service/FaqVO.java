package egovframework.example.faq.service;

import egovframework.example.common.Criteria;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class FaqVO extends Criteria {
	private int fno;
	private String title;
	private String content;
}
