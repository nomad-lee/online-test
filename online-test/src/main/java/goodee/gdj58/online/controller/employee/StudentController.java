package goodee.gdj58.online.controller.employee;

import java.util.ArrayList;
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
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TestToPaperService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired TestToPaperService testToPaperService;
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
		
		return "redirect:/student/paperList";
	}
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */	
	
	// Paper Begin	
	@GetMapping("/student/paperList")
	public String paperList(HttpSession session, Model model
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
		return "student/paperList";
	}
	
	
	@GetMapping("/student/testOne")
	public String testOnePage(HttpSession session, Model model
							, @RequestParam(value="testNo", defaultValue = "0") int testNo) { 
							// int currentPage = reuqest.getParamenter("currentPage");

		log.debug("\u001B[31m"+testNo+" <-- testNo");

		//List<Test> test = testToPaperService.getTestList();
		List<Question> question = testToPaperService.getQuestionList(testNo);
		List<Example> example = new ArrayList<Example>();
		for(int i=0; i<question.size(); i++) {
			example = testToPaperService.getExampleList(question.get(i).getQuestionNo());
		}
		log.debug("\u001B[31m"+question+" <-- question");
		log.debug("\u001B[31m"+example+" <-- example");
		
		// request.setAttribute("list", list);
		//model.addAttribute("test", test.get(testNo).getTestTitle());
		model.addAttribute("question", question);
		model.addAttribute("example", example);;
		model.addAttribute("testNo", testNo);
		return "student/testOne";
	}
	
	@GetMapping("/student/paperOne")
	public String paperOnePage(HttpSession session, Model model
							, @RequestParam(value="paperNo", defaultValue = "0") int testNo
							, @RequestParam(value="studentNo", defaultValue = "0") int studentNo
							, @RequestParam(value="questionNo", defaultValue = "0") int questionNo
							, @RequestParam(value="answer", defaultValue = "0") int answer) {
							// int currentPage = reuqest.getParamenter("currentPage");

		log.debug("\u001B[31m"+testNo+" <-- testNo");
		log.debug("\u001B[31m"+questionNo+" <-- questionNo");

		//List<Test> test = testToPaperService.getTestList();
		List<Question> question = testToPaperService.getQuestionList(testNo);
		List<Example> example = new ArrayList<Example>();
		for(int i=0; i<question.size(); i++) {
			example = testToPaperService.getExampleList(question.get(i).getQuestionNo());
		}
		log.debug("\u001B[31m"+question+" <-- question");
		log.debug("\u001B[31m"+example+" <-- example");
		
		// request.setAttribute("list", list);
		//model.addAttribute("test", test.get(testNo).getTestTitle());
		model.addAttribute("question", question);
		model.addAttribute("example", example);;
		model.addAttribute("testNo", testNo);
		model.addAttribute("questionNo", questionNo);
		return "student/testOne";
	}
	// Paper End
}