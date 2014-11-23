/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;

/**
 *
 * @author MasterM
 */
public class ScreensManager {
    private GameManager game = null;
    
    private HudScreen hudScreen = null;
    private MainMenuScreen mainMenu = null;
    private SettingsScreen settingsScreen = null;
    
    private NiftyJmeDisplay niftyDisplay = null;
    
    ScreensManager(GameManager parentGame)
    {
        this.game = parentGame;
    }
    
    public GameManager getGame() { return this.game; }
    public MainMenuScreen getMainScreen() { return this.mainMenu; }
    
    public NiftyJmeDisplay getNiftyDisplay() { return this.niftyDisplay; }
    
    public void init()
    {
        Main app = game.getApp();
        niftyDisplay = new NiftyJmeDisplay(
            app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        Nifty nifty = niftyDisplay.getNifty();
        app.getGuiViewPort().addProcessor(niftyDisplay);
        if (app.getFlyByCamera() != null)
            app.getFlyByCamera().setDragToRotate(true);

        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        
        this.hudScreen = new HudScreen(this);
        this.mainMenu = new MainMenuScreen(this);
        this.settingsScreen = new SettingsScreen(this);
        
        this.hudScreen.init();
        this.mainMenu.init();
        this.settingsScreen.init();
    }
}
