package kr.co.infopub.hrm.controller;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.infopub.hrm.dto.DepCountDto;
import kr.co.infopub.hrm.dto.DepartmentDto;
import kr.co.infopub.hrm.dto.EmployeeDto;
import kr.co.infopub.hrm.help.NumberResult;
import kr.co.infopub.hrm.service.EmployeeService;
@RestController
@RequestMapping("/api")
@Api(value="hrm", description="Human Resouces Management - Oracle DBM DB Schema")
public class EmployeeController {
 public static final Logger logger = 
		 LoggerFactory.getLogger(EmployeeController.class);
 private String to__(String v){
	return v.replaceAll(" ", "%20");
 }
 private String __to(String v){
	return v.replaceAll("%20", " ");
 }
 @Autowired
 private EmployeeService  employeeService; 

 @ApiOperation(value = "모든 사원의 정보를 반환한다.", response = List.class)
 @RequestMapping(value = "/findAllEmployees", method = RequestMethod.GET)
 public ResponseEntity<List<EmployeeDto>> findAllEmployees() throws Exception {
	logger.info("1-------------findAllEmployees----------------"+new Date());
	List<EmployeeDto> emps = employeeService.findAllEmployees();
	if (emps.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<EmployeeDto>>(emps, HttpStatus.OK);
 }
 @ApiOperation(value = "모든 부서의 정보를 반환한다. "
 		 + "적어도 1명 이상의 사원이 있는 부서만 반환한다.", response = List.class)
 @RequestMapping(value = "/findAllDepartments", method = RequestMethod.GET)
 public ResponseEntity<List<DepartmentDto>> findAllDepartments() throws Exception {
	logger.info("2-------------findAllDepartments--------"+new Date());
	List<DepartmentDto> emps = employeeService.findAllDepartments();
	if (emps.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<DepartmentDto>>(emps, HttpStatus.OK);
 }
 @ApiOperation(value = " 사원과 관리자 관계를 트리로 반환한다.", response = List.class)
 @RequestMapping(value = "/findTreeManagerInEmployee", method = RequestMethod.GET)
 public ResponseEntity<List<EmployeeDto>> findTreeManagerInEmployee() 
		                                                  throws Exception {
	logger.info("3-------findTreeManagerInEmployee--------"+new Date());
	List<EmployeeDto> emps = employeeService.findTreeManagerInEmployee();
	if (emps.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<EmployeeDto>>(emps, HttpStatus.OK);
 }
 @ApiOperation(value = " 관리자와 사원의 관계를 트리로 만들때 최대 leaf값을 반환한다.",
		                               response = NumberResult.class)
 @RequestMapping(value = "/getTreeMaxLevel", method = RequestMethod.GET)
 public ResponseEntity<NumberResult> getTreeMaxLevel() throws Exception {
	logger.info("4---------getTreeMaxLevel--------"+new Date());
	int total = employeeService.getTreeMaxLevel();
	NumberResult nr=new NumberResult();
	nr.setCount(total);
	nr.setName("getTreeMaxLevel");
	nr.setState("succ");
	if (total<=0) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
 }	
 @ApiOperation(value = " 각 부서에 소속된 사원 수, 부서번호, 부서이름를 반환한다.", 
		                                              response = List.class)
 @RequestMapping(value = "/findAllDepCounts", method = RequestMethod.GET)
 public ResponseEntity<List<DepCountDto>> findAllDepCounts() throws Exception {
	logger.info("5--------findAllDepCounts---------------"+new Date());
	List<DepCountDto> emps = employeeService.findAllDepCounts();
	if (emps.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<DepCountDto>>(emps, HttpStatus.OK);
 }
}
