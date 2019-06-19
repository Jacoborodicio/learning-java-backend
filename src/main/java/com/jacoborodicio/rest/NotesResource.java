package com.jacoborodicio.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Note;

@Path("/notes")
public class NotesResource {
	
	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_PLAIN)
	public String getNotes() {
		return "This will return all notes!";
	}
	
	@GET
	@Path("/demojson")
	@Produces(MediaType.APPLICATION_JSON)
	public Note demoJson() {
		Note note = new Note("Nota inicializada a la fuerza sin interacción de la bdd", 3, "Primera nota demo");
		return note;
	}
}
