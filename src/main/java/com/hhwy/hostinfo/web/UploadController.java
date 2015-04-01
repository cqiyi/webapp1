package com.hhwy.hostinfo.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(method = RequestMethod.GET)
	public String viewUploadJspPage() {
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String handleUploadProcess(HttpServletRequest request,
			@RequestParam("f1") MultipartFile file1) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/upload");

		try {
			FileUtils.copyInputStreamToFile(file1.getInputStream(), new File(
					realPath, file1.getOriginalFilename()));
			System.out.println(new Date() + " uploaded successfull:"
					+ file1.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error:" + e.getMessage();
		}
		return "success";
	}
}
