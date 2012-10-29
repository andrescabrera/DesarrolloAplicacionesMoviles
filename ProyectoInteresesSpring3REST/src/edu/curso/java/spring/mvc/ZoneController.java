package edu.curso.java.spring.mvc;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.curso.java.spring.bo.Zone;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class ZoneController {
	
	@Autowired
	private InteresesService interesesService;
	
	//@ResponseBody - REST
	//Listar zonas
	@RequestMapping(method = RequestMethod.GET, value="/zones") 
	public @ResponseBody List<Zone> findZones (){
		List<Zone> zones = interesesService.findZones();
		return zones;
	}
	//Agregar zona
	@RequestMapping(method = RequestMethod.POST, value = "/zone")
	public @ResponseBody
	Zone addZone(@RequestBody Zone zone) {
		interesesService.createZone(zone);
		return zone;
	}
	
	//Actualizar zona
	@RequestMapping(method = RequestMethod.PUT, value="/zone/{id}")
	public @ResponseBody
	Zone updateZone(@RequestBody Zone zone, @PathVariable String id) {
		zone.setId(Long.parseLong(id));
		interesesService.updateZone(zone);
		return zone;
	}
	
	//Mostrar zona por id
	@ResponseBody
	@RequestMapping(value = "/zone/{id}", method = RequestMethod.GET)
	public Zone findZone(@PathVariable String id, HttpServletResponse response) {
		Zone zone = null;
		if(id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			zone = interesesService.readZone(Long.parseLong(id));
		}
		return zone;
	}
	
	// Borrar zona
	@RequestMapping(method = RequestMethod.DELETE, value = "/zone/{id}")
	public @ResponseBody
	void removeZone(@PathVariable String id) {
		interesesService.deleteZone(interesesService.readZone(Long.parseLong(id)));
	}
	// searchBy
	@ResponseBody
	@RequestMapping(value = "/zones/search/{text}", method = RequestMethod.GET)
	public List<Zone> searchByName(@PathVariable String text) {
		List<Zone> zones = interesesService.findZoneByName(text);
		return zones;
	}
	
	//Recorrer buscando zonas hijas
//	@ResponseBody
//	@RequestMapping(value="/zones/searchByCountry/{countryId}", method = RequestMethod.GET)
//	public Set<Zone> searchZonesByCountry(@PathVariable String countryId) {
//		return interesesService.findCityZonesByCountry(Long.parseLong(countryId));
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/zones/searchChilds/{zoneId}", method = RequestMethod.GET)
//	public Set<Zone> searchChildZones(@PathVariable String zoneId) {
//		return interesesService.findChildZones(Long.parseLong(zoneId));
//	}
}
