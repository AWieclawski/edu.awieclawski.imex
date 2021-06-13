package edu.awieclawski.imex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.imex.service.I_UploadService;
import edu.awieclawski.models.ValuesLists;

@Controller
public class UploadFileController {

	private I_UploadService uploadService;

	@Autowired
	public UploadFileController(I_UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@RequestMapping(value = "/up", method = RequestMethod.GET)
	public String displayForm() {

		return "/uploadform";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String submit(@RequestParam("file") final MultipartFile file, final ModelMap modelMap,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		ValuesLists valuesLists = new ValuesLists();
		valuesLists = uploadService.fileOperate(file);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded submitted file!");
		redirectAttributes.addFlashAttribute("fileName", file.getOriginalFilename());
		redirectAttributes.addFlashAttribute("fileType", file.getContentType());
		redirectAttributes.addFlashAttribute("fileSize", file.getSize());

		session.setAttribute("sessionValuesLists", valuesLists);
		return "redirect:/result";
	}
}
