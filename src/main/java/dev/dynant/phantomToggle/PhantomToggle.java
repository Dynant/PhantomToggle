/* Licensed under GNU General Public License v3.0 */
package dev.dynant.phantomToggle;

import dev.dynant.phantomToggle.events.PhantomSpawn;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

public final class PhantomToggle extends JavaPlugin {
  public static PhantomToggle pluginInstance;
  public static NamespacedKey PHANTOM_DISABLED_KEY;

  @Override
  public void onEnable() {
    pluginInstance = this;
    PHANTOM_DISABLED_KEY = new NamespacedKey(this, "phantoms_disabled");

    try {
      PaperCommandManager<CommandSourceStack> commandManager =
          PaperCommandManager.builder()
              .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
              .buildOnEnable(this);

      AnnotationParser<CommandSourceStack> annotationParser =
          new AnnotationParser(commandManager, CommandSourceStack.class);

      annotationParser.parseContainers();

    } catch (Exception exc) {
      getLogger().warning("Failed to parse command containers. Commands will not work!");
      exc.printStackTrace();
    }

    // Register events
    getServer().getPluginManager().registerEvents(new PhantomSpawn(), this);

    getLogger().info("PhantomToggle Enabled!");
  }

  @Override
  public void onDisable() {
    getLogger().info("PhantomToggle Disabled!");
  }

  public static boolean isPhantomDisabled(Player player) {
    return player
        .getPersistentDataContainer()
        .getOrDefault(PHANTOM_DISABLED_KEY, PersistentDataType.BOOLEAN, false);
  }
}
