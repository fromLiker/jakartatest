package com.example.jakartatest.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.repository.BlogRepository;
import com.example.jakartatest.utils.CommonResult;
import com.example.jakartatest.utils.ResponseCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

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

	public CommonResult updateBlog(TBlog tblog) {
		try {
			TBlog old = blogRepository.findById(tblog.getId()).get();
			old.setTitle(tblog.getTitle());
			old.setContent(tblog.getContent());
			old.setAuthor(tblog.getAuthor());
			old.setUpdatedTime(new Date());			
			blogRepository.save(old);
			return CommonResult.build(ResponseCode.SUCCESS, "Blog updated successfully!");
		} catch (Exception e) {
			log.error("Fail to update blog, error:", e);
			return CommonResult.build(ResponseCode.FAIL, "Fail to update blog!");
		}
	}

	/**
	 * @MethodName: getBlog
	 * @Description: TODO
	 * @Author Liker
	 * @return CommonResult
	 * @Date 2023-08-30 10:24:47
	 */
	public CommonResult getBlog() {
		// TODO Auto-generated method stub
		return null;
	}

}
