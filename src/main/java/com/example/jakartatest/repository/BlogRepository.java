package com.example.jakartatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jakartatest.entity.TBlog;

@Repository
public interface BlogRepository extends JpaRepository<TBlog, Integer> {

}
