package main.java.net.yuthea.fastfire;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Random;

class FireBlockManager {
    private static final Random r = new Random();
    private static final ArrayList<FireBlock> fireBlocks = new ArrayList<>();


    static void AddFire(Block bl) {
        if (getFireBlock(bl) == null) {
            fireBlocks.add(new FireBlock(bl));
        }
    }

    public static void RemoveFire(Block bl) {
        FireBlock fb = getFireBlock(bl);
        RemoveFire(fb);
    }

    public static void RemoveFire(FireBlock fb) {
        if (fb != null) {
            fb.close();
            fireBlocks.remove(fb);
        }
    }

    private static FireBlock getFireBlock(Block bl) {
        for (FireBlock fb : fireBlocks) {
            if (fb.getBlock().getLocation().equals(bl.getLocation())) {
                return fb;
            }
        }
        return null;
    }

    public static boolean BurntBlock(Block block) {
        if (isWoodLog(block)) {
            if (r.nextInt(2) < 1) {
                block.setType(Material.COAL_BLOCK);
                return true;
            }

        }
        return false;

    }

    private static boolean isWoodLog(Block block) {
        ArrayList<Material> woodLogs = new ArrayList<>();
        woodLogs.add(Material.ACACIA_LOG);
        woodLogs.add(Material.BIRCH_LOG);
        woodLogs.add(Material.DARK_OAK_LOG);
        woodLogs.add(Material.JUNGLE_LOG);
        woodLogs.add(Material.OAK_LOG);
        woodLogs.add(Material.SPRUCE_LOG);
        woodLogs.add(Material.STRIPPED_ACACIA_LOG);
        woodLogs.add(Material.STRIPPED_BIRCH_LOG);
        woodLogs.add(Material.STRIPPED_DARK_OAK_LOG);
        woodLogs.add(Material.STRIPPED_JUNGLE_LOG);
        woodLogs.add(Material.STRIPPED_OAK_LOG);
        woodLogs.add(Material.STRIPPED_SPRUCE_LOG);
        woodLogs.add(Material.ACACIA_WOOD);
        woodLogs.add(Material.BIRCH_WOOD);
        woodLogs.add(Material.DARK_OAK_WOOD);
        woodLogs.add(Material.JUNGLE_WOOD);
        woodLogs.add(Material.OAK_WOOD);
        woodLogs.add(Material.SPRUCE_WOOD);
        return woodLogs.contains(block.getType());
    }
}
