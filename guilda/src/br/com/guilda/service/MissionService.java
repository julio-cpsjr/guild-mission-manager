package br.com.guilda.service;

import br.com.guilda.model.MemberGuilda;
import br.com.guilda.model.Mission;
import br.com.guilda.model.StatusMember;
import br.com.guilda.model.StatusMission;
import br.com.guilda.repository.MemberGuildaRepository;
import br.com.guilda.repository.MissionRepository;

import java.time.LocalDate;
import java.util.Objects;

public class MissionService {

    private final MissionRepository missaoRepository;
    private final MemberGuildaRepository membroRepository;

    public MissionService() {
        this.missaoRepository = new MissionRepository();
        this.membroRepository = new MemberGuildaRepository();
    }

    public void iniciarMissao(int missaoId, int membroId) {

        Mission missao = missaoRepository.buscarPorId(missaoId);
        MemberGuilda membro = membroRepository.buscarPorId(membroId);
        String aberta = StatusMission.ABERTA.toString();

        if (!Objects.equals(missao.getStatus(), aberta)) {
            throw new IllegalStateException("Missão não está aberta");
        }

        if (membro.getStatus() != StatusMember.ATIVO) {
            throw new IllegalStateException("Membro inativo");
        }

        missaoRepository.atribuirMembro(missaoId, membroId);
        missaoRepository.atualizarStatus(missaoId, StatusMission.EM_ANDAMENTO);
    }

    public void concluirMissao(int missaoId) {

        Mission missao = missaoRepository.buscarPorId(missaoId);
        String em_andamento = StatusMission.EM_ANDAMENTO.toString();

        if (!Objects.equals(missao.getStatus(), em_andamento)) {
            throw new IllegalStateException("Missão não pode ser concluída");
        }

        missaoRepository.finalizar(
                missaoId,
                StatusMission.CONCLUIDA,
                LocalDate.now()
        );
    }
}
