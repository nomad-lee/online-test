package goodee.gdj58.online.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.controller.employee.TeacherController;
import goodee.gdj58.online.mapper.TestToPaperMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TestToPaperService {
	@Autowired private TestToPaperMapper testToPaperMapper;
	
	// Test Begin
	public int removeTest(int testNo) {
		return testToPaperMapper.deleteTest(testNo);
	}
	
	public int addTest(Test test) {
		return testToPaperMapper.insertTest(test);
	}
	
	public List<Test> getTestList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testToPaperMapper.selectTestList(paramMap);
	}
	
	public int cntTestList(String searchWord) {
		return testToPaperMapper.cntTestList(searchWord);
	}
	// Test End
	
	// Question Begin
	public int removeQuestion(int questionNo) {
		return testToPaperMapper.deleteQuestion(questionNo);
	}
	
	public int addQuestion(Question question) {
		return testToPaperMapper.insertQuestion(question);
	}
	
	public List<Question> getQuestionList(int testNo, int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("testNo", testNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		log.debug("\u001B[31m"+testNo+" <-- testNo");
		log.debug("\u001B[31m"+beginRow+" <-- beginRow");
		log.debug("\u001B[31m"+rowPerPage+" <-- rowPerPage");
		log.debug("\u001B[31m"+searchWord+" <-- searchWord");
		return testToPaperMapper.selectQuestionList(paramMap);
	}
	
	public int cntQuestionList(int testNo, String searchWord) {
		return testToPaperMapper.cntQuestionList(testNo, searchWord);
	}
	// Question End
	
	
	// Example Begin
	public int removeExample(int exampleNo) {
		return testToPaperMapper.deleteExample(exampleNo);
	}
	
	public int addExample(Example example) {
		return testToPaperMapper.insertExample(example);
	}
	
	public List<Example> getExampleList(int questionNo, int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionNo", questionNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testToPaperMapper.selectExampleList(paramMap);
	}
	
	public int cntExampleList(int questionNo, String searchWord) {
		return testToPaperMapper.cntExampleList(questionNo, searchWord);
	}
	// Example End
	
	
	// Paper Begin	
	public List<Question> getQuestionList(int testNo) {
		int beginRow = 0;
		int rowPerPage = 9999;
		String searchWord = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		log.debug("\u001B[31m"+paramMap+" <-- paramMap");
		paramMap.put("testNo", testNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testToPaperMapper.selectQuestionList(paramMap);
	}
	public List<Example> getExampleList(int questionNo) {
		int beginRow = 0;
		int rowPerPage = 9999;
		String searchWord = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionNo", questionNo);
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return testToPaperMapper.selectExampleList(paramMap);
	}
	// Paper End
}
