package eu.unchat.uhc.demonslayer.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import eu.unchat.uhc.API;
import eu.unchat.uhc.cycle.Cycle;
import eu.unchat.uhc.demonslayer.command.validator.role.HasRole;
import eu.unchat.uhc.demonslayer.command.validator.team.HasTeam;
import eu.unchat.uhc.demonslayer.power.slayer.tanjiro.SmellPower;
import eu.unchat.uhc.demonslayer.role.defaults.demon.MuzanRole;
import eu.unchat.uhc.demonslayer.role.defaults.slayer.TanjiroRole;
import eu.unchat.uhc.demonslayer.team.defaults.DemonTeam;
import eu.unchat.uhc.power.AbstractPower;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.util.CC;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

@Command(name = "demonslayer", aliases = {"ds"})
public final class DemonSlayerCommand {

    @Execute(name = "effect")
    void executeEffect(final @Context Player player) {
        player.performCommand("effect");
    }

    @Execute(name = "role")
    void executeRole(final @Context Player player) {
        final IProfile profile = IProfile.of(player.getUniqueId());
        if (profile.getRole() == null) {
            player.sendMessage(CC.error("Vous n'avez pas de rôle"));
            return;
        }

        player.sendMessage(CC.center("&3&lDEMON SLAYER UHC"));
        Audience audience = API.get().getAdventure().player(player);
        audience.sendMessage(MiniMessage.miniMessage().deserialize(profile.getRole().getDescription(player)));
    }

    @Execute(name = "flair")
    void executeFlair(final @Context @HasRole(TanjiroRole.class) Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        AbstractRole role = profile.getRole();
        AbstractPower power = role.getPower(SmellPower.class);

        if (API.get().getCycleHandler().getCurrentCycle() != Cycle.NIGHT) {
            player.sendMessage(CC.translate("&cCe pouvoir n'est utilisable que la nuit."));
            return;
        }

        player.sendMessage(CC.translate("&cTest !"));
    }

    @Execute(name = "blood")
    void executeBloodGift(
            final @Context @HasRole(MuzanRole.class) Player player,
            @Arg("target") @HasTeam(DemonTeam.class) IProfile target) {

        IProfile profile = IProfile.of(player.getUniqueId());
        AbstractRole role = profile.getRole();
        AbstractPower power = role.getPower(MuzanRole.BloodGiftPower.class);

    }

}
