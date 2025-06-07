package com.app.tennis.service;

import com.app.tennis.Player;
import com.app.tennis.PlayerToSave;
import com.app.tennis.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingCalculator {

    private final List<Player> currentPlayersRanking;
    private final PlayerToSave PlayerToSave;

    public RankingCalculator(List<Player> currentPlayersRanking, PlayerToSave PlayerToSave) {
        this.currentPlayersRanking = currentPlayersRanking;
        this.PlayerToSave = PlayerToSave;
    }

    public List<Player> getNewPlayersRanking() {
        List<Player> newRankingList = new ArrayList<>(currentPlayersRanking);
        newRankingList.add(new Player(
                PlayerToSave.firstName(),
                PlayerToSave.lastName(),
                PlayerToSave.birthDate(),
                new Rank(999999999, PlayerToSave.points())
        ));

        newRankingList.sort((player1, player2) -> Integer.compare(player2.rank().points(), player1.rank().points()));

        List<Player> updatedPlayers = new ArrayList<>();

        for (int i = 0; i < newRankingList.size(); i++) {
            Player player = newRankingList.get(i);
            Player updatedPlayer = new Player(
                    player.firstName(),
                    player.lastName(),
                    player.birthDate(),
                    new Rank(i+1, player.rank().points())
            );
            updatedPlayers.add(updatedPlayer);
        }
        return updatedPlayers;
    }
}
