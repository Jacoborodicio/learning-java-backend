package com.jacoborodicio.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import model.Note;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNote(@PathParam("id") int id) {
		try {
			TypedQuery<Note> note = this.em.createNamedQuery("Note.findById", Note.class);
			note.setParameter("id", id);
			Note finalNote = note.getSingleResult();	
			return Response.ok(finalNote).build();
		} catch (NoResultException ex) {
			return Response.ok("No se han encontrado resultados con el id: "+id).build();
		}
//		if(finalNote != null) {
//			System.out.println("Si hay una nota con el id especificado --> "+id);
//			return Response.ok(finalNote).build();			
//		} else {
//			return Response.ok("Este es el id que entró: "+id+" pero no se encontró nada...").build();
//		}
	}
}
