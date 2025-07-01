package egovframework.example.gallery.service;

import org.springframework.web.multipart.MultipartFile;

import egovframework.example.common.Criteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author user
 * 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GalleryVO extends Criteria{
	private String uuid;            //기본키
	private String galleryTitle;    //제목
	private byte[] galleryData;     //첨부파일
	private MultipartFile image;    //내부목적사용
	private String galleryFileUrl;  //이미지 다운 url
//필드 3개	
	public GalleryVO(String uuid, String galleryTitle, byte[] galleryData) {
		super();
		this.uuid = uuid;
		this.galleryTitle = galleryTitle;
		this.galleryData = galleryData;
	}
//필드 2개	
	public GalleryVO(String galleryTitle, byte[] galleryData) {
		super();
		this.galleryTitle = galleryTitle;
		this.galleryData = galleryData;
	}
	

}
