package com.study.security_junil.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.study.security_junil.handler.aop.annotation.Log;
import com.study.security_junil.handler.aop.annotation.Timer;
import com.study.security_junil.handler.aop.annotation.ValidCheck2;
import com.study.security_junil.handler.exception.CustomValidationApiException;
import com.study.security_junil.service.auth.AuthService;
import com.study.security_junil.service.auth.PrincipalDetails;
import com.study.security_junil.service.auth.PrincipalDetailsService;
import com.study.security_junil.web.dto.CMRespDto;
import com.study.security_junil.web.dto.auth.SignupReqDto;
import com.study.security_junil.web.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
	
	private final PrincipalDetailsService principalDetailsService;
	private final AuthService authService;
	
	@Log
	@Timer
	@ValidCheck2
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) {
		boolean status = false;
		
		try {
			status = authService.checkUsername(usernameCheckReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "서버 오류", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "회원가입 가능여부", status));
	}
	
	@ValidCheck2
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
		boolean status = false;
		
		try {
			status = principalDetailsService.addUser(signupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원가입 실패", status));
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "회원가입 성공", status));
	}
	
	@GetMapping("/principal")
	public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails){
		if(principalDetails == null) {
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "principal is null", null));
		}
		return ResponseEntity.ok(new CMRespDto<>(1, "success load", principalDetails.getUser()));
	}

	@PutMapping("/principal")
	public ResponseEntity<?> updatePrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails){
		principalDetails.getUser().setUser_gender(2);

		if(principalDetails == null) {
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "principal is null", null));
		}
		return ResponseEntity.ok(new CMRespDto<>(1, "success load", principalDetails.getUser()));
	}
	
}












