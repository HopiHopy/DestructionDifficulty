package com.movtery.difficultyd;

import com.movtery.difficultyd.Config.ModAutoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class ShowRandomNumber {
    static ModAutoConfig config = AutoConfig.getConfigHolder(ModAutoConfig.class).getConfig();

    public static void RandomNumber(PlayerEntity player) {
        player.sendMessage(Text.literal("===================================").formatted(Formatting.YELLOW));
        if (!config.randomDropChance &&
                !(config.foodLevel && config.randomFoodLevelChance) &&
                !(config.exhaustionLevel && config.randomExhaustionLevelChance)) {
            Text message = Text.translatable("text.difficultyd.randomnumbers.nothing").formatted(Formatting.RED);
            player.sendMessage(message);
        }
        if (config.randomDropChance) {
            String judgment = DifficultyD.getDropJudgment();
            Text message;
            if (!Objects.equals(judgment, " ? ")) {
                message = Text.translatable("text.difficultyd.randomnumbers.drop")
                        .append(" ")
                        .append(String.valueOf(DifficultyD.getDropChance()))
                        .append(judgment)
                        .append(String.valueOf(DifficultyD.getDifferentToolDropChance()))
                        .append(" ")
                        .append(yesOrNo(judgment));
            } else {
                message = Text.translatable("text.difficultyd.randomnumbers.drop")
                        .append(Text.translatable("text.difficultyd.randomnumbers.yes"));
            }
            player.sendMessage(message);
        }
        if (config.foodLevel && config.randomFoodLevelChance) {
            String judgment = BreakHunger.getFoodJudgment();
            Text message = Text.translatable("text.difficultyd.randomnumbers.food")
                    .append(" ")
                    .append(String.valueOf(BreakHunger.getFoodChance()))
                    .append(judgment)
                    .append(String.valueOf(config.foodLevelChance))
                    .append(" ")
                    .append(yesOrNo(judgment));
            player.sendMessage(message);
        }
        if (config.exhaustionLevel && config.randomExhaustionLevelChance) {
            String judgment = BreakHunger.getExhaustionJudgment();
            Text message = Text.translatable("text.difficultyd.randomnumbers.exhaustion")
                    .append(" ")
                    .append(String.valueOf(BreakHunger.getExhaustionChance()))
                    .append(judgment)
                    .append(String.valueOf(config.exhaustionLevelChance))
                    .append(" ")
                    .append(yesOrNo(judgment));
            player.sendMessage(message);
        }
        player.sendMessage(Text.literal("===================================").formatted(Formatting.YELLOW));
    }

    private static Text yesOrNo(String judgment) {
        Text yesOrNo = Text.translatable("text.difficultyd.randomnumbers.no");
        return switch (judgment) {
            case " > " -> {
                yesOrNo = Text.translatable("text.difficultyd.randomnumbers.no");
                yield yesOrNo;
            }
            case " < " -> {
                yesOrNo = Text.translatable("text.difficultyd.randomnumbers.yes");
                yield yesOrNo;
            }
            default -> yesOrNo;
        };
    }
}
