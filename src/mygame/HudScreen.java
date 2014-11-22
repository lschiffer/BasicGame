package mygame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;

/**
 *
 * @author MasterM
 */
public class HudScreen {
    private final String screenID = "hud";
    
    private ScreensManager parent;
    
    HudScreen(ScreensManager parentScreensManager)
    {
        this.parent = parentScreensManager;
    }
    
    public void init()
    {
        Nifty nifty = parent.getNiftyDisplay().getNifty();
        ///// Main Menu //////
        nifty.addScreen(this.screenID, new ScreenBuilder(this.screenID){{
            controller(new DefaultScreenController());
        }}.build(nifty));
        //////////////////////
    }
}
