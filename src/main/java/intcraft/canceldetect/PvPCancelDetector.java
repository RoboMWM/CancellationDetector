package intcraft.canceldetect;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import intcraft.canceldetect.CancellationDetector.CancelListener;

public class PvPCancelDetector extends JavaPlugin implements Listener
{
    private CancellationDetector<PlayerCommandPreprocessEvent> detector = new CancellationDetector<PlayerCommandPreprocessEvent>(PlayerCommandPreprocessEvent.class);
    //private CancellationDetector<ItemSpawnEvent> ItemSpawndetector = new CancellationDetector<ItemSpawnEvent>(ItemSpawnEvent.class);

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);

        detector.addListener(new CancelListener<PlayerCommandPreprocessEvent>()
        {
            @Override
            public void onCancelled(Plugin plugin, PlayerCommandPreprocessEvent event)
            {
                System.out.println("Chat cancelled by " + plugin);
            }
        });

        /*
        ItemSpawndetector.addListener(new CancelListener<ItemSpawnEvent>()
        {
            @Override
            public void onCancelled(Plugin plugin, ItemSpawnEvent event)
            {
                System.out.println("ItemSpawn cancelled by " + plugin);
            }
        });
        */
    }

    @Override
    public void onDisable()
    {
        // Incredibly important!
        detector.close();
    }

    // For testing
    /*@EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e)
    {
        e.setCancelled(true);
    }
    */
}
