package com.jinu.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinu.memo.post.domain.Post;
import com.jinu.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/list-view")
	public String memoList(
	        Model model,
	        HttpSession session) {

	    Integer userId = (Integer) session.getAttribute("userId");

	    // userId가 null인 경우 예외를 던지거나 기본값을 설정합니다.
	    if (userId == null) {
	        // 예를 들어, 로그인 페이지로 리다이렉트
	        return "redirect:/login"; // 로그인 페이지의 URL로 변경
	    }

	    List<Post> postList = postService.getPostList(userId);

	    model.addAttribute("memoList", postList);

	    return "post/list";
	}

	
	@GetMapping("/create-view")
	public String inputMemo() {
		return "post/input";
	}
	
	@GetMapping("/detail-view")
	public String memoDetail(
			@RequestParam("id") int id
			, Model model) {
		
		Post post = postService.getPost(id);
		
		model.addAttribute("memo", post);
		
		return "post/detail";
	}

}
