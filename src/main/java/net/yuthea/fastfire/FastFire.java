package main.java.net.yuthea.fastfire;

import org.bukkit.plugin.java.JavaPlugin;

class FastFire extends JavaPlugin {

    private static JavaPlugin Instance;

    public static JavaPlugin getInstance() {
        return Instance;
    }

    @Override
    public void onLoad() {
        Instance = this;
    }

    @Override
    public void onEnable() {
        BlockEventListener blockEventListener = new BlockEventListener();
        getServer().getPluginManager().registerEvents(blockEventListener, this);
    }
}
