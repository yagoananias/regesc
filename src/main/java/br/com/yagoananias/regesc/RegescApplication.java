package br.com.yagoananias.regesc;

import br.com.yagoananias.regesc.orm.Professor;
import br.com.yagoananias.regesc.repository.IProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {

	private IProfessorRepository repository;

	public RegescApplication(IProfessorRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Andressa", "xpto");

		System.out.println("Professor antes da persistencia:");
		System.out.println(professor);

		this.repository.save(professor);

		System.out.println("Professor depois do save");
		System.out.println(professor.toString());
	}
}
