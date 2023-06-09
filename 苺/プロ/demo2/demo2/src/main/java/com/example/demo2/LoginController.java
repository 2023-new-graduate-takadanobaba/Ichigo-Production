// package com.example.demo2;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// // import javax.servlet.http.HttpSession;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// // import jp.co.sss.book.entity.BookUser;
// // import jp.co.sss.book.form.LoginForm;
// // import jp.co.sss.book.repository.BookUserRepository;

// @Controller

// public class LoginController {
//     @Autowired
//     private hyoziUserRepository userRepository;


//     @RequestMapping(path = "/" , method =RequestMethod.GET)
//     public String display() {
//         return "login";
//     }
//     // loginform form ログイン画面で入力した値をビューからコントローラーに渡す
// 	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
// 	public String doLogin(LoginForm form, HttpSession session, Model model) {
		
// 		Integer userId = Integer.parseInt(form.getUserId());
// 		String password = form.getPassword();
// 		displayUser user = userRepository.findByDisplayUserIdAndPassword(userId, password);

// 		if (user != null) {
// 			session.setAttribute("userName",user.getBookUserName());
// 			return "redirect:/list";
			
// 		} else {
// 			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
// 			return "login";
// 		}
// 	}}

// 	@RequestMapping(path = "/logout", method = RequestMethod.GET)
// 	public String logout(HttpSession session) {
// 		//セッションの破棄
// 		session.invalidate();
// 		return "redirect:/";
// 	}

			
// }


    
