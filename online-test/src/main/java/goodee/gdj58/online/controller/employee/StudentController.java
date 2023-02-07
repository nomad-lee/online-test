package goodee.gdj58.online.controller.employee;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session) {
		return "student/modifyStudentPw";
	}	
	// pw수정 액션
	@PostMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session
			, @RequestParam(value="oldPw") String oldPw
			, @RequestParam(value="newPw") String newPw) {
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");		
		studentService.updateStudentPw(loginStudent.getStudentNo(), oldPw, newPw);
		
		return "redirect:/employee/empList"; //수정하쇼
	}
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */	

}