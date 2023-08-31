package com.example.jakartatest.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.jakartatest.entity.TBlog;

@Repository
public interface BlogRepository extends JpaRepository<TBlog, Integer> {
	
	@Query(name = "getTitleById", nativeQuery = true, value = "select title from t_blog where id = :id")
	String getTitleById(int id);
	
	@Modifying
	@Query(name = "updateContentById", nativeQuery = true, value = "update t_blog set content = ?1 where id = ?2")
	void updateContentById(String content, int id);

	@Query(name = "getTitlesByAuthors", nativeQuery = true, value = "select title from t_blog where author in (:authors)")
	ArrayList<String> getTitlesByAuthors(ArrayList<String> authors);
	
}
