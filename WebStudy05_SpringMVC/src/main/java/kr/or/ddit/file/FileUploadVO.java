package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUploadVO {
	private String uploader;
	private MultipartFile uploadFile;
	
	private String saveName;
	private String fileUrl;
	
	public void setUploadFile(MultipartFile uploadFile) {
		if(uploadFile!=null && !uploadFile.isEmpty()) {
			this.uploadFile = uploadFile;
			saveName = UUID.randomUUID().toString();
			// 업로드된 파일이 있는 경우에만 savaName 존재
		}
	}
	
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(uploadFile!=null)
			uploadFile.transferTo(new File(saveFolder, saveName));
		
	}
	
	public void makeFileUrl(String folderUrl) {
		if(uploadFile!=null)
			this.fileUrl = folderUrl + "/" + saveName;
	}
}
