package com.jinu.memo.user.service;

import org.springframework.stereotype.Service;

import com.jinu.memo.common.MD5HashingEncoder;
import com.jinu.memo.user.domain.User;
import com.jinu.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	// 생성자가 autowired를 위한 것 하나만 존재하는 경우 autowired 생략
	// @Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public int addUser(
			String loginId
			, String password
			, String name
			, String email){
		
		// 암호화
		String encryptPassword = MD5HashingEncoder.edcode(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}
	
	public User getUser(String loginId, String password){
		
		String encryptPassword = MD5HashingEncoder.edcode(password);
		return userRepository.selectUser(loginId, password);
	}

}
