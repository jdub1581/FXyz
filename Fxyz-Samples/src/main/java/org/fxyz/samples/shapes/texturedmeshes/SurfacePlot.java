/**
* SurfacePlot.java
*
* Copyright (c) 2013-2015, F(X)yz
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* * Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
* * Redistributions in binary form must reproduce the above copyright
* notice, this list of conditions and the following disclaimer in the
* documentation and/or other materials provided with the distribution.
* * Neither the name of the organization nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.fxyz.samples.shapes.texturedmeshes;

import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import org.fxmisc.easybind.EasyBind;
import org.fxyz.controls.NumberSliderControl;
import org.fxyz.controls.ScriptFunction2DControl;
import org.fxyz.controls.factory.ControlFactory;
import org.fxyz.samples.shapes.TexturedMeshSample;
import org.fxyz.shapes.primitives.SurfacePlotMesh;

/**
 *
 * @author jpereda
 */
public class SurfacePlot extends TexturedMeshSample {
    public static void main(String[] args){SurfacePlot.launch(args);}
        
    @Override
    public void createMesh() {        
        model = new SurfacePlotMesh(p->Math.sin(p.magnitude())/p.magnitude(), 20.0,20.0,100,100,2.0);        
        model.getTransforms().addAll(new Rotate(0, Rotate.X_AXIS), rotateY);    
        group.getChildren().add(model);         
    }
    
    @Override
    protected void addMeshAndListeners() {
        
    }
    
    @Override
    protected Node buildControlPanel() {                
        this.controlPanel = ControlFactory.buildControlPanel();
        
        ScriptFunction2DControl func = ControlFactory.buildScriptFunction2DControl();
        NumberSliderControl rx = ControlFactory.buildNumberSlider(0.01, 1, 100);
        NumberSliderControl ry = ControlFactory.buildNumberSlider(0.01, 1, 100);
        NumberSliderControl dx = ControlFactory.buildNumberSlider(1, 1, 500);
        NumberSliderControl dy = ControlFactory.buildNumberSlider(1, 1, 500);
        NumberSliderControl s  = ControlFactory.buildNumberSlider(0.01, 0.01, 100);
               
        controlPanel.getGeometry().addControls(func,rx,ry,dx,dy,s);
        
        modelVisible = EasyBind.combine(
        model.visibleProperty(), model.sceneProperty(),
        (visible, scene) -> visible && scene != null);
        
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).function2DProperty(), func.functionProperty());        
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).rangeXProperty(), rx.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).rangeYProperty(), ry.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).divisionsXProperty(), dx.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).divisionsYProperty(), dy.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((SurfacePlotMesh)model).scaleProperty(), s.getSlider().valueProperty()); 
        
        return controlPanel;
    }
    

    
    
}
