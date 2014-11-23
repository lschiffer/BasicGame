/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Actor {
    private String name;
    private GameManager game;
    
    public Actor(String actorName, GameManager parentGame) {
        this.name = actorName;
        this.game = parentGame;
    }
    
    public void init() {
        Box box = new Box(0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry(name, box);
        Material mat = new Material(game.getApp().getAssetManager(), "Common/MatDefs/Misc/ColoredTextured.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        geom.setMaterial(mat);
        geom.setShadowMode(RenderQueue.ShadowMode.Cast);
        geom.setLocalTranslation(-2.0f, 10.0f, 0);
        
        game.getApp().getRootNode().attachChild(geom);
        
        //RigidBodyControl body = game.getWorldManager().addMovable(geom);
    }
}
