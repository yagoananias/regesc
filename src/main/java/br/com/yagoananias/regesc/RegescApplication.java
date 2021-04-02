package br.com.yagoananias.regesc;

import br.com.yagoananias.regesc.orm.Professor;
import br.com.yagoananias.regesc.service.CrudDisciplinaService;
import br.com.yagoananias.regesc.service.CrudProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {

	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;

	// os obj passados sao injetados autom pelo spring (@service)
	public RegescApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService) {
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);


		while (isTrue) {
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");

			int opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					this.professorService.menu(scanner);
					break;
				case 2:
					this.disciplinaService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}
		}
		System.out.println("Fim do programa");
	}
}
