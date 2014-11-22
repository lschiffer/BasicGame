/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import java.io.IOException;
import mygame.network.ClientListener;
import mygame.network.PacketChat;
import mygame.network.ServerListener;

/**
 *
 * @author MasterM
 */
public class NetworkManager {
    private GameManager game;
    
    private static final int serverPort = 27375;
    private boolean isServer = false;
    private boolean isActive = false;
    private Server myServer = null;
    private Client myClient = null;
    
    private ClientListener clientListener = null;
    private ServerListener serverListener = null;
    
    NetworkManager(GameManager parentGame)
    {
        this.game = parentGame;
    }
    
    public void init()
    {
        Serializer.registerClass(PacketChat.class);
    }
    
    public boolean startServer()
    {
        if (isActive)
            return false;
        
        try {
            myServer = Network.createServer(serverPort);
            myServer.start();
            if (serverListener == null)
                serverListener = new ServerListener();
            myServer.addMessageListener(serverListener, PacketChat.class);
            
            isServer = true;
            isActive = true;
            return true;
        } catch (IOException exception) {
        }
        
        return false;
    }
    
    public boolean stopServer()
    {
        if (!isServer || !isActive)
            return false;
        
        myServer.close();
        isServer = false;
        isActive = false;
        
        return true;
    }
    
    public boolean connectToServer(String serverAddr)
    {
        if (isActive)
            return false;
        
        try {
            myClient = Network.connectToServer(serverAddr, serverPort);
            myClient.start();
            if (clientListener == null)
                clientListener = new ClientListener();
            myClient.addMessageListener(clientListener, PacketChat.class);
            
            isActive = true;
            isServer = false;
            return true;
        } catch (IOException exception) {
        }
        
        return false;
    }
    
    public boolean closeConnectionToServer()
    {
        if (isServer || !isActive)
            return false;
        
        myClient.close();
        isActive = false;
        
        return true;
    }
    
    public boolean sendPacket(Message pkt)
    {
        if (!isActive)
            return false;
        
        if (isServer)
            myServer.broadcast(pkt);
        else
            myClient.send(pkt);
                
        
        return true;
    }
}
