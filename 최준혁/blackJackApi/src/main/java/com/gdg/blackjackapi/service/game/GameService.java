package com.gdg.blackjackapi.service.game;

import com.gdg.blackjackapi.domain.Game;
import com.gdg.blackjackapi.domain.Player.Player;
import com.gdg.blackjackapi.dto.Game.GameInfoResponseDto;
import com.gdg.blackjackapi.dto.Game.GameSaveRequestDto;
import com.gdg.blackjackapi.repository.GameRepository;
import com.gdg.blackjackapi.service.player.PlayerFinder;
import com.gdg.blackjackapi.service.player.PlayerService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameCreator gameCreator;
    private final GameFinder gameFinder;
    private final PlayerFinder playerFinder;
    private final PlayerService playerService;

    @Transactional
    public GameInfoResponseDto saveGame(GameSaveRequestDto gameSaveRequestDto) {
        Player player = playerFinder.findByIdOrThrow(gameSaveRequestDto.getPlayerId());

        return GameInfoResponseDto.from(gameCreator.create(player, gameSaveRequestDto));
    }

    @Transactional(readOnly = true)
    public GameInfoResponseDto getGame(Long gameId) {
        Game game = gameFinder.findByIdOrThrow(gameId);

        return GameInfoResponseDto.from(game);
    }

    @Transactional(readOnly = true)
    public List<GameInfoResponseDto> getAllGame() {
        return gameFinder.findAll()
                .stream()
                .map(GameInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public GameInfoResponseDto getGameResult(Long gameId) {
        Game game = gameFinder.findByIdOrThrow(gameId);

        playerService.updatePlayer(game.getPlayer().getId(), game.getChips());

        return GameInfoResponseDto.from(game);
    }

    @Transactional
    public void deleteGame(Long gameId) {
        gameRepository.deleteById(gameId);
    }

}
