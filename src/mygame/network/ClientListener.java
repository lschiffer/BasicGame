/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.network;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

/**
 *
 * @author MasterM
 */
public class ClientListener implements MessageListener<Client> {
    
    public void messageReceived(Client source, Message message) {
        if (message instanceof PacketChat) {
          PacketChat helloMessage = (PacketChat)message;
    }
  }
}
