package goodee.gdj58.online.controller.employee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Teacher;

@Controller 
public class TeacherController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;	
	
	public String modifyTeacherPw(HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("loginTeacher");
		return "redirect:/teacher/loginTeacher";
	}
}