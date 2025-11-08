package com.gdg.blackjackapi.dto.Game;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class GameSaveRequestDto {
    @NotNull(message = "플레이어 ID는 필수 입력입니다.")
    private Long playerId;
}
