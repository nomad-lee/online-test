package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;

@Controller
public class LoginController {
	@Autowired EmployeeService employeeService;
	@Autowired StudentService studentService;
	@Autowired TeacherService teacherService;
	
	// 로그인
	@GetMapping("/login")
	public String login(HttpSession session) {
		// 이미 로그인 중이라면 redirect:/employee/empList
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		} else if(loginStudent != null) {
			return "redirect:/student/paperList";
		} else if(loginTeacher != null) {
			return "redirect:/teacher/testList";		
		}
		return "login";
	}
	
	// 1. emp 로그인
	@PostMapping("/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		Employee resultEmp = employeeService.loginEmp(emp);
		
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}	
	
	// 2. student 로그인
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session, Student student) {
		Student resultStudent = studentService.loginStudent(student);
		
		session.setAttribute("loginStudent", resultStudent);
		return "redirect:/student/paperList";
	}	
	
	// 3. teacher 로그인
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session, Teacher teacher) {
		Teacher resultTeacher = teacherService.loginTeacher(teacher);
		
		session.setAttribute("loginTeacher", resultTeacher);
		return "redirect:/teacher/testList";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
