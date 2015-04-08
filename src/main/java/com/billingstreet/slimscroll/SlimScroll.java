/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.billingstreet.slimscroll;

import java.io.IOException;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import org.ajax4jsf.javascript.JSLiteral;
import org.richfaces.application.ServiceTracker;
import org.richfaces.function.RichFunction;
import org.richfaces.javascript.JavaScriptService;

/**
 *
 * @author duncan
 */
@FacesComponent(value = "slimscroll")
@ResourceDependency(name = "js/slimscroll.js")
public class SlimScroll extends UIComponentBase {

    private String height = "300";
    private boolean railVisible = true;
    private boolean alwaysVisible;
    
    @Override
    public String getFamily() {
        return "Output";
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        JavaScriptService service = ServiceTracker.getService(JavaScriptService.class);
        UIComponent parent = getParent();

        String jQueryId = RichFunction.jQuerySelector(parent.getClientId());
//        String function = "$('"+ jQueryId + "').slimScroll({ height: '" + height + "px' });";
        StringBuilder sb = new StringBuilder()
                .append("$('"+ jQueryId + "').slimScroll(")
                .append("{ height: '" + height + "px'")
                .append(", alwaysVisible: " + alwaysVisible)
                .append(", railVisible: " + railVisible)
                .append("});");
        JSLiteral literal = new JSLiteral(sb.toString());
        service.addPageReadyScript(context, literal);

    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isRailVisible() {
        return railVisible;
    }

    public void setRailVisible(boolean railVisible) {
        this.railVisible = railVisible;
    }

    public boolean isAlwaysVisible() {
        return alwaysVisible;
    }

    public void setAlwaysVisible(boolean alwaysVisible) {
        this.alwaysVisible = alwaysVisible;
    }
 
}
