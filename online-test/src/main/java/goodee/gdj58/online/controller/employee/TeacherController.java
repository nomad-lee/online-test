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
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.service.TestToPaperService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class TeacherController {
	@Autowired TeacherService teacherService;
	@Autowired TestToPaperService testToPaperService;
	@Autowired IdService idService;
	
	// pw수정 폼
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session) {
		return "teacher/modifyTeacherPw";
	}	
	// pw수정 액션
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
			, @RequestParam(value="oldPw") String oldPw
			, @RequestParam(value="newPw") String newPw) {
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");		
		teacherService.updateTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		
		return "redirect:/teacher/testList";
	}
	
	/*
	 *  emp로그인 후에 사용가능한 기능
	 */
	
	// Test Begin
	// Test 삭제
	@GetMapping("/teacher/removeTest")
	public String removeTest(HttpSession session, @RequestParam("testNo") int testNo) {		
		testToPaperService.removeTest(testNo);
		return "redirect:/teacher/testList"; // 리스트로 리다이렉트
	}
	
	// Test 입력
	@GetMapping("/teacher/addTest")
	public String addTest(HttpSession session) {		
		return "teacher/addTest"; // forward
	}
	
	@PostMapping("/teacher/addTest")
	public String addTest(HttpSession session, Model model, Test test) {		
		
		int row = testToPaperService.addTest(test);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "teacher/addTest";
		}
		return "redirect:/teacher/testList"; // sendRedirect , CM -> C
	}
	
	// Test 리스트
	@GetMapping("/teacher/testList")
	public String testList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
							// int currentPage = reuqest.getParamenter("currentPage");
		
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		List<Test> list = testToPaperService.getTestList(currentPage, rowPerPage, searchWord);
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		int cntTestList = testToPaperService.cntTestList(searchWord);
		int endPage = cntTestList / rowPerPage;
		if(cntTestList % rowPerPage != 0) {
			endPage++;
		}
		model.addAttribute("startPage", 1);
		model.addAttribute("endPage", endPage);
		return "teacher/testList";
	}
	// Test End
}