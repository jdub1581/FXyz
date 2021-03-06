/**
 * ControlPanel.java
 * 
* Copyright (c) 2013-2015, F(X)yz All rights reserved.
 * 
* Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the organization nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.fxyz.controls;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.fxyz.controls.factory.ControlFactory;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class ControlPanel extends VBox {
    
    private final ControlBasePane accordion;
    protected final LightingCategory lights;
    protected final ControlCategory geometry;
    protected final TextureCategory texType;
    

    public ControlPanel() {
        this.accordion = new ControlBasePane();
        
        this.lights = new LightingCategory();
        this.geometry = (ControlFactory.buildCategory("Mesh Geometry"));
        this.texType = new TextureCategory();
        
        this.accordion.getPanes().addAll(lights, geometry, texType);
        VBox.setVgrow(accordion, Priority.ALWAYS);
        
        this.getChildren().add(accordion);
        this.setAlignment(Pos.CENTER);
    }

    public ControlPanel withCategory(ControlCategory cat) {
        if (accordion != null) {
            this.accordion.getPanes().add(cat);
        }
        return this;
    }

    public LightingCategory getLights() {
        return lights;
    }

    public ControlCategory getGeometry() {
        return geometry;
    }

    public TextureCategory getTextureTypes() {
        return texType;
    }

    
    
}
