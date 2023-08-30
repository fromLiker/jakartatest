package com.example.jakartatest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.service.BlogService;
import com.example.jakartatest.utils.CommonResult;

@CrossOrigin
@RestController
@RequestMapping("/test/path")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/getBlog")
	public CommonResult getBlog() {
		return blogService.getBlog();
	}
	
	@PostMapping("/newBlog")
	public CommonResult newBlog(@RequestBody TBlog tblog) {
		return blogService.newBlog(tblog);
	}
	
	@PutMapping("/updateBlog")
	public CommonResult updateBlog(@RequestBody TBlog tblog) {
		return blogService.updateBlog(tblog);
	}
}
