package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.network.Client;
import com.jme3.network.Server;
import com.jme3.renderer.RenderManager;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private static final int serverPort = 27375;
    private boolean isServer = false;
    private Server myServer = null;
    private Client myClient = null;
    
    GameManager myGameManager = null;

    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }
    
    public Main()
    {
        super((AppState)null);
    }

    @Override
    public void simpleInitApp() {
        myGameManager = new GameManager(this);
        myGameManager.init();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
