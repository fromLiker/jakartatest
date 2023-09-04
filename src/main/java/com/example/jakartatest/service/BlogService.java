package com.example.jakartatest.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.repository.BlogRepository;
import com.example.jakartatest.utils.CommonResult;
import com.example.jakartatest.utils.ResponseCode;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ModeService modeService;

	public CommonResult getBlog(int id) {
		try {
			TBlog blog = blogRepository.findById(id).get();
			return CommonResult.build(ResponseCode.SUCCESS, "Blog search successfully!", blog);
		} catch (Exception e) {
			log.error("Fail to search blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to search blog!");
		}
	}
	
	public CommonResult getTitleById(int id) {
		try {
			String title = blogRepository.getTitleById(id);
			return CommonResult.build(ResponseCode.SUCCESS, "Get title Successfully!", title);
		} catch (Exception e) {
			log.error("Fail to new blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to new blog!");
		}
	}

	// test
	public CommonResult getTitlesByAuthors() {
		try {		
			ArrayList<String> authors = addAuthors();
			ArrayList<String> titles = blogRepository.getTitlesByAuthors(authors);
			return CommonResult.build(ResponseCode.SUCCESS, "Get titles by authors Successfully!", titles.toString());
		} catch (Exception e) {
			log.error("Fail to get titles, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to get titles!");
		}
	}

	/**
	 * @MethodName: addAuthors
	 * @Description: TODO
	 * @Author Liker
	 * @return ArrayList<String>
	 * @Date 2023-09-01 11:15:16
	 */
	public ArrayList<String> addAuthors() {
		ArrayList<String> authors = new ArrayList<>();
		authors.add("Liker");
		authors.add("Steven");
		return authors;
	}
	
	public CommonResult newBlog(TBlog tblog) {
		try {
			tblog.setCreatedTime(new Date());
			tblog.setUpdatedTime(new Date());
			// jpa给修改操作加了默认事务，所以必须加上flush()方法提交才能真正的保存数据。
			TBlog res = blogRepository.saveAndFlush(tblog);
			log.info("saveAndFlush");
			return CommonResult.build(ResponseCode.SUCCESS, "New a blog Successfully!", res);
		} catch (Exception e) {
			log.error("Fail to new blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to new blog!");
		}
	}

	// test
	public CommonResult updateBlog(TBlog tblog) {
		try {
			TBlog old = modeService.getOldBlog(tblog);	
			old.setTitle(tblog.getTitle());
			old.setContent(tblog.getContent());
			old.setAuthor(tblog.getAuthor());
			old.setUpdatedTime(new Date());
			TBlog savedTBlog = blogRepository.save(old);
			return CommonResult.build(ResponseCode.SUCCESS, "Blog updated successfully!", savedTBlog.toString());
		} catch (Exception e) {
			log.error("Fail to update blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to update blog!");
		}
	}

	@Transactional
	public CommonResult updateContentById(Integer id, String content) {
		try {		
			blogRepository.updateContentById(content, id);
			return CommonResult.build(ResponseCode.SUCCESS, "Blog updated successfully!");
		} catch (Exception e) {
			log.error("Fail to update blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to update blog!");
		}
	}

	public CommonResult deleteBlogById(Integer id) {
		try {		
			blogRepository.deleteById(id);
			return CommonResult.build(ResponseCode.SUCCESS, "Blog delete successfully!");
		} catch (Exception e) {
			log.error("Fail to delete blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to delete blog!");
		}
	}

}
