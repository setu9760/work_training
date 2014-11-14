package spring.desai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//
//@Controller
public class Home_controller {

	@RequestMapping(value = "/hello-page")
	public ModelAndView getHelloPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("hello");
		return view;
	}
}
