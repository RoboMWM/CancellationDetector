package intcraft.canceldetect;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import intcraft.canceldetect.CancellationDetector.CancelListener;

public class PvPCancelDetector extends JavaPlugin implements Listener
{
    private CancellationDetector<EntityDamageByBlockEvent> detector = new CancellationDetector<EntityDamageByBlockEvent>(EntityDamageByBlockEvent.class);

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);

        detector.addListener(new CancelListener<EntityDamageByBlockEvent>()
        {
            @Override
            public void onCancelled(Plugin plugin, EntityDamageByBlockEvent event)
            {
                System.out.println("cancelled by " + plugin);
            }
        });
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
