package br.com.yagoananias.regesc.repository;

import br.com.yagoananias.regesc.orm.Disciplina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisciplinaRepository extends CrudRepository<Disciplina, Long> {
}
