package com.CKishore.springmvc.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;import org.eclipse.jdt.internal.compiler.ast.SuperReference;
import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todocount=0;
	static
	{
		todos.add(new Todo(++todocount,"kishore","learn AWS",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todocount,"kishore","learn Java",
				LocalDate.now().plusYears(2),false));
		todos.add(new Todo(++todocount,"kishore","learn SQL",
				LocalDate.now().plusYears(3),false));
	}
	public List<Todo> allListtodosbyusername(String username)
	{
		Predicate<? super Todo> predicate = todo->todo.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addtodo(String username,String description,LocalDate date,boolean done)
	{	
		todos.add(new Todo(++todocount,username,
				description,date.plusYears(1),done));
	}
	
	public void deleteTodo(int id)
	{
		Predicate<? super Todo> predicate= todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}

	public Todo findByid(int id) {
		Predicate<? super Todo> predicate=todo->todo.getId()==id;
		return todos.stream().filter(predicate).findFirst().get();
	}

	public void updateTodo(Todo todo) {
	deleteTodo(todo.getId());
	todos.add(todo);	
	}
	
	public String findLoggedInUserName() {
		Authentication authentication= SecurityContextHolder.getContext()
									.getAuthentication();
		return authentication.getName();
	}
}
