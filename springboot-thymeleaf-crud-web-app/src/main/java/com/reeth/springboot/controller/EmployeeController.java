package com.reeth.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.reeth.springboot.model.Employee;
import com.reeth.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		//retrive list of employees and added to the model
		model.addAttribute("ListEmployees",employeeService.getAllEmployees());
		return "index"; //return the view
	}
		@GetMapping("/showNewEmployeeForm")
		public String showNewEmployeeForm(Model model) {
			Employee employee=new Employee();
			//create a model attribute to bind form data
			model.addAttribute("employee",employee); //Thymeleaf template will access this empty employee object for binding form data
			return "new_employee";	
		}
		
		@PostMapping("/saveEmployee")
		public String saveEmployee(@ModelAttribute("employee") Employee employee) {
			//save employee to the database
			employeeService.saveEmployee(employee);
			return "redirect:/"; //redirect to the home page	
		}
		
		@GetMapping("/showFormForUpdate/{id}")
		public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
			//get employee from the service
			Employee employee=employeeService.getEmployeeById(id);
			//set employee as a model attribute to pre-populate the form
			model.addAttribute("employee",employee);
			return "update_employee";	
		}
		
		@GetMapping("/deleteEmployee/{id}")
		public String deleteEmployee(@PathVariable(value="id") long id) {
			//call the delete employee method
			this.employeeService.deleteEmployeeById(id);
			return "redirect:/";
		}
		
	

}
