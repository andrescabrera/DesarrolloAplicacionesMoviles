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

import edu.curso.java.spring.bo.Post;
import edu.curso.java.spring.bo.User;
import edu.curso.java.spring.service.InteresesService;

@Controller
public class PostController {
		
		@Autowired
		private InteresesService interesesService;
		
		//@ResponseBody - REST
		//Listar publicaciones
		@RequestMapping(method = RequestMethod.GET, value="/posts") 
		public @ResponseBody List<Post> findPosts (){
			List<Post> posts = interesesService.findPosts();
			return posts;
		}
		//Agregar publicacion
		@RequestMapping(method = RequestMethod.POST, value = "/post")
		public @ResponseBody
		Post addPost(@RequestBody Post post) {
			interesesService.createPost(post);
			return post;
		}
		
		//Actualizar publicacion
		@RequestMapping(method = RequestMethod.PUT, value="/post/{id}")
		public @ResponseBody
		Post updatePost(@RequestBody Post post, @PathVariable String id) {
			post.setId(Long.parseLong(id));
			interesesService.updatePost(post);
			return post;
		}
		
		//Mostrar publicacion por id
		@ResponseBody
		@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
		public Post findPost(@PathVariable String id, HttpServletResponse response) {
			Post post = null;
			if(id.isEmpty()) {
				try {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				} catch (IOException e) {
					e.printStackTrace();
				}				
			} else {
				post = interesesService.readPost(Long.parseLong(id));
			}	
			return post;
		}
		
		@ResponseBody
		@RequestMapping(value = "/post/{idPost}/autor", method = RequestMethod.GET)
		public User findPostAutor(@PathVariable String idPost) { 
			return interesesService.findPostAutor(Long.parseLong(idPost));
		}
		
		// Borrar publicacion
		@RequestMapping(method = RequestMethod.DELETE, value = "/post/{id}")
		public @ResponseBody
		void removePost(@PathVariable String id) {
			interesesService.deletePost(interesesService.readPost(Long.parseLong(id)));
		}
		// searchBy
		@ResponseBody
		@RequestMapping(value = "/posts/search/text/{text}", method = RequestMethod.GET)
		public List<Post> searchByName(@PathVariable String text) {
			List<Post> posts = interesesService.findPostByText(text);
			return posts;
		}
		
		@ResponseBody
		@RequestMapping(value = "/posts/search/interest/{interest}", method = RequestMethod.GET)
		public List<Post> searchByInterest(@PathVariable String interest) { 
			List<Post> posts = interesesService.findPostsByInterest(Long.parseLong(interest));
			return posts;
		}
}
