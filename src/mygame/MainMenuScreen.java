package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 *
 * @author MasterM
 */
public class MainMenuScreen extends AbstractAppState implements ScreenController {
    private final String screenID = "main";
    
    private ScreensManager parent;
    
    MainMenuScreen(ScreensManager parentScreensManager)
    {
        this.parent = parentScreensManager;
    }
    
    public void init()
    {
        final MainMenuScreen thisInstance = this;
        
        Nifty nifty = parent.getNiftyDisplay().getNifty();
        ///// Main Menu //////
        nifty.addScreen(this.screenID, new ScreenBuilder(this.screenID){{
            controller(thisInstance);
            
            // layer added
            /*layer(new LayerBuilder("background") {{
                childLayoutCenter();
                backgroundColor("#000000ff");
            }});*/
            
            // layer added
            layer(new LayerBuilder("foreground") {{
               childLayoutCenter();
               backgroundColor("#0000");
               
               // panel added
                panel(new PanelBuilder("panel_center") {{
                    childLayoutCenter();
                    alignCenter();
                    backgroundColor("#f008");
                    height("25%");
                    width("75%");
                    
                    // add control
                    control(new ButtonBuilder("CreateGameButton", "Create Game") {{
                      alignCenter();
                      valignCenter();
                      height("50%");
                      width("50%");
                    }});
                    
                    // add control
                    control(new ButtonBuilder("CreateGameButton", "Join Game") {{
                      alignCenter();
                      valignCenter();
                      height("50%");
                      width("50%");
                    }});
                    
                    // add control
                    control(new ButtonBuilder("QuitButton", "Quit") {{
                      alignCenter();
                      valignCenter();
                      height("50%");
                      width("50%");
                      interactOnClick("quitGame()");
                    }});
                }});
            }});
        }}.build(nifty));
        //////////////////////
    }
    
    public void showScreen()
    {
        Nifty nifty = parent.getNiftyDisplay().getNifty();
        nifty.gotoScreen(this.screenID);
    }
    
    public void quitGame() {
        this.parent.getGame().getApp().stop();
    }
    
    
    //// Abstract Class AbstractAppState: ////
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
        //this is called on the OpenGL thread after the AppState has been attached
    }
 
    @Override
    public void update(float tpf) {
        //TODO: implement behavior during runtime
    }
 
    @Override
    public void cleanup() {
        super.cleanup();
        //TODO: clean up what you initialized in the initialize method,
        //e.g. remove all spatials from rootNode
        //this is called on the OpenGL thread after the AppState has been detached
    }
    
    /////////////////////////////////////
    
    
    
    //// Interface ScreenController: ////
    
    public void bind(Nifty nifty, Screen screen) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
 
    public void onStartScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
 
    public void onEndScreen() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /////////////////////////////////////
}
