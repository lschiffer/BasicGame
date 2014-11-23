package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.BasicShadowRenderer;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PointLightShadowRenderer;

/**
 *
 * @author lschiffer
 */
public class WorldManager {
    
    private BulletAppState bulletAppState;
    private SimpleApplication app;
    
    public WorldManager(SimpleApplication app) {
        
        this.app = app;
        bulletAppState = new BulletAppState();  
        app.getStateManager().attach(bulletAppState);
    }
    
    public void initWorld() {
        
        Box groundBox = new Box(10, 1, 10);
        Geometry geometry = new Geometry("Ground", groundBox);
        Material material = new Material(app.getAssetManager(), "Common/MatDefs/Misc/ColoredTextured.j3md");
        material.setColor("Color", ColorRGBA.DarkGray);
        geometry.setMaterial(material);
        geometry.move(0, -2.5f, 0);
        geometry.setShadowMode(RenderQueue.ShadowMode.Receive);
        addImmovable(geometry);
        
        Box box = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", box);
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/ColoredTextured.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        geom.setShadowMode(RenderQueue.ShadowMode.Cast);
        addImmovable(geom);
               
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        app.getRootNode().addLight(al);
        
        PointLight light = new PointLight();
        light.setColor(ColorRGBA.Orange);
        light.setRadius(15);
        light.setPosition(new Vector3f(0, 10, 0));
        
        PointLightShadowRenderer shadowRenderer1 = new PointLightShadowRenderer(app.getAssetManager(), 512);
        shadowRenderer1.setLight(light);
        app.getViewPort().addProcessor(shadowRenderer1);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        sun.setColor(ColorRGBA.Yellow);
        
        DirectionalLightShadowRenderer shadowRenderer2 = new DirectionalLightShadowRenderer(app.getAssetManager(), 512, 1);
        shadowRenderer2.setLight(sun);
        app.getViewPort().addProcessor(shadowRenderer2);   
        
    }
    
    public PhysicsRigidBody addImmovable(Geometry geometry) {
        
        CollisionShape shape = CollisionShapeFactory.createMeshShape(geometry);
        PhysicsRigidBody body = new PhysicsRigidBody(shape, 0);
        body.setPhysicsLocation(geometry.getLocalTranslation());
        bulletAppState.getPhysicsSpace().add(body);
        app.getRootNode().attachChild(geometry);
        return body;
    }
    
    public RigidBodyControl addMovable(Geometry geometry) {
        
        CollisionShape shape = CollisionShapeFactory.createDynamicMeshShape(geometry);
        RigidBodyControl body = new RigidBodyControl(shape, 0);
        bulletAppState.getPhysicsSpace().add(body);
        geometry.addControl(body);
        app.getRootNode().attachChild(geometry);
        return body;
    }
    
}
