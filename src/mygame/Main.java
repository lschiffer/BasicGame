package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Client;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.io.IOException;

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
        Main app = new Main(true);
        app.showSettings = false;
        app.start();
    }
    
    Main(boolean initAsServer)
    {
        this.isServer = initAsServer;
    }

    @Override
    public void simpleInitApp() {
        if (this.isServer) {
            try {
                myServer = Network.createServer(serverPort);
                myServer.start();
                myGameManager = new GameManager(this);
            } catch (IOException exception) {
            }
        } else {
            try {
                myClient = Network.connectToServer("", serverPort);
                myClient.start();
                myGameManager = new GameManager(this);
            } catch (IOException exception) {
            }
        }
        
        if (myGameManager != null)
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
