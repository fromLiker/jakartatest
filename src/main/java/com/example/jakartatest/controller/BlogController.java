package com.example.jakartatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.service.BlogService;
import com.example.jakartatest.utils.CommonResult;

import jakarta.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("/test/path")
public class BlogController {
	
//	CREATE TABLE `t_blog` (
//	`id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
//	`title` varchar (60) DEFAULT NULL COMMENT '博客标题',
//	`author` varchar (60) DEFAULT NULL COMMENT '博客作者',
//	`content` mediumtext COMMENT '博客内容',
//	`created_time` datetime DEFAULT NULL COMMENT '创建时间',
//	`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
//	PRIMARY KEY (`id`)
//	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
	
//	INSERT INTO `testdb`.`t_blog` (`title`, `author`, `content`, `created_time`, `updated_time`) VALUES ('Springboot怎么这么优秀', 'Liker', 'Springboot的优势', '2023-07-07 11:25:39', '2023-07-08 12:25:50');
	
	@Autowired
	private BlogService blogService;
	
	// Get localhost:8755/test/path/getBlog/1
	@GetMapping("/getBlog/{id}")
	public CommonResult getBlog(@PathVariable("id") Integer id) {
		return blogService.getBlog(id);
	}
	
	// Get localhost:8755/test/path/getBlog?id=1
	@GetMapping("/getBlog")
	public CommonResult getBlogById(@PathParam("id") Integer id) {
		return blogService.getBlog(id);
	}

	@GetMapping("/getTitle")
	public CommonResult getTitle(@PathVariable("id") Integer id) {
		return blogService.getTitleById(id);
	}

	@GetMapping("/getTitlesByAuthors")
	public CommonResult getTitlesByAuthors() {
		return blogService.getTitlesByAuthors();
	}
	
	@PostMapping("/newBlog")
	public CommonResult newBlog(@RequestBody TBlog tblog) {
		return blogService.newBlog(tblog);
	}
	
	@PutMapping("/updateBlog")
	public CommonResult updateBlog(@RequestBody TBlog tblog) {
		return blogService.updateBlog(tblog);
	}
	
	// localhost:8755/test/path/updateContent/1?content=XXX
	@PostMapping("/updateContent/{id}")
	public CommonResult updateContent(@PathVariable("id") Integer id, @PathParam("content") String content) {
		return blogService.updateContentById(id, content);
	}
	
	@DeleteMapping("/deleteBlog/{id}")
	public CommonResult deleteBlog(@PathVariable("id") Integer id) {
		return blogService.deleteBlogById(id);
	}
	
}
