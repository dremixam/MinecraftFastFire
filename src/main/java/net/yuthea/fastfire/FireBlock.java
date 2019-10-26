package main.java.net.yuthea.fastfire;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

class FireBlock {
    private static final Random r = new Random();
    private static final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private final Block block;
    private final int taskId;

    public FireBlock(Block block) {
        this.block = block;

        taskId = scheduler.scheduleSyncRepeatingTask(FastFire.getInstance(), () -> {
            if (!this.block.getLocation().getChunk().isLoaded()) return;
            if (this.block.getType() != Material.FIRE) {
                FireBlockManager.RemoveFire(this);
            }
            if (r.nextInt(80) * this.block.getHumidity() < 1) {
                spread();
            }
        }, 1L, 1L);
    }

    public Block getBlock() {
        return block;
    }

    public void close() {
        scheduler.cancelTask(taskId);
    }

    private void spread() {
        Block target = findRandomTarget(block.getLocation());
        if (target == null) return;
        Block fireblock;

        if (target.getLocation().getY() > block.getLocation().getY() && target.getRelative(BlockFace.DOWN).getType() == Material.AIR) {
            fireblock = target.getRelative(BlockFace.DOWN);
        } else if (target.getLocation().getY() < block.getLocation().getY() && target.getRelative(BlockFace.UP).getType() == Material.AIR) {
            fireblock = target.getRelative(BlockFace.UP);
        } else {
            switch (r.nextInt(4)) {
                case 0:
                    fireblock = target.getRelative(BlockFace.NORTH);
                    if (fireblock.getType() == Material.AIR)
                        break;
                case 1:
                    fireblock = target.getRelative(BlockFace.SOUTH);
                    if (fireblock.getType() == Material.AIR)
                        break;
                case 2:
                    fireblock = target.getRelative(BlockFace.EAST);
                    if (fireblock.getType() == Material.AIR)
                        break;
                case 3:
                    fireblock = target.getRelative(BlockFace.WEST);
                    if (fireblock.getType() == Material.AIR)
                        break;
                default:
                    fireblock = null;
            }
        }

        if (fireblock != null) {
            fireblock.setType(Material.FIRE);
            Bukkit.getServer().getPluginManager().callEvent(new BlockSpreadEvent(target, fireblock, block.getState()));
        }
    }

    private Block findRandomTarget(Location loc) {
        Block target;
        int i = 0;
        do {
            target = loc.clone().add(r.nextInt(5) - 2, r.nextInt(5) - 1, r.nextInt(5) - 2).getBlock();
            i++;
        } while (i < 10 && !(target.getType().isFlammable() || target.getType() == Material.COAL_ORE));

        return target;
    }


}
