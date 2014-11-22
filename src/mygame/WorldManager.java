package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 * @author lschiffer
 */
public class WorldManager {
    
    private BulletAppState bulletAppState;
    
    
    public WorldManager(AppStateManager appStateManager) {
        
        bulletAppState = new BulletAppState();
        appStateManager.attach(bulletAppState);  
    }
    
    public void addImmovable(Geometry geometry) {
        
        CollisionShape shape = CollisionShapeFactory.createMeshShape(geometry);
        PhysicsRigidBody body = new PhysicsRigidBody(shape);
        bulletAppState.getPhysicsSpace().add(body); 
    }
    
    public void addMovable(Geometry geometry) {
        
        CollisionShape shape = CollisionShapeFactory.createDynamicMeshShape(geometry);
        RigidBodyControl body = new RigidBodyControl(shape);
        bulletAppState.getPhysicsSpace().add(body);
    }
    
}
