package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	int updateTeacherPw(Map<String, Object> paramMap);
	Teacher loginTeacher(Teacher teacher);
	int deleteTeacher(int teacherNo);
	int insertTeacher(Teacher teahcer);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
	int cntTeacherList(String searchWord);
}