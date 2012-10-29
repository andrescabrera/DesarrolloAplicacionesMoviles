package edu.curso.java.spring.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.curso.java.spring.bo.*;
import edu.curso.java.spring.dao.*;

@Service
@Transactional
public class InteresesService {

	//private static Log log = LogFactory.getLog(InteresesService.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private ZoneDAO zoneDAO;

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private RatingDAO ratingDAO;

	@Autowired
	private InterestDAO interestDAO;

	@Autowired
	private LocalizationDAO localizationDAO;

	// CRUD Usuarios
	public Long createUser(User user) {
		return userDAO.create(user);
	}

	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	public void updateUser(User user) {
		userDAO.update(user);
	}

	public User readUser(Long id) {
		return userDAO.read(id);
	}

	// Devuelve todos los usuarios
	public List<User> findUsers() {
		return userDAO.findAll();
	}

	// Busca usuarios por su nombre completo
	public List<User> findUserByName(String name, String surname) {
		return userDAO.findByName(name, surname);
	}

	// CRUD Publicaciones
	public Post readPost(Long id) {
		return postDAO.read(id);
	}

	public Long createPost(Post post) {
		return postDAO.create(post);
	}

	public void deletePost(Post post) {
		postDAO.delete(post);
	}

	public void updatePost(Post post) {
		postDAO.update(post);
	}

	// Devuelve todos las publicaciones
	public List<Post> findPosts() {
		return postDAO.findAll();
	}

	// Busca publicaciones por contenido
	public List<Post> findPostByText(String text) {
		return postDAO.findByText(text);
	}

	// CRUD Zonas
	public Long createZone(Zone zone) {
		return zoneDAO.create(zone);
	}

	public void deleteZone(Zone zone) {
		zoneDAO.delete(zone);
	}

	public void updateZone(Zone zone) {
		zoneDAO.update(zone);
	}

	public Zone readZone(Long id) {
		return zoneDAO.read(id);
	}

	// Devuelve todas las zonas
	public List<Zone> findZones() {
		return zoneDAO.findAll();
	}

	public List<Zone> findZoneByName(String zoneName) {
		return zoneDAO.findByName(zoneName);
	}
	
//	public Set<Zone> findCityZonesByCountry(Long countryId) {
//		Zone country = zoneDAO.read(countryId);
//		Set<Zone> childZones = new HashSet<Zone>();
//		for(Zone state : country.getZones()){
//			for(Zone city : state.getZones()){
//				childZones.add(city);
//			}
//		}
//		return childZones;
//	}
//	
//	public Set<Zone> findChildZones(Long Id) {
//		Zone zona = zoneDAO.read(Id);
//		Set<Zone> childZones = new HashSet<Zone>();
//		for(Zone child : zona.getZones()){
//			childZones.add(child);
//		}
//		return childZones;
//	}
	
	// CRUD Intereses
	public Long createInterest(Interest interest) {
		return interestDAO.create(interest);
	}

	public void deleteInterest(Interest interest) {
		interestDAO.delete(interest);
	}

	public void updateInterest(Interest interest) {
		interestDAO.update(interest);
	}

	public Interest readInterest(Long id) {
		return interestDAO.read(id);
	}

	// Devuelve todos los intereses
	public List<Interest> findInterests() {
		return interestDAO.findAll();
	}

	public List<Interest> findInterestByDescription(String interestName) {
		return interestDAO.findByDescription(interestName);
	}

	// CRUD Mensajes
	public Long createMessage(Message message) {
		return messageDAO.create(message);
	}

	public void deleteMessage(Message message) {
		messageDAO.delete(message);
	}

	public void updateMessage(Message message) {
		messageDAO.update(message);
	}

	public Message readMessage(Long id) {
		return messageDAO.read(id);
	}

	// Devuelve todos los mensajes
	public List<Message> findMessages() {
		return messageDAO.findAll();
	}

	public List<Message> findMessageBySender(Long senderId) {
		return messageDAO.findBySender(senderId);
	}

	// CRUD Localizaciones
	public Long createLocalization(Localization localization) {
		return localizationDAO.create(localization);
	}

	public void deleteLocalization(Localization localization) {
		localizationDAO.delete(localization);
	}

	public void updateLocalization(Localization localization) {
		localizationDAO.update(localization);
	}

	public Localization readLocalization(Long id) {
		return localizationDAO.read(id);
	}

	// Devuelve todas las localizaciones
	public List<Localization> findLocalizations() {
		return localizationDAO.findAll();
	}

	// CRUD Ratings
	public Long createRating(Rating rating) {
		return ratingDAO.create(rating);
	}

	public void deleteRating(Rating rating) {
		ratingDAO.delete(rating);
	}

	public void updateRating(Rating rating) {
		ratingDAO.update(rating);
	}

	public Rating readRating(Long id) {
		return ratingDAO.read(id);
	}

	// Devuelve todos los puntajes
	public List<Rating> findRatings() {
		return ratingDAO.findAll();
	}
}
