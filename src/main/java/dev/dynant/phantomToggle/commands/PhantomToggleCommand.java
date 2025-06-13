/* Licensed under GNU General Public License v3.0 */
package dev.dynant.phantomToggle.commands;

import dev.dynant.phantomToggle.PhantomToggle;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.processing.CommandContainer;

@SuppressWarnings("unused")
@CommandContainer
@CommandDescription("Toggle phantom spawning")
public class PhantomToggleCommand {
  private static final MiniMessage mm = MiniMessage.miniMessage();

  @Command("phantomtoggle")
  public void phantomToggle(CommandSourceStack sourceStack) {
    if (!(sourceStack.getSender() instanceof Player player)) return;

    PersistentDataContainer container = player.getPersistentDataContainer();

    boolean isDisabled = PhantomToggle.isPhantomDisabled(player);
    container.set(PhantomToggle.PHANTOM_DISABLED_KEY, PersistentDataType.BOOLEAN, !isDisabled);

    Component message =
        mm.deserialize(
            isDisabled
                ? "<green>Phantom spawning is now <bold>enabled</bold> for you. Don't forget to sleep!"
                : "<red>Phantom spawning is now <bold>disabled</bold> for you. No more phantom visits.");
    player.sendMessage(message);
  }

  @Command("phantomtoggle status")
  public void phantomToggleStatus(CommandSourceStack sourceStack) {
    if (!(sourceStack.getSender() instanceof Player player)) return;

    boolean isDisabled = PhantomToggle.isPhantomDisabled(player);

    Component message =
        mm.deserialize(
            isDisabled
                ? "<red>Phantom spawning is currently <bold>disabled</bold> for you. Enjoy the peace and quiet!"
                : "<green>Phantom spawning is currently <bold>enabled</bold> for you. Remember to sleep!");
    player.sendMessage(message);
  }
}
