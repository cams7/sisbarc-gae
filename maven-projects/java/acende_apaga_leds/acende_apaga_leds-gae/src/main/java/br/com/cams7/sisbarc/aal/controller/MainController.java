package br.com.cams7.sisbarc.aal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private final String ATTRIBUTE_PAGE_ACTIVE = "active";

	private final String PAGE_HOME = "home";
	private final String PAGE_ABOUT = "sobre";

	@Autowired
	@Qualifier("sobreApp")
	private ArrayList<?> sobre;

	@RequestMapping(value = "/" + PAGE_HOME, method = RequestMethod.GET)
	public String defaultPage(Model uiModel) {
		uiModel.addAttribute("title",
				"Spring Security Login Form - Database Authentication");
		uiModel.addAttribute("message", "This is default page!");
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, PAGE_HOME);

		return PAGE_HOME;
	}

	@RequestMapping(value = "/" + PAGE_ABOUT, method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute(PAGE_ABOUT, sobre);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, PAGE_ABOUT);
		return PAGE_ABOUT;
	}

}
