package br.com.yagoananias.regesc.service;

import br.com.yagoananias.regesc.orm.Professor;
import br.com.yagoananias.regesc.repository.IProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudProfessorService {
    private IProfessorRepository professorRepository; //dependencia da classe CrudProfessorService

    //Spring cria aut um obj com a interface IProfessorRepository
    // e injeta no construtor da classe atual ==> Injeção de dependencia
    public CrudProfessorService(IProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação quer executar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizar um Professor");

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
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o Nome do professor: ");
        String nome = scanner.next(); // le a prox string até achar enter ou espaço

        System.out.print("Digite o Prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo com sucesso!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        //se o hibernate achar um registro na tabela de prof com o id passado
        if(optional.isPresent()) {

            System.out.print("Digite o Nome do professor: ");
            String nome = scanner.next(); // le a prox string até achar enter ou espaço

            System.out.print("Digite o Prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            //atualiza o objeto no banco
            professorRepository.save(professor);
            System.out.println("Professor atualizado com sucesso!\n");


        } else {
            System.out.println("O ID informado: " + id + "é inválido!\n");
        }
    }

    private void visualizar() {
        Iterable<Professor> professores = this.professorRepository.findAll();
        professores.forEach(professor -> {
            System.out.println(professor);
        });
        System.out.println();
    }
}
