package com.CKishore.springmvc.todo;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface TodoRepo extends JpaRepository<Todo,Integer> {

	public List<Todo> findByUsername(String username);
}
