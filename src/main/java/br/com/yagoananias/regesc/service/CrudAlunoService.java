package br.com.yagoananias.regesc.service;

import br.com.yagoananias.regesc.orm.Aluno;
import br.com.yagoananias.regesc.orm.Disciplina;
import br.com.yagoananias.regesc.orm.Professor;
import br.com.yagoananias.regesc.repository.IAlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudAlunoService {
    private IAlunoRepository alunoRepository;

    public CrudAlunoService(IAlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação quer executar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar novo aluno");
            System.out.println("2 - Atualizar um aluno");
            System.out.println("3 - Visualizar Alunos");
            System.out.println("4 - Deletar um aluno");
            System.out.println("5 - Visualizar um aluno");

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
                case 5:
                    this.visualizarAluno(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    @Transactional
    private void visualizarAluno(Scanner scanner) {
        System.out.println("Digite o ID do Aluno a ser visualizado: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();

            System.out.println("-ID: " + aluno.getId());
            System.out.println("-Nome: " + aluno.getNome());
            System.out.println("-Idade: " + aluno.getIdade());
            System.out.println("-Disciplinas: [:");

            if (aluno.getDisciplinas() != null) {
                for (Disciplina disciplina : aluno.getDisciplinas()) {
                    System.out.println("\t - Disciplinas: " + disciplina.getNome());
                    System.out.println("\t - Semestre: " + disciplina.getSemestre());
                    System.out.println("");
                }
            }
            System.out.println("]");
        } else {
            System.out.println("O id do aluno informado: " + id + " é inválido!\n");
        }
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o Nome do aluno");
        String nome = scanner.next();

        System.out.println("Idade: ");
        Integer idade = scanner.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno cadastrado com sucesso!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);

        if (optional.isPresent()) {

            Aluno aluno = optional.get();

            System.out.println("Nome: ");
            String nome = scanner.next();

            System.out.println("Idade: ");
            Integer idade = scanner.nextInt();

            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
            System.out.println("Aluno atualizado!\n");
        } else {
            System.out.println("O id do aluno informado: " + id + " é inválido!\n");
        }
    }

    private void visualizar() {
        Iterable<Aluno> alunos = this.alunoRepository.findAll();
        alunos.forEach(aluno -> {
            System.out.println(aluno);
        });
        System.out.println();
    }
    private void deletar(Scanner scanner) {
        System.out.print("ID: ");
        Long id = scanner.nextLong();
        this.alunoRepository.deleteById(id);
        System.out.println("Aluno deletado com sucesso!\n");
    }
}
