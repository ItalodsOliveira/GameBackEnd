package com.isogames.app.repository;

import com.isogames.app.model.BuyGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyGamesRepository extends JpaRepository<BuyGame, String> {

}
