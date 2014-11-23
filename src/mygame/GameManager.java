package mygame;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
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
    
    private PlayerControlAppState pControlState = null;
    
    GameManager(Main mainApp) {
        this.app = mainApp;
        this.worldManager = new WorldManager(app);
        this.network = new NetworkManager(this);

        this.pControlState = new PlayerControlAppState(new PlayerController(app.getCamera()));
    }
    
    public Main getApp() { return this.app; }
    public WorldManager getWorldManager() { return this.worldManager; }
    public NetworkManager getNetworkManager() { return this.network; }
    
    public void init() {
        network.init();
        
        screenManager = new ScreensManager(this);
        screenManager.init();  
        screenManager.getMainScreen().showScreen();
        
        worldManager.initWorld();
        
        Box box = new Box(0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry("Box2", box);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/ColoredTextured.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geom.setMaterial(mat);
        geom.setShadowMode(RenderQueue.ShadowMode.Cast);
        geom.setLocalTranslation(2.0f, 10.0f, 0);
        
        //app.getRootNode().attachChild(geom);
        
        RigidBodyControl body = worldManager.addMovable(geom);
        
        setPlayerControl(null);
    }
    
    /*
     * set to null if no actor should be controlled
     */
    public void setPlayerControl(Actor controlledActor) {
        if (controlledActor == null)
            app.getStateManager().attach(pControlState);
        else
            app.getStateManager().detach(pControlState);
    }
    
    public void setPlayerView(Actor viewedActor) {
        
    }

}
