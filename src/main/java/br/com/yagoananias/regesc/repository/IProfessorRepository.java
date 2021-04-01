package br.com.yagoananias.regesc.repository;

import br.com.yagoananias.regesc.orm.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor, Long> {
}
