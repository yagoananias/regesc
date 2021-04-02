package br.com.yagoananias.regesc.service;

import br.com.yagoananias.regesc.orm.Disciplina;
import br.com.yagoananias.regesc.orm.Professor;
import br.com.yagoananias.regesc.repository.IDisciplinaRepository;
import br.com.yagoananias.regesc.repository.IProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudDisciplinaService {
    private IDisciplinaRepository disciplinaRepository;
    private IProfessorRepository professorRepository; //dependencia da classe CrudProfessorService

    //Spring cria aut um obj com a interface IProfessorRepository
    // e injeta no construtor da classe atual ==> Injeção de dependencia
    public CrudDisciplinaService(IDisciplinaRepository disciplinaRepository, IProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação quer executar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Disciplina");
            System.out.println("3 - Visualizar Disciplinas");
            System.out.println("4 - Deletar uma Disciplina");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o Nome da Disciplina: ");
        String nome = scanner.next(); // le a prox string até achar enter ou espaço

        System.out.print("Semestre: ");
        Integer semestre = scanner.nextInt();

        System.out.println("Professor Id: ");
        Long professorId = scanner.nextLong();

        Optional<Professor> optional = professorRepository.findById(professorId);
        if(optional.isPresent()) {

            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo com sucesso!\n");

        } else {
            System.out.println("Professor ID " + professorId + " é inválido!\n");
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID da Disciplina a ser atualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if(optionalDisciplina.isPresent()) {

            Disciplina disciplina = optionalDisciplina.get();

            System.out.print("Digite o Nome da disciplina: ");
            String nome = scanner.next(); // le a prox string até achar enter ou espaço

            System.out.print("Digite o Semestre: ");
            Integer semestre = scanner.nextInt();

            System.out.println("Professor Id: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);

            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado com sucesso!\n");
            } else {
                System.out.println("Professor ID " + professorId + " inválido");
            }


        } else {
            System.out.println("O ID da disciplina informada: " + id + " é inválida!\n");
        }
    }

    private void visualizar() {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        disciplinas.forEach(disciplina -> {
            System.out.println(disciplina);
        });
        System.out.println();
    }
    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID da disciplina a ser deletada: ");
        Long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id);
        System.out.println("Disciplina deletada com sucesso!");
    }
}
