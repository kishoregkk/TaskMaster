package com.CKishore.springmvc.todo;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.CKishore.springmvc.todo.Repository.TodoRepo;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("nam")
public class TodoControler {
	
	@Autowired
	TodoRepo todoRepo;
	
	//list-todos
	@RequestMapping("list-todos")
	public String listoftodos(ModelMap m)
	{
	List<Todo> todos =	todoRepo.findByUsername(findLoggedInUserName());
		m.put("todos", todos);
		return "Listtodos";
	}
	
	@RequestMapping(value="add-todo",method = RequestMethod.GET)
	public String displayaddtodopage(ModelMap model)
	{
		String uname=(String) model.get("nam");
		Todo todo=new Todo(0,uname,"",LocalDate.now(),false);
		model.put("todo", todo);
		return "addtodo";
	}
	
	@RequestMapping(value="add-todo",method = RequestMethod.POST)
	public String afterAddingTodo(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addtodo";
		}
		String uname=findLoggedInUserName();
		todo.setUsername(uname);
		todoRepo.save(todo);
		
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deletebyidoftodo(@RequestParam int id)
	{
		todoRepo.deleteById(id);
		return "redirect:list-todos";
	}
	
	
	@RequestMapping(value="update-todo",method = RequestMethod.GET)
	public String showtheupdatetodopage(@RequestParam int id,ModelMap model)
	{
		Todo todo= todoRepo.findById(id).get();
		model.put("todo", todo);
		return "addtodo";
	}
	@RequestMapping(value="update-todo",method = RequestMethod.POST)
	public String updatethetodovalues(@Valid Todo todo,ModelMap model,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addtodo";
		}
		String uname=findLoggedInUserName();
		todo.setUsername(uname);
		todoRepo.save(todo);
		return "redirect:list-todos";
		
	}
	
	
	public String findLoggedInUserName() {
		Authentication authentication= SecurityContextHolder.getContext()
									.getAuthentication();
		return authentication.getName();
	}
	

}
