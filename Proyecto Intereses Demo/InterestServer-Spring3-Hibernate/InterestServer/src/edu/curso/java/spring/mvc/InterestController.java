package edu.curso.java.spring.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.curso.java.spring.bo.Interest;
import edu.curso.java.spring.bo.Zone;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class InterestController {

	@Autowired
	private InteresesService interesesService;

	// @ResponseBody - REST
	// Listar intereses
	@RequestMapping(method = RequestMethod.GET, value = "/interests")
	public @ResponseBody
	List<Interest> findInterests() {
		List<Interest> interests = interesesService.findInterests();
		return interests;
	}

	// Agregar interes
	@RequestMapping(method = RequestMethod.POST, value = "/interest")
	public @ResponseBody
	Interest addInterest(@RequestBody Interest interest) {
		interesesService.createInterest(interest);
		return interest;
	}

	// Actualizar interes
	@RequestMapping(method = RequestMethod.PUT, value = "/interest/{id}")
	public @ResponseBody
	Interest updateInterest(@RequestBody Interest interest,
			@PathVariable String id) {
		interest.setId(Long.parseLong(id));
		interesesService.updateInterest(interest);
		return interest;
	}

	// Mostrar interes por id
	@ResponseBody
	@RequestMapping(value = "/interest/{id}", method = RequestMethod.GET)
	public Interest findInterest(@PathVariable String id,
			HttpServletResponse response) {
		Interest i = null;
		if (id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			i = interesesService.readInterest(Long.parseLong(id));
		}
		return i;
	}

	// Borrar interes
	@RequestMapping(method = RequestMethod.DELETE, value = "/interest/{id}")
	public @ResponseBody
	void removeInterest(@PathVariable String id) {
		interesesService.deleteInterest(interesesService.readInterest(Long
				.parseLong(id)));
	}

	// searchBy
	@ResponseBody
	@RequestMapping(value = "/interests/search/{text}", method = RequestMethod.GET)
	public List<Interest> searchByName(@PathVariable String text) {
		List<Interest> interests = interesesService
				.findInterestByDescription(text);
		return interests;
	}
}
