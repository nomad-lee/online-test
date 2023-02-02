package goodee.gdj58.online.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.IdService;

@Controller 
public class StudentController {
	@Autowired EmployeeService employeeService;
	@Autowired IdService idService;
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */	

}