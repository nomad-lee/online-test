package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TestToPaperMapper;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestToPaperService {
	@Autowired private TestToPaperMapper testToPaperMapper;
	
	// Test
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
	
	// Question
	// Example
	// Paper
}
