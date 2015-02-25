/**
* Spheroids.java
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

package org.fxyz.pending;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import org.fxmisc.easybind.EasyBind;
import org.fxyz.controls.ControlCategory;
import org.fxyz.controls.NumberSliderControl;
import org.fxyz.controls.factory.ControlFactory;
import org.fxyz.samples.shapes.ShapeBaseSample;
import org.fxyz.shapes.primitives.SpheroidMesh;

/**
 *
 * @author Dub
 */
public class Spheroids extends ShapeBaseSample<SpheroidMesh> {

    public static void main(String[] args){launch(args);}

    private final DoubleProperty minRad = new SimpleDoubleProperty(this, "Minor Radius : ", 25.0);
    private final DoubleProperty majRad = new SimpleDoubleProperty(this, "Major Radius : ", 50.0);
    private final IntegerProperty divs = new SimpleIntegerProperty(this, "Divisions :", 64);

    private final Property<CullFace> culling = new SimpleObjectProperty<>(this, "Cull Mode :", CullFace.NONE);
    private final Property<DrawMode> wireMode = new SimpleObjectProperty<>(this, "Draw Mode :", DrawMode.FILL);
    
    @Override
    protected void createMesh() {
        model = new SpheroidMesh();
        model.setDivisions(divs.get());
        model.setMinorRadius(minRad.doubleValue());
        model.setMajorRadius(majRad.doubleValue());
        model.setMaterial(material);

    }

    @Override
    protected void addMeshAndListeners() {
        
      
    }


    @Override
    protected Node buildControlPanel() {
        NumberSliderControl major = ControlFactory.buildNumberSlider(0.01, 10, 110);
        NumberSliderControl minor = ControlFactory.buildNumberSlider(0.01, 10, 110);
        NumberSliderControl div = ControlFactory.buildNumberSlider(1, 4, 64);

        ControlCategory geomControls  = controlPanel.getGeometry();
        geomControls.addControls(div, minor, major);        
        
        EasyBind.when(model.visibleProperty()).bind((model).drawModeProperty(), wireMode);
        EasyBind.when(model.visibleProperty()).bind((model).cullFaceProperty(), culling);
        EasyBind.when(model.visibleProperty()).bind((model).divisionsProperty(), div.getSlider().valueProperty());
        EasyBind.when(model.visibleProperty()).bind((model).majorRadiusProperty(), major.getSlider().valueProperty());
        EasyBind.when(model.visibleProperty()).bind((model).minorRadiusProperty(), minor.getSlider().valueProperty());
        
        return controlPanel;
    }

}
