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

import edu.curso.java.spring.bo.Message;
import edu.curso.java.spring.bo.Rating;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class RatingController {
	@Autowired
	private InteresesService interesesService;
	
	//@ResponseBody - REST
	//Listar calificaciones
	@RequestMapping(method = RequestMethod.GET, value="/ratings") 
	public @ResponseBody List<Rating> findRating (){
		List<Rating> interests = interesesService.findRatings();
		return interests;
	}
	//Agregar calificacion
	@RequestMapping(method = RequestMethod.POST, value = "/rating")
	public @ResponseBody
	Rating addRating(@RequestBody Rating rating) {
		interesesService.createRating(rating);
		return rating;
	}
	
	//Actualizar calificacion
	@RequestMapping(method = RequestMethod.PUT, value="/rating/{id}")
	public @ResponseBody
	Rating updateRating(@RequestBody Rating rating, @PathVariable String id) {
		rating.setId(Long.parseLong(id));
		interesesService.updateRating(rating);
		return rating;
	}
	
	//Mostrar calificacion por id
	@ResponseBody
	@RequestMapping(value = "/rating/{id}", method = RequestMethod.GET)
	public Rating findRating(@PathVariable String id, HttpServletResponse response) {
		Rating rating = null;
		if (id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			rating = interesesService.readRating(Long.parseLong(id));
		}
		return rating;
	}
	
	// Borrar calificacion
	@RequestMapping(method = RequestMethod.DELETE, value = "/rating/{id}")
	public @ResponseBody
	void removeRating(@PathVariable String id) {
		interesesService.deleteRating(interesesService.readRating(Long.parseLong(id)));
	}
}
