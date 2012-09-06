package to.us.diamondcraft;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DiamondCREEPAH extends JavaPlugin 
implements Listener
{
private HashMap<Class<? extends Entity>, List<String>> EWs = new HashMap();

public void onEnable() { if (getConfig().options().header() == null) {
	getLogger().info("DiamondCREEAPH enabled! Non-player entities can no longer modify blocks!");
	getConfig().options().copyHeader();
    getConfig().options().copyDefaults(true);
    saveConfig();
  }

  this.EWs.put(Enderman.class, getConfig().getStringList("Endermen"));
  this.EWs.put(Sheep.class, getConfig().getStringList("Sheep"));
  this.EWs.put(Creeper.class, getConfig().getStringList("MobExplosion"));
  this.EWs.put(TNTPrimed.class, getConfig().getStringList("TNT"));
  this.EWs.put(Snowman.class, getConfig().getStringList("Snowman"));
  this.EWs.put(Silverfish.class, getConfig().getStringList("Silverfish"));
  getServer().getPluginManager().registerEvents(this, this); } 
@EventHandler
public void onBlockChange(EntityChangeBlockEvent e) {
  if (((e.getEntity() instanceof Enderman)) && ((this.EWs == null) || (!this.EWs.containsKey(Enderman.class)) || (!((List)this.EWs.get(Enderman.class)).contains(e.getBlock().getLocation().getWorld().getName())))) e.setCancelled(true);
  if (((e.getEntity() instanceof Silverfish)) && ((this.EWs == null) || (!this.EWs.containsKey(Silverfish.class)) || (!((List)this.EWs.get(Silverfish.class)).contains(e.getBlock().getLocation().getWorld().getName())))) e.setCancelled(true);
  if (((e.getEntity() instanceof Sheep)) && ((this.EWs == null) || (!this.EWs.containsKey(Sheep.class)) || (!((List)this.EWs.get(Sheep.class)).contains(e.getBlock().getLocation().getWorld().getName())))) {
    e.setCancelled(true);
    ((Sheep)e.getEntity()).setSheared(false);
  }
}
@EventHandler
public void onExplosion(EntityExplodeEvent e) { if (((e.getEntity() instanceof TNTPrimed)) && ((this.EWs == null) || (!this.EWs.containsKey(TNTPrimed.class)) || (!((List)this.EWs.get(TNTPrimed.class)).contains(e.getLocation().getWorld().getName())))) e.blockList().clear();
  else if ((this.EWs == null) || (!this.EWs.containsKey(Creeper.class)) || (!((List)this.EWs.get(Creeper.class)).contains(e.getLocation().getWorld().getName()))) e.blockList().clear();  } 
@EventHandler
public void onEntityForm(EntityBlockFormEvent e) {
  if ((this.EWs == null) || (!this.EWs.containsKey(Snowman.class)) || (!((List)this.EWs.get(Snowman.class)).contains(e.getBlock().getLocation().getWorld().getName()))) e.setCancelled(true);
}


}
