package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.role.defaults.demon.MuzanRole;
import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.profile.IProfile;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import eu.unchat.uhc.util.CC;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
@Role(name = "Kagaya", identifier = "kagaya", team = SlayerTeam.class, material = Material.CHEST)
public final class KagayaRole extends AbstractRole {
    private final String tolgeeReference;

    public KagayaRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.kagaya";
        registerKnownRole(KiriyaRole.class);
    }

    @Override
    public void onDistribute(Player player) {
        IProfile profile = IProfile.of(player.getUniqueId());
        profile.setWeaknessBuffer(profile.getWeaknessBuffer() + 15);
    }

    @Override
    public void onDeath(Player player, Player killer) {
        if (!AbstractRole.isRole(IProfile.of(player.getUniqueId()), MuzanRole.class)) {
            return;
        }

        IProfile profile = AbstractRole.findPlayer(getClass());
        if (profile.getState().equals(IProfile.State.SPECTATING)) {
            return;
        }

        profile.setWeaknessBuffer(profile.getWeaknessBuffer() - 15);
        profile.sendMessage(CC.info("&cMuzan &bvient de mourir, vous perdez donc votre effet de &8Faiblesse&b."));
    }
}
