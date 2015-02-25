/**
* NumberSliderControl.java
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

import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class NumberSliderControl extends ControlBase{
    
    @FXML
    private Label propName;
    @FXML
    private StackPane spacer;
    @FXML
    private Label valueLabel;
    @FXML
    private Slider valSlider;
    
    private final NumberFormat format = NumberFormat.getInstance();
    
    public NumberSliderControl(final Number precision, final Number lowerBound, final Number upperBound) {
        super("/org/fxyz/controls/NumberSliderControl.fxml");
        
        valSlider.setBlockIncrement(precision.doubleValue());
        
        if(precision instanceof Integer){
            format.setMaximumFractionDigits(0);
            valSlider.setMin(lowerBound.intValue());
            valSlider.setMax(upperBound.intValue());
        }
        else if(precision instanceof Double){
            format.setMaximumFractionDigits(2);
            valSlider.setMin(lowerBound.doubleValue());
            valSlider.setMax(upperBound.doubleValue());
        }
        valueLabel.textProperty().bind(valSlider.valueProperty().asString());
                
        // PENDING
//        valSlider.valueProperty().addListener((ov,i,i1)->{
//            if(!valSlider.isValueChanging()){
//                model.setValue(i1);
//            }
//        });
//        valSlider.valueChangingProperty().addListener((ov,b,b1)->{
//            if(!b1){
//               model.setValue(valSlider.getValue());
//            }
//        });
        //controlledProperty.bind(valSlider.valueProperty());
        //propName.setText(!model.getName().isEmpty() ? model.getName() : "Empty Property Name:");
      
        
    }

    public Slider getSlider() {
        return valSlider;
    }

    public Label getPropName() {
        return propName;
    }

    public Label getValueLabel() {
        return valueLabel;
    }

    public Slider getValSlider() {
        return valSlider;
    }

    public NumberFormat getFormat() {
        return format;
    }

    

}
