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
        app.getFlyByCamera().setDragToRotate(true);

        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        
        this.hudScreen = new HudScreen(this);
        this.mainMenu = new MainMenuScreen(this);
        this.settingsScreen = new SettingsScreen(this);
        
        this.hudScreen.init();
        this.mainMenu.init();
        this.settingsScreen.init();

        /*
        // <screen>
        nifty.addScreen("Screen_ID", new ScreenBuilder("Hello Nifty Screen"){{
            controller(new DefaultScreenController()); // Screen properties       

            // <layer>
            layer(new LayerBuilder("Layer_ID") {{
                childLayoutVertical(); // layer properties, add more...

                // <panel>
                panel(new PanelBuilder("Panel_ID") {{
                   childLayoutCenter(); // panel properties, add more...               

                    // GUI elements
                    control(new ButtonBuilder("Button_ID", "Hello Nifty"){{
                        alignCenter();
                        valignCenter();
                        height("5%");
                        width("15%");
                    }});

                    //.. add more GUI elements here              

                }});
                // </panel>
              }});
            // </layer>
          }}.build(nifty));
        // </screen>
        * */
        

        //nifty.gotoScreen("Screen_ID"); // start the screen
    }
}
