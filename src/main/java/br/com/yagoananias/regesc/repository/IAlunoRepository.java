package br.com.yagoananias.regesc.repository;

import br.com.yagoananias.regesc.orm.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface IAlunoRepository extends CrudRepository<Aluno, Long> {
}
