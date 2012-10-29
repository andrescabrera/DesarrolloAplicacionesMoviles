package edu.curso.java.spring.mvc;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.curso.java.spring.bo.User;
import edu.curso.java.spring.bo.Zone;
import edu.curso.java.spring.mvc.form.UserForm;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class UserController {

	@Autowired
	private InteresesService interesesService;

	//@ResponseBody - REST
	//Listar usuarios
	@RequestMapping(method = RequestMethod.GET, value="/users") 
	public @ResponseBody List<User> findUsers (){
		List<User> users = interesesService.findUsers();
		return users;
	}
	//Agregar usuario
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public @ResponseBody
	User addUser(@RequestBody User user) {
		interesesService.createUser(user);
		return user;
	}
	
	//Actualizar usuario
	@RequestMapping(method = RequestMethod.PUT, value="/user/{id}")
	public @ResponseBody
	User updateUser(@RequestBody User user, @PathVariable String id) {
		user.setId(Long.parseLong(id));
		interesesService.updateUser(user);
		return user;
	}
	
	//Mostrar usuario por id
	@ResponseBody
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User findUser(@PathVariable String id, HttpServletResponse response) {
		User user = null;
		if(id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			user = interesesService.readUser(Long.parseLong(id));
		}
		return user;
	}
	
	// Borrar usuario
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public @ResponseBody
	void removeUser(@PathVariable String id) {
		interesesService.deleteUser(interesesService.readUser(Long.parseLong(id)));
	}
	// searchBy
	@ResponseBody
	@RequestMapping(value = "/users/search/{surname}+{name}", method = RequestMethod.GET)
	public List<User> searchByName(@PathVariable String surname,
			@PathVariable String name) {
		List<User> users = interesesService.findUserByName(name, surname);
		return users;
	}
	

//	// **.. No REST - Metodos para la vista jsp
//
//	// Instancia y muestra el formulario
//	@RequestMapping
//	public String showForm(Model model) {
//		UserForm userForm = new UserForm();
//		model.addAttribute("userForm", userForm);
//		return "/users/form";
//	}
//
//	// Recibe el formulario, crear un nuevo usuario o actualiza.
//	@RequestMapping(method = RequestMethod.POST)
//	public String createOrUpdate(
//			@ModelAttribute("userForm") @Valid UserForm userForm,
//			BindingResult result, Model model) {
//
//		System.out.println(result.hasErrors());
//
//		if (result.hasErrors()) {
//			return "/users/form";
//		}
//
//		Long id = userForm.getId();
//		User user = null;
//		if (id != null) {
//			user = interesesService.readUser(id);
//			BeanUtils.copyProperties(userForm, user);
//			interesesService.updateUser(user);
//		} else {
//			user = new User();
//			BeanUtils.copyProperties(userForm, user);
//			id = interesesService.createUser(user);
//			System.out.println("El nuevo id es: " + id);
//			model.addAttribute("user", user);
//		}
//
//		return "redirect:/users/view.html?id=" + id;
//	}
//
//	// Editar un usuario: Lo busca, instancia un formulario y lo setea y
//	// devuelve a la vista
//	@RequestMapping
//	public String edit(@RequestParam("id") long id, Model model) {
//		User user = interesesService.readUser(id);
//		UserForm userForm = new UserForm();
//		BeanUtils.copyProperties(user, userForm);
//		model.addAttribute("userForm", userForm);
//		return "/users/form";
//	}
//
//	// Lista los usuarios
//	@RequestMapping(value = "/list.html")
//	public String list(Model model) {
//		List<User> users = interesesService.findUsers();
//		model.addAttribute("users", users);
//		return null;
//	}
//
//	// Se recibe un id de empleado y se lo borra
//	@RequestMapping
//	public String delete(@RequestParam("id") long id, Model model) {
//		interesesService.deleteUser(interesesService.readUser(id));
//		return "redirect:/users/list.html";
//	}
}
