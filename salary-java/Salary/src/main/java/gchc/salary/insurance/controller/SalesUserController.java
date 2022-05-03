package gchc.salary.insurance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import gchc.salary.insurance.config.PasswordEncoding;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import gchc.salary.insurance.domain.SalesUser;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.parameter.SalesUserParameter;
import gchc.salary.insurance.repository.SalesUserRepository;
import gchc.salary.insurance.jwt.service.JwtService;
import gchc.salary.insurance.service.SalesUserService;

/*
 * 게시판 컨트롤러
 * @author 노자바
-	REST AP의 URI정보와 입력 parameter에 대한 Validation을 처리한다.
-	실제 동작하는 로직
-	사용자에게 입력을 받고 돌려주는 역할
 * */

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "영업담당자 암호화 AUTH API")
public class SalesUserController {
	@Autowired
	private SalesUserService salesUserService;
	
	
	@Autowired
	JwtService jwtService;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	PasswordEncoding passwordEncoding = new PasswordEncoding(passwordEncoder);	

	
	/*
	 * 로그인 처리.
	 * @param member
	 */	
	@ResponseBody
	@PostMapping("/login")
	@ApiOperation(value = "로그인 처리", notes = "로그인.")
	public ResponseEntity<Map <String, Object>> login(@RequestBody SalesUser salesUser, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			//passwordEncoding.encode(model.getPassword())
			SalesUser loginUser = salesUserService.login(salesUser.getID(), salesUser.getPassword());
			System.out.println(loginUser);
			
			if(loginUser==null) {
//				System.out.println("해당 유저가 존재하지 않습니다.");
				resultMap.put("status", false);
				resultMap.put("LoginInfo", "NotID");
				status = HttpStatus.NO_CONTENT;

			} else {
				   if(!passwordEncoder.matches(salesUser.getPassword(), loginUser.getPassword())) {
//					   System.out.println("비밀번호가 일치하지 않습니다.");
						resultMap.put("status", false);
						resultMap.put("LoginInfo", "NotPwd");
						status = HttpStatus.ACCEPTED;		
						      
				   } else {
//					   System.out.println("비밀번호가 일치");
						String tocken = jwtService.create(loginUser);
						res.setHeader("jwt-auth-token", tocken);
						
						resultMap.put("status", true);
						resultMap.put("data", loginUser);
						resultMap.put("LoginInfo", "success");
						status = HttpStatus.ACCEPTED;			   
				   }
				
			}
			
		} catch (RuntimeException e) {
			//log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	@PostMapping("/info")
	public ResponseEntity<Map <String, Object>> getInfo(HttpServletRequest req, @RequestBody SalesUser salesUser) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String info = salesUserService.getServerInfo();
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("info", info);
			resultMap.put("request_body", salesUser);
			status = HttpStatus.ACCEPTED;
			
		} catch (RuntimeException e) {
			//log.error("로그인 정보 조회 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
//	 @ApiOperation(value = "가입", notes = "회원가입을 한다")
//	 @PostMapping(value = "/signup")
//	 public void signup(@RequestBody SalesUser model) {
//
//		  SalesUser user = new SalesUser(0, model.getID(), passwordEncoding.encode(model.getPassword()), model.getName(), model.getEmail(), 0, null, null);
//
//	    salesUserService.addUser(user);
//
//	  }	
	 
		/*
		 * 가입 처리.
		 * @param member
		 */	
		@ResponseBody
		@PostMapping("/signup")
		@ApiOperation(value = "가입", notes = "회원가입을 한다")
		public ResponseEntity<Map <String, Object>> signup2(@RequestBody SalesUser model, HttpServletResponse res) {
			Map<String, Object> resultMap = new HashMap<>();
			HttpStatus status = null;
			try {
				SalesUser user = new SalesUser(0, model.getID(), passwordEncoding.encode(model.getPassword()), model.getName(), model.getEmail(), 0, null, null);				
				salesUserService.addUser(user);
				resultMap.put("status", true);
				status = HttpStatus.ACCEPTED;					

				
			} catch (RuntimeException e) {
 				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<Map<String,Object>>(resultMap, status);
		}	 
	

}
