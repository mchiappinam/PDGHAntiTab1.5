package me.mchiappinam.pdghantitab;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
  ProtocolManager protocolManager;

public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    this.protocolManager = ProtocolLibrary.getProtocolManager();
    this.protocolManager.addPacketListener(new PacketAdapter(this, ConnectionSide.CLIENT_SIDE, ListenerPriority.NORMAL, new Integer[] { Integer.valueOf(203) })
    {
      public void onPacketReceiving(PacketEvent event)
      {
        if (event.getPacketID() == 203)
          try {
            PacketContainer packet = event.getPacket();
            String message = (String)packet.getSpecificModifier(String.class).read(0);
            if ((message.startsWith("/")) && (!message.contains(" ")))
              event.setCancelled(true);
          }
          catch (FieldAccessException e) {
            Main.this.getLogger().log(Level.SEVERE, "Couldn't access field.", e);
          }
      }
    });
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2ativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2Acesse: http://pdgh.net/");
  }

@Override
public void onDisable() {
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2desativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2Acesse: http://pdgh.net/");
}

}