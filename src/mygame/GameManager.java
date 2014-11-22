/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
    
    GameManager(Main mainApp) {
        this.app = mainApp;
        worldManager = new WorldManager(app.getStateManager());
    }
    
    public Main getApp() { return this.app; }
    
    public void init() {
        screenManager = new ScreensManager(this);
        screenManager.init();
        
        initWorld();
    }
    
    public void initWorld() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        app.getRootNode().attachChild(geom);
    }
}
