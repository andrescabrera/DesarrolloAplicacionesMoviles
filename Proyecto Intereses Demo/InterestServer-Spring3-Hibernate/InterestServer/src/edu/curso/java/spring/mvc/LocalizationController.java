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

import edu.curso.java.spring.bo.Localization;
import edu.curso.java.spring.bo.Message;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class LocalizationController {
	@Autowired
	private InteresesService interesesService;

	// @ResponseBody - REST
	// Listar localizaciones
	@RequestMapping(method = RequestMethod.GET, value = "/localizations")
	public @ResponseBody
	List<Localization> findLocalization() {
		List<Localization> interests = interesesService.findLocalizations();
		return interests;
	}

	// Agregar localizacion
	@RequestMapping(method = RequestMethod.POST, value = "/localization")
	public @ResponseBody
	Localization addLocalization(@RequestBody Localization localization) {
		interesesService.createLocalization(localization);
		return localization;
	}

	// Actualizar localizacion
	@RequestMapping(method = RequestMethod.PUT, value = "/localization/{id}")
	public @ResponseBody
	Localization updateLocalization(@RequestBody Localization localization,
			@PathVariable String id) {
		localization.setId(Long.parseLong(id));
		interesesService.updateLocalization(localization);
		return localization;
	}

	// Mostrar localizacion por id
	@ResponseBody
	@RequestMapping(value = "/localization/{id}", method = RequestMethod.GET)
	public Localization findLocalization(@PathVariable String id,
			HttpServletResponse response) {
		Localization l = null;
		if (id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			l = interesesService.readLocalization(Long.parseLong(id));
		}
		return l;
	}

	// Borrar localizacion
	@RequestMapping(method = RequestMethod.DELETE, value = "/localization/{id}")
	public @ResponseBody
	void removeLocalization(@PathVariable String id) {
		interesesService.deleteLocalization(interesesService
				.readLocalization(Long.parseLong(id)));
	}
}
