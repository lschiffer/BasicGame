/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.network;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author MasterM
 */
@Serializable
public class PacketChat extends AbstractMessage {
    private String chatMessage;
    public PacketChat() { }
    public PacketChat(String s) { chatMessage = s; }
}
