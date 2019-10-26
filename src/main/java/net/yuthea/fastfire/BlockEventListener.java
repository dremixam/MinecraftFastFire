package main.java.net.yuthea.fastfire;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;

class BlockEventListener implements Listener {

    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.FIRE) {
            FireBlockManager.AddFire(e.getBlock());
        }
    }

    @EventHandler
    public void OnBlockSpread(BlockSpreadEvent e) {
        if (e.getSource().getType() == Material.FIRE) {
            FireBlockManager.AddFire(e.getBlock());
        }
    }


    @EventHandler
    public void OnBlockBurn(BlockBurnEvent e) {
        if (FireBlockManager.BurntBlock(e.getBlock())) {
            e.setCancelled(true);
        }
    }
}
