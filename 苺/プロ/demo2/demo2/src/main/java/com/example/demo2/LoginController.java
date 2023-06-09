package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class LoginController {
    @Autowired


    @RequestMapping(path = "/login" , method =RequestMethod.GET)
    public String hyozi() {
        return "/login";
    }
	@RequestMapping(path = "/aaa", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute  LoginForm form,BindingResult result,
			HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "index";
		}else {
		
		Integer userId = Integer.parseInt(form.getBookUserId());
		String password = form.getPassword();
		BookUser user = userRepository.findByBookUserIdAndPassword(userId, password);

		if (user != null) {
			session.setAttribute("userName",user.getBookUserName());
			return "redirect:/list";
			
		} else {
			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
			return "index";
		}
	}}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

			
}


    
