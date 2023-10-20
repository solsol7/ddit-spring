package com.springboard.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class ImageUploadController {
   
   @Value("#{appInfo.boardImagesUrl}")
   private String boardImagesUrl;
   
   @Value("#{appInfo.boardImagesUrl}")
   private Resource boardImages;

   @PostMapping("image")
   public String imageUpload(MultipartFile upload, Model model, HttpServletRequest request ) throws IOException {
      if(!upload.isEmpty()) {
         String saveName = UUID.randomUUID().toString(); 
         File saveFolder = boardImages.getFile(); 
         File saveFile = new File(saveFolder,saveName);
         upload.transferTo(saveFile);  //업로드 완료
         
         String url = request.getContextPath()+ boardImagesUrl + "/" + saveName;  //업로드 되면서 url 만들어짐, 비동기 응답데이터로 주기
         model.addAttribute("uploaded",1);
         model.addAttribute("fileName",upload.getOriginalFilename()); //업로드된 원본 파일네임
         model.addAttribute("url",url);
      }else {
         //업로드 실패
         model.addAttribute("uploaded",0);
         model.addAttribute("error", Collections.singletonMap("message", "업로드된 파일 없음"));
         
         
      }
      return "jsonView";
      
   }
   
   
   
   
   
}