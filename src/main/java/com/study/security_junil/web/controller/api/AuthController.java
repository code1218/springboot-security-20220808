package com.study.security_junil.web.controller.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.security_junil.service.auth.PrincipalDetailsService;
import com.study.security_junil.web.dto.CMRespDto;
import com.study.security_junil.web.dto.auth.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final PrincipalDetailsService principalDetailsService;
	
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(error -> {
				System.out.println("오류발생 필드명: " + error.getField());
				System.out.println("오류발생 상세메세지: " + error.getDefaultMessage());
			});
		}
		
		return ResponseEntity.ok(new CMRespDto<>(1, "사용가능", true));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup() {
		return ResponseEntity.ok(principalDetailsService.addUser());
	}
	
}





