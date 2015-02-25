/**
* TextureTypeControl.java
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

import java.util.Collection;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import org.fxmisc.easybind.EasyBind;
import org.fxyz.controls.factory.ControlFactory;
import org.fxyz.shapes.primitives.helper.TriangleMeshHelper.TextureType;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class TextureTypeControl extends ComboBoxControl<TextureType>{

    private static final TextureImage 
            //animatedWater = new Image(TextureTypeControl.class.getResource("/org/fxyz/images/anim.gif").toExternalForm()),
            texture01 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/texture002.jpg").toExternalForm(),"Sand"), 
            texture02 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/diamondPlate.jpg").toExternalForm(),"Diamond Plate"),
            texture03 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/tiled.jpg").toExternalForm(),"Tiled"),
            texture04 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/water.jpg").toExternalForm(),"Water"),
            texture05 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/metal-scale-tile.jpg").toExternalForm(),"Metal Tile");
    
    protected final ObservableList<TextureImage> textures; 
    
    protected ColorSliderControl colorSlider;    
    protected ImagePreviewControl diffMapControl;      
    protected NumberSliderControl patternScaler;
    protected ComboBoxControl patternChooser;    
    protected ScriptFunction3DControl densFunct;
    protected ScriptFunction1DControl funcFunct;    
    protected ColorSliderControl specColor;
    protected NumberSliderControl specSlider;    
    protected CheckBoxControl bumpMap;
    protected CheckBoxControl invertBumpMap;
    protected NumberSliderControl bumpScale;
    protected NumberSliderControl bumpFine; 
    protected ComboBoxControl<CullFace> cullFace;
    protected ComboBoxControl<DrawMode> drawMode;
    
    private final BooleanBinding 
            useColorSlider,
            useImage,             
            useDensScriptor, 
            useFuncScriptor,
            usePatternChooser,
            usePatternScaler,
            useSpecColor,
            useSpecPower,
            useBumpMapping;
    
    public TextureTypeControl(
            final String lbl, 
            final Collection<TextureType> items
    ) {
        super(lbl, items, true);
        this.textures = FXCollections.observableArrayList(texture01,texture02,texture03,texture04, texture05);        
        buildSubControls();
        
        this.useColorSlider = selection.valueProperty().isEqualTo(TextureType.NONE);        
        this.useImage = selection.valueProperty().isEqualTo(TextureType.IMAGE);        
        this.usePatternChooser = selection.valueProperty().isEqualTo(TextureType.PATTERN);
        this.usePatternScaler = selection.valueProperty().isEqualTo(TextureType.PATTERN);          
        this.useBumpMapping = selection.valueProperty().isEqualTo(TextureType.IMAGE)
                .or(selection.valueProperty().isEqualTo(TextureType.PATTERN));        
        this.useDensScriptor = selection.valueProperty().isEqualTo(TextureType.COLORED_VERTICES_3D);
        this.useFuncScriptor = selection.valueProperty().isEqualTo(TextureType.COLORED_VERTICES_1D);        
        this.useSpecColor = selection.valueProperty().isNotNull();
        this.useSpecPower = selection.valueProperty().isNotNull();
        
        EasyBind.includeWhen(subControls.getChildren(), colorSlider, useColorSlider);        
        EasyBind.includeWhen(subControls.getChildren(), diffMapControl, useImage);        
        EasyBind.includeWhen(subControls.getChildren(), patternChooser, usePatternChooser);
        EasyBind.includeWhen(subControls.getChildren(), patternScaler, usePatternScaler);        
        EasyBind.includeWhen(subControls.getChildren(), densFunct, useDensScriptor);
        EasyBind.includeWhen(subControls.getChildren(), funcFunct, useFuncScriptor);        
        EasyBind.includeWhen(subControls.getChildren(), specColor, useSpecColor);
        EasyBind.includeWhen(subControls.getChildren(), specSlider, useSpecPower);        
        EasyBind.includeWhen(subControls.getChildren(), bumpMap, useBumpMapping);//.or(diffMapControl.getImageSelector().valueProperty().isNotEqualTo(animatedWater)));
        EasyBind.includeWhen(subControls.getChildren(), invertBumpMap, useBumpMapping);//.or(diffMapControl.getImageSelector().valueProperty().isNotEqualTo(animatedWater)));
        EasyBind.includeWhen(subControls.getChildren(), bumpScale, useBumpMapping);//.or(diffMapControl.getImageSelector().valueProperty().isNotEqualTo(animatedWater)));
        EasyBind.includeWhen(subControls.getChildren(), bumpFine, useBumpMapping);//.or(diffMapControl.getImageSelector().valueProperty().isNotEqualTo(animatedWater)));
    }

    @Override
    protected boolean useSubControls() {
        return true;
    }

    private void buildSubControls() {
      
        this.drawMode = ControlFactory.buildDrawModeControl();
        this.cullFace = ControlFactory.buildCullFaceControl();
        
        this.diffMapControl = ControlFactory.buildImageViewToggle("Image", textures);        
        this.patternChooser = ControlFactory.buildPatternChooser();
        this.patternScaler = ControlFactory.buildNumberSlider(0.01, 1, 100);   
        
        // only if image or pattern        
        this.bumpMap = ControlFactory.buildCheckBoxControl();        
        this.invertBumpMap = ControlFactory.buildCheckBoxControl();
        this.bumpScale = ControlFactory.buildNumberSlider(0.01, 0, 100);
        this.bumpFine = ControlFactory.buildNumberSlider(0.01, 0.01, 100);        
        // only if texture none
        this.colorSlider = ControlFactory.buildColorSliderControl(0l, 1530l);
        //                       
        this.densFunct = ControlFactory.buildScriptFunction3DControl();
        this.funcFunct = ControlFactory.buildScriptFunction1DControl();        
        this.specColor = new ColorSliderControl(0, 1530);
        this.specColor.setPrefSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
        this.specSlider = ControlFactory.buildNumberSlider(1, 32, 10000);
    }

    public void  resetBumpMap() {
        this.bumpMap.setSelected(false);
    }

    public static TextureImage getTexture01() {
        return texture01;
    }

    public static TextureImage getTexture02() {
        return texture02;
    }

    public static TextureImage getTexture03() {
        return texture03;
    }

    public static TextureImage getTexture04() {
        return texture04;
    }

    public static TextureImage getTexture05() {
        return texture05;
    }

    public ObservableList<TextureImage> getTextures() {
        return textures;
    }

    public ColorSliderControl getColorSlider() {
        return colorSlider;
    }

    public ImagePreviewControl getDiffMapControl() {
        return diffMapControl;
    }

    public NumberSliderControl getPatternScaler() {
        return patternScaler;
    }

    public ComboBoxControl getPatternChooser() {
        return patternChooser;
    }

    public ScriptFunction3DControl getDensFunct() {
        return densFunct;
    }

    public ScriptFunction1DControl getFuncFunct() {
        return funcFunct;
    }

    public ColorSliderControl getSpecColor() {
        return specColor;
    }

    public NumberSliderControl getSpecSlider() {
        return specSlider;
    }

    public CheckBoxControl getBumpMap() {
        return bumpMap;
    }

    public CheckBoxControl getInvertBumpMap() {
        return invertBumpMap;
    }

    public NumberSliderControl getBumpScale() {
        return bumpScale;
    }

    public NumberSliderControl getBumpFine() {
        return bumpFine;
    }

    public ComboBoxControl<CullFace> getCullFace() {
        return cullFace;
    }

    public ComboBoxControl<DrawMode> getDrawMode() {
        return drawMode;
    }
        
    public BooleanBinding getUseColorSlider() {
        return useColorSlider;
    }

    public BooleanBinding getUseImage() {
        return useImage;
    }

    public BooleanBinding getUseDensScriptor() {
        return useDensScriptor;
    }

    public BooleanBinding getUseFuncScriptor() {
        return useFuncScriptor;
    }

    public BooleanBinding getUsePatternChooser() {
        return usePatternChooser;
    }

    public BooleanBinding getUsePatternScaler() {
        return usePatternScaler;
    }

    public BooleanBinding getUseSpecColor() {
        return useSpecColor;
    }

    public BooleanBinding getUseSpecPower() {
        return useSpecPower;
    }

    public BooleanBinding getUseBumpMapping() {
        return useBumpMapping;
    }
    
    
}
