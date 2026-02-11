package br.com.guilda.ui;

import br.com.guilda.model.*;
import br.com.guilda.repository.*;

import java.util.Scanner;

public class MenuConsole {

    private final Scanner scanner = new Scanner(System.in);
    private final GuildaRepository guildaRepo = new GuildaRepository();
    private final MemberGuildaRepository membroRepo = new MemberGuildaRepository();
    private final MissionRepository missaoRepo = new MissionRepository();

    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> criarGuilda();
                case 2 -> criarMembro();
                case 3 -> criarMissao();
                case 4 -> listarGuildas();
                case 5 -> listarMissoes();
                case 6 -> listarMembros();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("""
            === SISTEMA DA GUILDA ===
            1 - Criar Guilda
            2 - Criar Membro
            3 - Criar Missão
            4 - Listar Guildas
            5 - Listar Missoes
            6 - Listar Membros
            0 - Sair
        """);
    }

    /* =====================
       Ações
       ===================== */

    private void criarGuilda() {
        System.out.print("Nome da guilda: ");
        String nome = scanner.nextLine();

        Guilda guilda = new Guilda(nome);
        guildaRepo.salvar(guilda);

        System.out.println("Guilda criada com sucesso!");
    }

    private void criarMembro() {
        System.out.print("Nome do membro: ");
        String nome = scanner.nextLine();

        System.out.print("Classe (ESPADACHIM, MAGO, SUPORTE): ");
        ClassMember classe = ClassMember.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Nível: ");
        int nivel = Integer.parseInt(scanner.nextLine());

        System.out.print("ID da Guilda: ");
        int guildaId = Integer.parseInt(scanner.nextLine());

        MemberGuilda membro = new MemberGuilda(
                nome,
                classe,
                nivel,
                guildaId,
                StatusMember.ATIVO
        );

        membroRepo.salvar(membro);
        System.out.println("Membro criado!");
    }

    private void criarMissao() {
        System.out.print("Nome da missão: ");
        String nomeMissao = scanner.nextLine();

        System.out.print("Dificuldade (D, C, B, A, S): ");
        DificultMission dificuldade = DificultMission.valueOf(scanner.nextLine().toUpperCase());

        Mission missao = new Mission(
                nomeMissao,
                dificuldade,
                StatusMission.ABERTA
        );

        missaoRepo.salvar(missao);
        System.out.println("Missão registrada!");
    }

    private void listarGuildas() {
        guildaRepo.listarTodas()
                .forEach(g -> System.out.println(
                        g.getId() + " - " + g.getNome() + " - " + g.getNivel() + " - " + g.getReputacao()
                ));
    }
    private void listarMissoes() {
        missaoRepo.listarTodas()
                .forEach(m -> System.out.println(
                        m.getId() + " - " + m.getNome() + " - " + m.getDificuldade() +  " - " + m.getStatus()
                ));
    }
    private void listarMembros() {
        membroRepo.listarTodos()
                .forEach(m -> System.out.println(
                        m.getId() + " - " + m.getNome() + " - " + m.getNivel() + " - " + m.getStatus() + " - " + m.getClasse()
                ));
    }

}
