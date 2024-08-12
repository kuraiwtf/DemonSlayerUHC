package eu.unchat.uhc.demonslayer.role.defaults.slayer;

import eu.unchat.uhc.demonslayer.team.defaults.SlayerTeam;
import eu.unchat.uhc.role.AbstractRole;
import eu.unchat.uhc.role.Role;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@Role(name = "Mitsuri", identifier = "mitsuri", team = SlayerTeam.class, material = Material.RED_ROSE)
public final class MitsuriRole extends AbstractRole {
    private final String tolgeeReference;

    public MitsuriRole() {
        this.tolgeeReference = "fr.unchat.demonslayer.role.mitsuri";
    }
}
