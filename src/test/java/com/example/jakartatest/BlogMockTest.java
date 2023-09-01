package com.example.jakartatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.jakartatest.entity.TBlog;
import com.example.jakartatest.repository.BlogRepository;
import com.example.jakartatest.service.BlogService;
import com.example.jakartatest.service.ModeService;
import com.example.jakartatest.utils.CommonResult;

/**
 * @ClassName BlogMockTest
 * @Description TODO
 * @Author Liker
 * @Date 2023-08-31 02:30:46
 */

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BlogMockTest {
	
    @Mock
    private BlogRepository blogRepository;
    
    @Mock
    private ModeService modeService;
    
    @InjectMocks
    private BlogService blogService;
 
//    @BeforeAll
//    void beforeAllInit() {
//        System.out.println("running before all");
//    }
// 
//    @AfterAll
//    void afterAllCleanUp() {
//        System.out.println("running after all");
//    }
//    
//    TBlog inputTBlog = new TBlog();
//    TBlog oldTBlog = new TBlog();
//    
//    @BeforeEach
//    void init() {
//    	inputTBlog.setTitle("Redis应用");
//    	inputTBlog.setAuthor("Liker");
//    	inputTBlog.setContent("Redis应用详情");
//    	
//    	oldTBlog.setId(1);
//    	oldTBlog.setTitle("zookeeper应用123");
//    	oldTBlog.setAuthor("Cindy123");
//    	oldTBlog.setContent("zookeeper应用详情123");
//    	oldTBlog.setCreatedTime(new Date());
//    	oldTBlog.setUpdatedTime(new Date());
//        System.out.println("running before each...");
//    }
// 
//    @AfterEach
//    void cleanUp() {
//        System.out.println("running after each...");
//    }
    
    @Test
    void updateBlogTest() {
    	
        TBlog inputTBlog = new TBlog();
        TBlog oldTBlog = new TBlog();
	
    	inputTBlog.setTitle("Redis应用");
    	inputTBlog.setAuthor("Liker");
    	inputTBlog.setContent("Redis应用详情");
    	
    	oldTBlog.setId(1);
    	oldTBlog.setTitle("zookeeper应用123");
    	oldTBlog.setAuthor("Cindy123");
    	oldTBlog.setContent("zookeeper应用详情123");
    	oldTBlog.setCreatedTime(new Date());
    	oldTBlog.setUpdatedTime(new Date());
    	
        //使用when对depdService方法进行模拟，使用eq、anyString对参数进行检验，使用threnReturn返回结果
        when(modeService.getOldBlog(Mockito.any(TBlog.class))).thenReturn(oldTBlog);
        when(blogRepository.save(Mockito.any(TBlog.class))).thenReturn(null);
        CommonResult res = blogService.updateBlog(inputTBlog);
        assertEquals(200, res.getStatus());

    }
    
}
