package br.com.cams7.sisbarc.aal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private final String ATTRIBUTE_PAGE_ACTIVE = "active";

	private final String PAGE_WELCOME = "welcome";
	private final String PAGE_ABOUT = "sobre";

	private final String PAGE_ADMIN = "admin";
	private final String PAGE_LOGIN = "login";
	private final String PAGE_403 = "403";

	@Autowired
	@Qualifier("sobreApp")
	private ArrayList<?> sobre;

	@RequestMapping(value = "/" + PAGE_WELCOME, method = RequestMethod.GET)
	public String defaultPage(Model uiModel) {
		uiModel.addAttribute("title",
				"Spring Security Login Form - Database Authentication");
		uiModel.addAttribute("message", "This is default page!");

		return PAGE_WELCOME;
	}

	@RequestMapping(value = "/" + PAGE_ABOUT, method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute(PAGE_ABOUT, sobre);
		uiModel.addAttribute(ATTRIBUTE_PAGE_ACTIVE, PAGE_ABOUT);
		return PAGE_ABOUT;
	}

	@RequestMapping(value = "/" + PAGE_ADMIN, method = RequestMethod.GET)
	public String adminPage(Model uiModel) {
		uiModel.addAttribute("title",
				"Spring Security Login Form - Database Authentication");
		uiModel.addAttribute("message", "This page is for ROLE_ADMIN only!");

		return PAGE_ADMIN;
	}

	@RequestMapping(value = "/" + PAGE_LOGIN, method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null)
			model.addObject("error", "Invalid username and password!");

		if (logout != null)
			model.addObject("msg", "You've been logged out successfully.");

		model.setViewName(PAGE_LOGIN);

		return model;
	}

	// for 403 access denied page
	@RequestMapping(value = "/" + PAGE_403, method = RequestMethod.GET)
	public String accesssDenied(Model uiModel) {
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			uiModel.addAttribute("username", userDetail.getUsername());

		}

		return PAGE_403;
	}
}
