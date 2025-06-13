/* Licensed under GNU General Public License v3.0 */
package dev.dynant.phantomToggle.events;

import com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent;
import dev.dynant.phantomToggle.PhantomToggle;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PhantomSpawn implements Listener {
  @EventHandler
  public void onPhantomPreSpawn(PhantomPreSpawnEvent event) {
    if (!(event.getSpawningEntity() instanceof Player player)) return;

    // Don't spawn phantom for player when player has disabled phantoms
    if (PhantomToggle.isPhantomDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPhantomTarget(EntityTargetLivingEntityEvent event) {
    if (!(event.getEntity() instanceof Phantom)) return;
    if (!(event.getTarget() instanceof Player player)) return;

    // Disable phantom attacks when player has disabled phantoms
    if (PhantomToggle.isPhantomDisabled(player)) {
      event.setCancelled(true);
    }
  }
}
