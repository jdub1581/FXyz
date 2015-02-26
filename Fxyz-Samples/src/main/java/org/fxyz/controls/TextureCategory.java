/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fxyz.controls;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import org.fxmisc.easybind.EasyBind;
import org.fxyz.controls.factory.ControlFactory;
import org.fxyz.scene.paint.Patterns;
import org.fxyz.shapes.primitives.helper.TriangleMeshHelper;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class TextureCategory extends ControlCategory{
    private static final TextureImage 
            //animatedWater = new Image(TextureTypeControl.class.getResource("/org/fxyz/images/anim.gif").toExternalForm()),
            texture01 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/texture002.jpg").toExternalForm(),"Sand"), 
            texture02 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/diamondPlate.jpg").toExternalForm(),"Diamond Plate"),
            texture03 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/tiled.jpg").toExternalForm(),"Tiled"),
            texture04 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/water.jpg").toExternalForm(),"Water"),
            texture05 = new TextureImage(TextureTypeControl.class.getResource("/org/fxyz/images/textures/metal-scale-tile.jpg").toExternalForm(),"Metal Tile");
    
    protected final ObservableList<TextureImage> textures = FXCollections.observableArrayList(texture01,texture02,texture03,texture04, texture05); 
    protected TextureTypeControl textureControl;
    
    protected ColorSliderControl colorSlider;    
    protected ImagePreviewControl diffMapControl;  
    
    protected NumberSliderControl patternScaler;
    protected ComboBoxControl<Patterns.CarbonPatterns> patternChooser;   
    
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
    
    protected ComboBoxControl<TriangleMeshHelper.SectionType> sectionType;
    
    private final BooleanBinding 
            useColorSlider,
            useImage,             
            useDensScriptor, 
            useFuncScriptor,
            usePatternChooser,
            usePatternScaler,
            useSpecColor,
            useSpecPower,
            useBumpMapping,
            notNull;
    
    public TextureCategory() {
        super("Texture Settings");
        buildSubControls();
        
        this.useColorSlider = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.NONE);        
        this.useImage = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.IMAGE);        
        this.usePatternChooser = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.PATTERN);
        this.usePatternScaler = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.PATTERN);          
        this.useBumpMapping = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.IMAGE)
                .or(textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.PATTERN));        
        this.useDensScriptor = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.COLORED_VERTICES_3D);
        this.useFuncScriptor = textureControl.getSelection().valueProperty().isEqualTo(TriangleMeshHelper.TextureType.COLORED_VERTICES_1D);        
        this.useSpecColor = textProperty().isNotNull();
        this.useSpecPower = textProperty().isNotNull();
        this.notNull = textProperty().isNotNull();
        
        EasyBind.includeWhen(getControlItems(), textureControl, notNull);
        EasyBind.includeWhen(getControlItems(), cullFace, notNull);
        EasyBind.includeWhen(getControlItems(), drawMode, notNull);        
        EasyBind.includeWhen(getControlItems(), colorSlider, useColorSlider);        
        EasyBind.includeWhen(getControlItems(), diffMapControl, useImage);        
        EasyBind.includeWhen(getControlItems(), patternChooser, usePatternChooser);
        EasyBind.includeWhen(getControlItems(), patternScaler, usePatternScaler);        
        EasyBind.includeWhen(getControlItems(), densFunct, useDensScriptor);
        EasyBind.includeWhen(getControlItems(), funcFunct, useFuncScriptor);        
        EasyBind.includeWhen(getControlItems(), specColor, useSpecColor);
        EasyBind.includeWhen(getControlItems(), specSlider, useSpecPower);        
        EasyBind.includeWhen(getControlItems(), bumpMap, useBumpMapping);
        EasyBind.includeWhen(getControlItems(), invertBumpMap, useBumpMapping);
        EasyBind.includeWhen(getControlItems(), bumpScale, useBumpMapping);
        EasyBind.includeWhen(getControlItems(), bumpFine, useBumpMapping);
    }

    private void buildSubControls() {

        this.textureControl = ControlFactory.buildTextureTypeControl(); 
        this.textureControl.getSelection().getSelectionModel().selectFirst();
        
        this.drawMode = ControlFactory.buildDrawModeControl();
        this.drawMode.getSelection().getSelectionModel().selectFirst();
        this.cullFace = ControlFactory.buildCullFaceControl();
        this.cullFace.getSelection().getSelectionModel().selectFirst();
        
        this.diffMapControl = ControlFactory.buildImageViewToggle("Image", textures);  
        this.diffMapControl.getImageSelector().getSelectionModel().selectFirst();
        
        this.patternChooser = ControlFactory.buildPatternChooser();
        this.patternChooser.getSelection().getSelectionModel().selectFirst();
        this.patternScaler = ControlFactory.buildNumberSlider(0.01, 1, 100);   
        
        // only if image or pattern        
        this.bumpMap = ControlFactory.buildCheckBoxControl();        
        this.invertBumpMap = ControlFactory.buildCheckBoxControl();
        this.bumpScale = ControlFactory.buildNumberSlider(0.01, 0, 100);
        this.bumpFine = ControlFactory.buildNumberSlider(0.01, 0.01, 100);        
        // only if texture none
        this.colorSlider = new ColorSliderControl("Diffuse Color",0, 1530);
        //                       
        this.densFunct = ControlFactory.buildScriptFunction3DControl();
        this.funcFunct = ControlFactory.buildScriptFunction1DControl();      
        
        this.specColor = new ColorSliderControl("Specular Color",0, 1530);
        this.specColor.setPrefSize(USE_COMPUTED_SIZE, USE_PREF_SIZE);
        this.specSlider = ControlFactory.buildNumberSlider(1, 32, 10000);
    }

    public void  resetBumpMap() {
        this.bumpMap.setSelected(false);
    }
    
    public TextureTypeControl getTextureControl() {
        return textureControl;
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

    public ComboBoxControl<Patterns.CarbonPatterns> getPatternChooser() {
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

    public ComboBoxControl<TriangleMeshHelper.SectionType> getSectionType() {
        return sectionType;
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
