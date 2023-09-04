package com.example.jakartatest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.service.BlogService;

/**
 * @ClassName BlogTest
 * @Description TODO
 * @Author Liker
 * @Date 2023-09-04 09:54:54
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BlogTestViaDB {
	
    @Autowired
    private BlogService blogService;
    
    @Test
    void saveBlogTest() {
    	TBlog tBlog = new TBlog();
    	tBlog.setTitle("Redis应用");
    	tBlog.setAuthor("Liker");
    	tBlog.setContent("Redis应用详情");
        assertEquals(200, blogService.newBlog(tBlog).getStatus());
    }
}
