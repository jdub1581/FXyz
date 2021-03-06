/**
* Cones.java
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
import org.fxmisc.easybind.EasyBind;
import org.fxyz.controls.NumberSliderControl;
import org.fxyz.controls.factory.ControlFactory;
import org.fxyz.samples.shapes.TexturedMeshSample;
import org.fxyz.shapes.primitives.ConeMesh;

/**
 *
 * @author Birdasaur
 * adapted jDub's Capsule Test merged with my original Cone Test
 */
public class Cones extends TexturedMeshSample {
    
    public static void main(String[] args){launch(args);}    
    
    @Override
    protected void createMesh() {
        model = new ConeMesh(64, 50, 75);
        group.getChildren().add(model);
    }

    @Override
    protected void addMeshAndListeners() {
        
    }

    @Override
    protected Node buildControlPanel() {
        controlPanel = ControlFactory.buildControlPanel();
        
        NumberSliderControl divsSlider = ControlFactory.buildNumberSlider(0.01, .01D, 200D);
        NumberSliderControl heightSlider = ControlFactory.buildNumberSlider(0.01, .01D, 200D);        
        NumberSliderControl radSlider = ControlFactory.buildNumberSlider(0.01, .01D, 200D);
        
        controlPanel.getGeometry().addControls(divsSlider,heightSlider,radSlider);
        
        modelVisible = EasyBind.combine(
        model.visibleProperty(), model.sceneProperty(),
        (visible, scene) -> visible && scene != null);
        
        EasyBind.when(modelVisible).bind(((ConeMesh)model).divisionsProperty(), divsSlider.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((ConeMesh)model).radiusProperty(), radSlider.getSlider().valueProperty());
        EasyBind.when(modelVisible).bind(((ConeMesh)model).heightProperty(), heightSlider.getSlider().valueProperty());       
        
        return controlPanel;
    }

   
}