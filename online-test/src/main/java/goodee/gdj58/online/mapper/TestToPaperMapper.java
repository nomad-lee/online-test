package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.QuestionAndExample;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestToPaperMapper {
	// Test
	int deleteTest(int testNo);
	int insertTest(Test test);
	List<Test> selectTestList(Map<String, Object> paramMap);
	int cntTestList(String searchWord);
	
	// Question
	int deleteQuestion(int questionNo);
	int insertQuestion(Question question);
	List<Question> selectQuestionList(Map<String, Object> paramMap);
	int cntQuestionList(@Param("testNo") int testNo, @Param("searchWord") String searchWord);
	
	// Example
	int deleteExample(int exampleNo);
	int insertExample(Example example);
	List<Example> selectExampleList(Map<String, Object> paramMap);
	int cntExampleList(@Param("questionNo") int questionNo, @Param("searchWord") String searchWord);

	// Paper
	List<QuestionAndExample> selectTestForStudent(Map<String, Object> paramMap);
	List<QuestionAndExample> selectPaper(Map<String, Object> paramMap);
	
}
