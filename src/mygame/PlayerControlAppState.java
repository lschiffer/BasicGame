/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author MasterM
 */
public class PlayerControlAppState extends AbstractAppState {

    private Application app;
    private PlayerController pController;

    public PlayerControlAppState(PlayerController controller) {
        this.pController = controller;
    }    

    /**
     *  This is called by SimpleApplication during initialize().
     */
    /*void setPlayerController( PlayerController controller ) {
        this.pController = controller;
    }*/
    
    public PlayerController getPlayerController() {
        return pController;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        
        this.app = app;

        if (app.getInputManager() != null) {
        
            if (pController == null) {
                pController = new PlayerController(app.getCamera());
            }
            
            pController.registerWithInput(app.getInputManager());            
        }               
    }
            
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        
        pController.setEnabled(enabled);
    }
    
    @Override
    public void cleanup() {
        super.cleanup();

        if (app.getInputManager() != null) {        
            pController.unregisterInput();
        }        
    }


}
