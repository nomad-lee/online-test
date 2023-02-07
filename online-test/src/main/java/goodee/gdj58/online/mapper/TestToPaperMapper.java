package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestToPaperMapper {
	int deleteTest(int testNo);
	int insertTest(Test test);
	List<Test> selectTestList(Map<String, Object> paramMap);
	int cntTestList(String searchWord);

}
