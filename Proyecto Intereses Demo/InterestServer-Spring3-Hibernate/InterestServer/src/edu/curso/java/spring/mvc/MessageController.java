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
import edu.curso.java.spring.bo.Message;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class MessageController {
	@Autowired
	private InteresesService interesesService;
	
	//@ResponseBody - REST
	//Listar mensajes
	@RequestMapping(method = RequestMethod.GET, value="/messages") 
	public @ResponseBody List<Message> findMessages (){
		List<Message> messages = interesesService.findMessages();
		return messages;
	}
	//Agregar mensaje
	@RequestMapping(method = RequestMethod.POST, value = "/message")
	public @ResponseBody
	Message addMessage(@RequestBody Message message) {
		interesesService.createMessage(message);
		return message;
	}
	
	//Actualizar mensaje
	@RequestMapping(method = RequestMethod.PUT, value="/message/{id}")
	public @ResponseBody
	Message updateMessage(@RequestBody Message message, @PathVariable String id) {
		message.setId(Long.parseLong(id));
		interesesService.updateMessage(message);
		return message;
	}
	
	//Mostrar mensaje por id
	@ResponseBody
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public Message findMessage(@PathVariable String id, HttpServletResponse response) {
		Message m = null;
		if (id.isEmpty()) {
			try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			m = interesesService.readMessage(Long.parseLong(id));
		}
		return m;
	}
	
	// Borrar mensaje
	@RequestMapping(method = RequestMethod.DELETE, value = "/message/{id}")
	public @ResponseBody
	void removeMessage(@PathVariable String id) {
		interesesService.deleteMessage(interesesService.readMessage(Long.parseLong(id)));
	}
	// Buscar los mensajes enviados por un usuario
	@ResponseBody
	@RequestMapping(value = "/messages/sentBy/{senderId}", method = RequestMethod.GET)
	public List<Message> searchBySender(@PathVariable String senderId) {
		List<Message> messages = interesesService.findMessageBySender(Long.parseLong(senderId));
		return messages;
	}
	
//	//Buscar los mensajes recibidos por un usuario
//	@ResponseBody
//	@RequestMapping(value="/messages/receivedBy/{receiverId}", method = RequestMethod.GET)
//	public List<Message> searchByReceiver(@PathVariable String receiverId) {
//		List<Message> messages = interesesService.findByReceiver(Long.parseLong(receiverId));
//		return messages;
//	}
}
