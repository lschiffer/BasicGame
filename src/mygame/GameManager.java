package mygame;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.network.Client;
import com.jme3.network.Server;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author MasterM
 */
public class GameManager {
    private Main app = null;
    private ScreensManager screenManager = null;
    private WorldManager worldManager = null;
    private NetworkManager network = null;
    
    GameManager(Main mainApp) {
        this.app = mainApp;
        worldManager = new WorldManager(app);
        this.network = new NetworkManager(this);

    }
    
    public Main getApp() { return this.app; }
    public NetworkManager getNetworkManager() { return this.network; }
    
    public void init() {
        network.init();
        
        screenManager = new ScreensManager(this);
        screenManager.init();  
        screenManager.getMainScreen().showScreen();
        
        worldManager.initWorld();
    }
    
    public void startServer()
    {
        
    }

}
