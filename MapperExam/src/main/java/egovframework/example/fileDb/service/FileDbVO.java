/**
 * 
 */
package egovframework.example.fileDb.service;

import org.springframework.web.multipart.MultipartFile;

import egovframework.example.common.Criteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 * vo는 카멜표기법 따르기.
 * 이미지는 BLOB 자료형
 * 
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FileDbVO extends Criteria {
	private String uuid;         //기본키(자바에서 생성)
	private String fileTitle;    //제목
	private String fileContent;  //내용
	private byte[] fileData;     //첨부파일
	private MultipartFile image; //내부 목적 사용
	private String fileUrl;      //이미지 다운로드를 위한 url
	//필드 4개
	public FileDbVO(String uuid, String fileTitle, String fileContent, byte[] fileData) {
		super();
		this.uuid = uuid;
		this.fileTitle = fileTitle;
		this.fileContent = fileContent;
		this.fileData = fileData;
	}
	//필드 3개
	public FileDbVO(String fileTitle, String fileContent, byte[] fileData) {
		super();
		this.fileTitle = fileTitle;
		this.fileContent = fileContent;
		this.fileData = fileData;
	}
	
}
