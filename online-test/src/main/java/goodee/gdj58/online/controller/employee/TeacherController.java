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
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
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
		
		List<Test> list = testToPaperService.getTestList(currentPage, rowPerPage, searchWord); // 한 페이지 10개의 컬럼
		int cntTestList = testToPaperService.cntTestList(searchWord); // 총 페이지
		int beginPaging = ((currentPage-1)/10)*10+1; // 10단위 페이징
		int endPaging = beginPaging + 10 - 1; // 10단위 페이징
		int lastPage = (int)Math.ceil(cntTestList/(double)10); // 마지막 페이지
		if(endPaging > lastPage){	// 마지막 페이지 처리
			endPaging = lastPage;
		}	
		log.debug("\u001B[31m"+cntTestList+" <-- cntTestList");
		log.debug("\u001B[31m"+beginPaging+" <-- beginPaging");
		log.debug("\u001B[31m"+endPaging+" <-- endPaging");
		log.debug("\u001B[31m"+lastPage+" <-- lastPage");
		
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("beginPaging", beginPaging);
		model.addAttribute("endPaging", endPaging);
		model.addAttribute("lastPage", lastPage);
		return "teacher/testList";
	}
	// Test End
	
	// Question Begin
	// Question 삭제
	@GetMapping("/teacher/removeQuestion")
	public String removeQuestion(HttpSession session, @RequestParam("questionNo") int questionNo) {		
		testToPaperService.removeQuestion(questionNo);
		return "redirect:/teacher/questionList"; // 리스트로 리다이렉트
	}
	
	// Question 입력
	@GetMapping("/teacher/addQuestion")
	public String addQuestion(HttpSession session, Model model
							, @RequestParam(value="testNo", defaultValue = "0") int testNo) {
		
		model.addAttribute("testNo", testNo);
		return "teacher/addQuestion"; // forward
	}
	
	@PostMapping("/teacher/addQuestion")
	public String addQuestion(HttpSession session, Model model, Question question) {		
		
		int row = testToPaperService.addQuestion(question);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "teacher/addQuestion";
		}
		return "redirect:/teacher/questionList"; // sendRedirect , CM -> C
	}
	
	// Question 리스트
	@GetMapping("/teacher/questionList")
	public String questionList(HttpSession session, Model model
							, @RequestParam(value="testNo", defaultValue = "0") int testNo
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
							// int currentPage = reuqest.getParamenter("currentPage");

		log.debug("\u001B[31m"+testNo+" <-- testNo");
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		
		List<Question> list = testToPaperService.getQuestionList(testNo, currentPage, rowPerPage, searchWord); // 한 페이지 10개의 컬럼
		int cntQuestionList = testToPaperService.cntQuestionList(testNo, searchWord); // 총 페이지
		int beginPaging = ((currentPage-1)/10)*10+1; // 10단위 페이징
		int endPaging = beginPaging + 10 - 1; // 10단위 페이징
		int lastPage = (int)Math.ceil(cntQuestionList/(double)10); // 마지막 페이지
		if(endPaging > lastPage){	// 마지막 페이지 처리
			endPaging = lastPage;
		}	
		log.debug("\u001B[31m"+cntQuestionList+" <-- cntQuestionList");
		log.debug("\u001B[31m"+beginPaging+" <-- beginPaging");
		log.debug("\u001B[31m"+endPaging+" <-- endPaging");
		log.debug("\u001B[31m"+lastPage+" <-- lastPage");
		log.debug("\u001B[31m"+list+" <-- list");
		
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("beginPaging", beginPaging);
		model.addAttribute("endPaging", endPaging);
		model.addAttribute("lastPage", lastPage);
		return "teacher/questionList";
	}
	// Question End
	
	// Example Begin
	// Example 삭제
	@GetMapping("/teacher/removeExample")
	public String exampleTest(HttpSession session, @RequestParam("exampleNo") int exampleNo) {		
		testToPaperService.removeExample(exampleNo);
		return "redirect:/teacher/exampleList"; // 리스트로 리다이렉트
	}
	
	// Example 입력
	@GetMapping("/teacher/addExample")
	public String addExample(HttpSession session, Model model
			, @RequestParam(value="questionNo", defaultValue = "0") int questionNo) {
		
		model.addAttribute("questionNo", questionNo);
		return "teacher/addExample"; // forward
	}
	
	@PostMapping("/teacher/addExample")
	public String addExample(HttpSession session, Model model, Example example) {		
		
		int row = testToPaperService.addExample(example);
		if(row == 0) {
			model.addAttribute("errorMsg", "시스템에러로 등록실패");
			return "teacher/addExample";
		}
		return "redirect:/teacher/exampleList"; // sendRedirect , CM -> C
	}
	
	// Example 리스트
	@GetMapping("/teacher/exampleList")
	public String exampleList(HttpSession session, Model model
							, @RequestParam(value="questionNo", defaultValue = "0") int questionNo
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) { 
							// int currentPage = reuqest.getParamenter("currentPage");

		log.debug("\u001B[31m"+questionNo+" <-- questionNo");
		log.debug("\u001B[31m"+currentPage+" <-- currentPage");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		
		List<Example> list = testToPaperService.getExampleList(questionNo, currentPage, rowPerPage, searchWord); // 한 페이지 10개의 컬럼
		int cntExampleList = testToPaperService.cntExampleList(questionNo, searchWord); // 총 페이지
		int beginPaging = ((currentPage-1)/10)*10+1; // 10단위 페이징
		int endPaging = beginPaging + 10 - 1; // 10단위 페이징
		int lastPage = (int)Math.ceil(cntExampleList/(double)10); // 마지막 페이지
		if(endPaging > lastPage){	// 마지막 페이지 처리
			endPaging = lastPage;
		}	
		log.debug("\u001B[31m"+cntExampleList+" <-- cntExampleList");
		log.debug("\u001B[31m"+beginPaging+" <-- beginPaging");
		log.debug("\u001B[31m"+endPaging+" <-- endPaging");
		log.debug("\u001B[31m"+lastPage+" <-- lastPage");
		
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("beginPaging", beginPaging);
		model.addAttribute("endPaging", endPaging);
		model.addAttribute("lastPage", lastPage);
		return "teacher/exampleList";
	}
	// Example End
}