package me.lightha.lhc.gui.fake.impl;

import me.lightha.lhc.gui.fake.FakeInventory;
import net.minecraft.server.v1_16_R3.ChatMessage;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayOutOpenWindow;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakeInventoryImpl implements FakeInventory {

    @Override
    public void replaceTitle(Player player, String newTitle) {
        EntityPlayer handle = ((CraftPlayer) player).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(
                handle.activeContainer.windowId,
                handle.activeContainer.getType(),
                new ChatMessage(newTitle)
        );
        try {
            handle.playerConnection.sendPacket(packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        handle.updateInventory(handle.activeContainer);
    }
}
