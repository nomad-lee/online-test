package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;

@Controller 
public class StudentController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */
	
	// 삭제
	@GetMapping("/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeStudent(studentNo);
		return "redirect:/student/studentList"; // 리스트로 리다이렉트
	}
	
	// 입력
	@GetMapping("/student/addStudent")
	public String addStudent(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		return "student/addStudent"; // forword
	}
	
	@PostMapping("/student/addStudent")
	public String addStudent(HttpSession session, Model model, Student student) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "student/addStudent";
		}
		
		int row = employeeService.addStudent(student);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "student/addStudent";
		}
		
		return "redirect:/student/studentList"; // sendRedirect , CM -> C
	}
	
	// 리스트
	@GetMapping("/student/studentList")
	public String studentList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { 
							// int currentPage = reuqest.getParamenter("currentPage");
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Student> list = employeeService.getStudentList(currentPage, rowPerPage);
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "student/studentList";
	}
}