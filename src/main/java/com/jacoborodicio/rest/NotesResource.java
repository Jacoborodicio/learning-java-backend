package com.jacoborodicio.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import model.Note;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Path("/notes")
public class NotesResource {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("api-rest");
	EntityManager em = emf.createEntityManager();
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotes() {
		TypedQuery<Note> notes = this.em.createNamedQuery("Note.findAll", Note.class);
		List<Note> totalNotes = notes.getResultList();
		return Response.ok(totalNotes).build();
	}
	
	@GET
	@Path("/demojson")
	@Produces(MediaType.APPLICATION_JSON)
	public Note demoJson() {
		Note note = new Note();
		note.setDescription("Description of a note this time already using entities");
		note.setTitle("Aún falta mucho pero vamos yendo!");
		return note;
	}
}
