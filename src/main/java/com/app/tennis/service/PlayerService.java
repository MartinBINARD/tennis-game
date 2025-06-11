package com.app.tennis.service;

import com.app.tennis.Player;
import com.app.tennis.PlayerList;
import com.app.tennis.PlayerToSave;
import com.app.tennis.Rank;
import com.app.tennis.data.PlayerEntity;
import com.app.tennis.data.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> new Player(
                        player.getFirstName(),
                        player.getLastName(),
                        player.getBirthDate(),
                        new Rank(player.getRank(), player.getPoints())
                ))
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());
    }

    public Player getByLastName(String lastName) {
        Optional<PlayerEntity> player = playerRepository.findOneByLastNameIgnoreCase(lastName);
        if (player.isEmpty()) {
            throw new PlayerNotFoundException(lastName);
        }

        return new Player(
                player.get().getFirstName(),
                player.get().getLastName(),
                player.get().getBirthDate(),
                new Rank(player.get().getRank(), player.get().getPoints())
        );
    }

    public Player create(PlayerToSave playerToSave) {
        Optional<PlayerEntity> player = playerRepository.findOneByLastNameIgnoreCase(playerToSave.lastName());
        if (player.isPresent()) {   
            throw new PlayerAlreadyExistsException(playerToSave.lastName());
        }

        PlayerEntity playerToRegister = new PlayerEntity(
                playerToSave.lastName(),
                playerToSave.firstName(),
                playerToSave.birthDate(),
                playerToSave.points(),
                999999999);

        PlayerEntity registeredPlayer = playerRepository.save(playerToRegister);

        RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
        List<PlayerEntity> newRanking = rankingCalculator.getNewPlayersRanking();
        playerRepository.saveAll(newRanking);

        return getByLastName(registeredPlayer.getLastName());
    }

    public Player update(PlayerToSave playerToSave) {
//        getByLastName(playerToSave.lastName());
//
//        List<Player> playersWithoutPlayerToUpdate = PlayerList.ALL.stream()
//                .filter(player -> !player.lastName().equals(playerToSave.lastName()))
//                .toList();
//
//        RankingCalculator rankingCalculator = new RankingCalculator(playersWithoutPlayerToUpdate, playerToSave);
//        List<Player> players = rankingCalculator.getNewPlayersRanking();
//
//        return getPlayerNewRanking(playersWithoutPlayerToUpdate, playerToSave);
        return null;
    }

    public void delete(String lastName) {
//        Player playerToDelete = getByLastName(lastName);
//
//        PlayerList.ALL.stream()
//                .filter(player -> !player.lastName().equals(lastName))
//                .toList();
//
//        RankingCalculator rankingCalculator = new RankingCalculator(PlayerList.ALL);
//        rankingCalculator.getNewPlayersRanking();
    }
}
