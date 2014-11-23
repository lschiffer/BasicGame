/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.collision.MotionAllowedListener;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author MasterM
 */
public class PlayerController implements AnalogListener, ActionListener {

    private static String[] mappings = new String[]{
            "PLAYER_StrafeLeft",
            "PLAYER_StrafeRight",
            "PLAYER_Forward",
            "PLAYER_Backward",
            
            "PLAYER_Jump"
        };

    protected Camera cam;
    protected float moveSpeed = 3f;
    protected MotionAllowedListener motionAllowed = null;
    protected boolean enabled = true;
    protected InputManager inputManager;
    
    /**
     * Creates a new FlyByCamera to control the given Camera object.
     * @param cam
     */
    public PlayerController(Camera cam){
        this.cam = cam;
    }

    public void setMotionAllowedListener(MotionAllowedListener listener){
        this.motionAllowed = listener;
    }

    /**
     * Sets the move speed. The speed is given in world units per second.
     * @param moveSpeed
     */
    public void setMoveSpeed(float moveSpeed){
        this.moveSpeed = moveSpeed;
    }
    
    /**
     * Gets the move speed. The speed is given in world units per second.
     * @return moveSpeed
     */
    public float getMoveSpeed(){
        return moveSpeed;
    }

    /**
     * @param enable If false, the camera will ignore input.
     */
    public void setEnabled(boolean enable){
        if (enabled && !enable){
            if (inputManager!= null){
                inputManager.setCursorVisible(true);
            }
        }
        enabled = enable;
    }
    
    public boolean isEnabled(){
        return enabled;
    }

    /**
     * Registers the controller to receive input events from the provided
     * Dispatcher.
     * @param inputManager
     */
    public void registerWithInput(InputManager inputManager){
        this.inputManager = inputManager;
        
        // keyboard only WASD for movement and WZ for rise/lower height
        inputManager.addMapping("PLAYER_StrafeLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("PLAYER_StrafeRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("PLAYER_Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("PLAYER_Backward", new KeyTrigger(KeyInput.KEY_S));
        
        inputManager.addMapping("PLAYER_Jump", new KeyTrigger(KeyInput.KEY_SPACE));

        inputManager.addListener(this, mappings);
        inputManager.setCursorVisible(!isEnabled());
    }

    /**
     * Registers the controller to receive input events from the provided
     * Dispatcher.
     * @param inputManager
     */
    public void unregisterInput(){
    
        if (inputManager == null) {
            return;
        }
    
        for (String s : mappings) {
            if (inputManager.hasMapping(s)) {
                inputManager.deleteMapping( s );
            }
        }

        inputManager.removeListener(this);
        inputManager.setCursorVisible(true);
    }

    protected void moveCamera(float value, boolean sideways){
        Vector3f vel = new Vector3f();
        Vector3f pos = cam.getLocation().clone();

        if (sideways){
            cam.getLeft(vel);
        }else{
            cam.getDirection(vel);
        }
        vel.multLocal(value * moveSpeed);

        if (motionAllowed != null)
            motionAllowed.checkMotionAllowed(pos, vel);
        else
            pos.addLocal(vel);

        cam.setLocation(pos);
    }

    public void onAnalog(String name, float value, float tpf) {
        if (!enabled)
            return;

        if (name.equals("PLAYER_Forward")){
            moveCamera(value, false);
        }else if (name.equals("PLAYER_Backward")){
            moveCamera(-value, false);
        }else if (name.equals("PLAYER_StrafeLeft")){
            moveCamera(value, true);
        }else if (name.equals("PLAYER_StrafeRight")){
            moveCamera(-value, true);
        }else if (name.equals("PLAYER_Jump")){
        }
    }

    public void onAction(String name, boolean value, float tpf) {
        if (!enabled)
            return;

             
    }

}
