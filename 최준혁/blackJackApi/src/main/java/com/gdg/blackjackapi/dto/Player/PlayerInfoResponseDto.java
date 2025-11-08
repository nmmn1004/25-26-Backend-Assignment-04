package com.gdg.blackjackapi.dto.Player;

import com.gdg.blackjackapi.domain.Player;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Builder
@Getter
public class PlayerInfoResponseDto {
    private Long id;
    private String name;
    private Long record;
    private LocalDate date;

    public static PlayerInfoResponseDto from(Player player) {
        return PlayerInfoResponseDto.builder()
                .id(player.getId())
                .name(player.getName())
                .record(player.getRecord())
                .date(player.getDate())
                .build();
    }
}
