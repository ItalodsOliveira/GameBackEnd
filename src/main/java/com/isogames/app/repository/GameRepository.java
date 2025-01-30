package com.isogames.app.repository;

import com.isogames.app.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    @Query(value = "Select n from Game n where n.nomeDoJogo like %?1%")
    List<Game> pesquisaPorNome(String nomeDoJogo);

    //@Query(nativeQuery = true, value = "SELECT * FROM public.game where nome_do_jogo like %?1%")
    //List<Game> pesquisaPorNome(String nomeDoJogo);

    @Query(value = "Select n from Game n where n.desenvolvedora like %?1%")
    List<Game> pesquisaPorDev(String desenvolvedora);

    @Query(value = "Select n from Game n where n.distribuidora like %?1%")
    List<Game> pesquisaPorDistribuidora(String distribuidora);

    @Query(value = "Select n from Game n where n.classificacaoIndiacativa like %?1%")
    List<Game> pesquisaPorClasseIndica(String classificacaoIndiacativa);

    @Query(value = "Select n from Game n where n.preco in (?1)")
    List<Game> pesquisaPorPreco(float preco);

    @Query(value = "Select n from Game n where n.preco between ?1 and ?2")
    List<Game> pesquisaPorPrecoEntre(float preco1, float preco2);
}
