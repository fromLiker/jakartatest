package com.example.jakartatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class BlogMockTest {
	
	@Mock
    private BlogRepository blogRepository;
    
	@Mock
    private ModeService modeService;
    
    @InjectMocks
    @Spy // 同类调用需要加注释@Spy, 否则报错
    private BlogService blogService;

    TBlog inputTBlog = new TBlog();
    TBlog oldTBlog = new TBlog();

    @BeforeAll
    void beforeAllInit() {
        System.out.println("running before all");
    }
 
    @AfterAll
    void afterAllCleanUp() {
        System.out.println("running after all");
    }
  
	@BeforeEach
    void init() throws ParseException {

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    	inputTBlog.setTitle("Redis应用");
    	inputTBlog.setAuthor("Liker");
    	inputTBlog.setContent("Redis应用详情");
    	
    	oldTBlog.setId(1);
    	oldTBlog.setTitle("zookeeper应用123");
    	oldTBlog.setAuthor("Cindy123");
    	oldTBlog.setContent("zookeeper应用详情123");
    	oldTBlog.setCreatedTime(sdf.parse("2022-12-11 13:00:00"));
    	oldTBlog.setUpdatedTime(new Date());
        System.out.println("running before each...");
    }
 
    @AfterEach
    void cleanUp() {
        System.out.println("running after each...");
    }
    
    @Test
    void updateBlogTest() {
        //使用when对depdService方法进行模拟，使用eq、anyString对参数进行检验，使用threnReturn返回结果
        when(modeService.getOldBlog(Mockito.any(TBlog.class))).thenReturn(oldTBlog);
        when(blogRepository.save(Mockito.any(TBlog.class))).thenReturn(inputTBlog);
        CommonResult res = blogService.updateBlog(inputTBlog);
        assertEquals(200, res.getStatus());

    }
    
    @Test
    void getTitlesByAuthorsTest() {
    	ArrayList<String> authors = new ArrayList<>();
		authors.add("Liker");
		authors.add("Cindy");
		ArrayList<String> titles = new ArrayList<>();
		titles.add("Springboot1");
		titles.add("Springboot2");
		// 同类调用需要加注释@Spy, 否则报错
		Mockito.doReturn(authors).when(blogService).addAuthors();
		Mockito.when(blogRepository.getTitlesByAuthors(authors)).thenReturn(titles);
    	CommonResult res = blogService.getTitlesByAuthors();
        assertEquals("[Springboot1, Springboot2]", res.getData().toString());
    }
    
    @Test
    void newBlogOkTest() {
    	Mockito.when(blogRepository.saveAndFlush(Mockito.any(TBlog.class))).thenReturn(oldTBlog);
    	CommonResult res = blogService.newBlog(inputTBlog);
    	assertEquals(200, res.getStatus());
    }
    
    @Test
    void newBlogFailTest() {
    	
    	Mockito.when(blogRepository.saveAndFlush(Mockito.any(TBlog.class))).thenThrow(new RuntimeException());
        try {
        	blogService.newBlog(inputTBlog);
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
        }
    }
    
}
