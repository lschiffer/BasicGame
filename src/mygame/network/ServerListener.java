/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.network;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

/**
 *
 * @author MasterM
 */
public class ServerListener implements MessageListener<HostedConnection> {
    public void messageReceived(HostedConnection source, Message message) {
    if (message instanceof PacketChat) {
      // do something with the message
      PacketChat helloMessage = (PacketChat)message;
    }
  }
}
