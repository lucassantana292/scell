package com.fatec.scell.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	@Query("SELECT l FROM Emprestimo l WHERE l.isbn = :isbn")
	public Emprestimo findByIsbn(@Param("isbn") String isbn);
}
