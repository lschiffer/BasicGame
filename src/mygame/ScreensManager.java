/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

/**
 *
 * @author MasterM
 */
public class ScreensManager {
    private GameManager game = null;
    
    ScreensManager(GameManager parentGame)
    {
        this.game = parentGame;
    }
    
    public void init()
    {
        Main app = game.getApp();
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
            app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        Nifty nifty = niftyDisplay.getNifty();
        app.getGuiViewPort().addProcessor(niftyDisplay);
        app.getFlyByCamera().setDragToRotate(true);

        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");

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
        
        ///// Main Menu //////
        nifty.addScreen("main", new ScreenBuilder("main"){{
            controller(new DefaultScreenController());
        }}.build(nifty));
        //////////////////////
        
        //////// HUD ////////
        nifty.addScreen("hud", new ScreenBuilder("hud"){{
            controller(new DefaultScreenController());
        }}.build(nifty));
        /////////////////////

        //nifty.gotoScreen("Screen_ID"); // start the screen
    }
}
