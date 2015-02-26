/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fxyz.controls;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class LightingCategory extends ControlCategory{

    protected LightingControls light1Controls, light2Controls;
    
    public LightingCategory() {
        super("Scene and Lighting");
        light1Controls = new LightingControls();
        light2Controls = new LightingControls();
        
        super.addControls(light1Controls, light2Controls);
    }

    public LightingControls getLight1Controls() {
        return light1Controls;
    }

    public LightingControls getLight2Controls() {
        return light2Controls;
    }
    
}
