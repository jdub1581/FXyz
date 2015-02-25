/**
* LightingControls.java
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

package org.fxyz.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfxtras.scene.control.ListSpinner;

/**
 * FXML Controller class
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class LightingControls extends ControlBase{

    @FXML
    private VBox lighting1;
    @FXML
    private Label light1Label;
    @FXML
    private Slider colorL1;
    @FXML
    private VBox rotControlL1;
    @FXML
    private ListSpinner<Integer> light1Rotate;
    @FXML
    private HBox rotAxisL1;
    @FXML
    private ToggleButton xAxisL1;
    @FXML
    private ToggleButton yAxisL1;
    @FXML
    private ToggleButton zAxisL1;
    @FXML
    private Slider distL1;
    @FXML
    private CheckBox l1On;

    private final ObservableList<Integer> angL1 = FXCollections.observableArrayList();

    public LightingControls() {
        super("/org/fxyz/controls/LightControlPanel.fxml");
        this.setOnMouseEntered(me->{me.consume();});
        colorL1.getStyleClass().addAll("colorSlider", "lighting-slider");        
        loadAngles();
    }

    private void loadAngles() {
        for (int i = 0; i < 360; i+=3) {
            angL1.add(i);
        }
        light1Rotate.setItems(angL1);
    }

    public Label getLight1Label() {
        return light1Label;
    }

    public Slider getColorL1() {
        return colorL1;
    }

    public ListSpinner<Integer> getLight1Rotate() {
        return light1Rotate;
    }

    public ToggleButton getxAxisL1() {
        return xAxisL1;
    }

    public ToggleButton getyAxisL1() {
        return yAxisL1;
    }

    public ToggleButton getzAxisL1() {
        return zAxisL1;
    }

    public Slider getDistL1() {
        return distL1;
    }

    public CheckBox getL1On() {
        return l1On;
    }

    public ObservableList<Integer> getAngL1() {
        return angL1;
    }
    
    

}
