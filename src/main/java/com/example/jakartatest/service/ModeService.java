package com.example.jakartatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.repository.BlogRepository;

/**
 * @ClassName ModeService
 * @Description TODO
 * @Author Liker
 * @Date 2023-08-31 01:57:02
 */
@Service
public class ModeService {

	@Autowired
	private BlogRepository blogRepository;
	
	public TBlog getOldBlog(TBlog tblog) {
		return blogRepository.findById(tblog.getId()).get();
	}

}
