package goodee.gdj58.online.controller.employee;

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
import goodee.gdj58.online.vo.Teacher;

@Controller 
public class EmployeeController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session) {
		// 로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		return "employee/modifyEmpPw";
	}	
	// pw수정 액션
	@PostMapping("/employee/modifyEmpPw")
	public String modifyEmpPw(HttpSession session
			, @RequestParam(value="oldPw") String oldPw
			, @RequestParam(value="newPw") String newPw) {
		// 로그인 후 호출 가능
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.updateEmployeePw(loginEmp.getEmpNo(), oldPw, newPw);
		
		return "redirect:/employee/empList";
	}
	
	// 로그인
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		// 이미 로그인 중이라면 redirect:/employee/empList
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		return "employee/loginEmp";
	}
	
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		Employee resultEmp = employeeService.login(emp);
		if(resultEmp == null) { // 로그인 실패
			return "redirect:/employee/loginEmp";
		}
		session.setAttribute("loginEmp", resultEmp);
		return "redirect:/employee/empList";
	}
	
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */
	
	// Employee Begin
	// 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeEmployee(empNo);
		return "redirect:/employee/empList"; // 리스트로 리다이렉트
	}
	
	// 입력
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		return "employee/addEmp"; // forword
	}
	
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Model model, Employee employee) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		String idCheck = idService.getIdCheck(employee.getEmpId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "employee/addEmp";
		}
		
		int row = employeeService.addEmployee(employee);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "employee/addEmp";
		}
		
		return "redirect:/employee/empList"; // sendRedirect , CM -> C
	}
	
	// 리스트
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { 
							// int currentPage = reuqest.getParamenter("currentPage");
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage);
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "employee/empList";
	}
	// Employee End
	
	// Student Begin
	// 삭제
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(HttpSession session, @RequestParam("studentNo") int studentNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeStudent(studentNo);
		return "redirect:/employee/student/studentList"; // 리스트로 리다이렉트
	}
	
	// 입력
	@GetMapping("/employee/student/addStudent")
	public String addStudent(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		return "employee/student/addStudent"; // forword
	}
	
	@PostMapping("/employee/student/addStudent")
	public String addStudent(HttpSession session, Model model, Student student) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "employee/student/addStudent";
		}
		
		int row = employeeService.addStudent(student);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "employee/student/addStudent";
		}
		
		return "redirect:/employee/student/studentList"; // sendRedirect , CM -> C
	}
	
	// 리스트
	@GetMapping("/employee/student/studentList")
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
		return "employee/student/studentList";
	}
	// Student End
	
	// Teacher Begin
	// 삭제
	@GetMapping("/employee/teacher/removeTeacher")
	public String removeTeacher(HttpSession session, @RequestParam("teacherNo") int teacherNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeTeacher(teacherNo);
		return "redirect:/employee/teacher/teacherList"; // 리스트로 리다이렉트
	}
	
	// 입력
	@GetMapping("/employee/teacher/addTeacher")
	public String addTeacher(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		return "employee/teacher/addTeacher"; // forward
	}
	
	@PostMapping("/employee/teacher/addTeacher")
	public String addTeacher(HttpSession session, Model model, Teacher teacher) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된ID");
			return "employee/teacher/addTeacher";
		}
		
		int row = employeeService.addTeacher(teacher);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "employee/teacher/addTeacher";
		}
		
		return "redirect:/employee/teacher/teacherList"; // sendRedirect , CM -> C
	}
	
	// 리스트
	@GetMapping("/employee/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { 
							// int currentPage = reuqest.getParamenter("currentPage");
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Teacher> list = employeeService.getTeacherList(currentPage, rowPerPage);
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "employee/teacher/teacherList";
	}
	// Teacher End
}