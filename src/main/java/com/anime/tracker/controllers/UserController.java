package com.anime.tracker.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.User;
import com.anime.tracker.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = this.userService.getUsers()
				.stream().map(user -> EntityModel.of(user,
						linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
						linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
						.collect(Collectors.toList());

				return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Long id) {
		User user = this.userService.getUserById(id);
		EntityModel<User> entityModel = EntityModel.of(user,
				linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
		return entityModel;
	}
}