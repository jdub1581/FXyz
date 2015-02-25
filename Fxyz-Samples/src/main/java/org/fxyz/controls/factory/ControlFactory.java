/**
 * ControlFactory.java
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
package org.fxyz.controls.factory;

import java.util.Arrays;
import java.util.Collection;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.util.Builder;
import org.fxyz.controls.CheckBoxControl;
import org.fxyz.controls.ColorPickControl;
import org.fxyz.controls.ColorSliderControl;
import org.fxyz.controls.ComboBoxControl;
import org.fxyz.controls.ControlCategory;
import org.fxyz.controls.ControlPanel;
import org.fxyz.controls.ImagePreviewControl;
import org.fxyz.controls.LightingControls;
import org.fxyz.controls.NumberSliderControl;
import org.fxyz.controls.ScriptFunction1DControl;
import org.fxyz.controls.ScriptFunction2DControl;
import org.fxyz.controls.ScriptFunction3DControl;
import org.fxyz.controls.TextureImage;
import org.fxyz.controls.TextureTypeControl;
import org.fxyz.scene.paint.Patterns.CarbonPatterns;
import org.fxyz.shapes.primitives.helper.TriangleMeshHelper.SectionType;
import org.fxyz.shapes.primitives.helper.TriangleMeshHelper.TextureType;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public final class ControlFactory {

    public static final ControlPanel buildSingleListControlPanel() {
        return new ControlPanel();
    }

    public static final Builder<ControlPanel> controlPanelBuilder() {
        return () -> {
            return new ControlPanel();
        };
    }

    public static final ControlPanel buildControlPanel() {
        return new ControlPanel();
    }

    public static final ControlCategory buildCategory(final String title) {
        return new ControlCategory(title);
    }
    /*==========================================================================
     Standard Control Types
     ==========================================================================*/

    public static final CheckBoxControl buildCheckBoxControl() {
        return new CheckBoxControl();
    }

    public static final NumberSliderControl buildNumberSlider(final Number p, final Number lb, final Number ub) {
        return new NumberSliderControl(p, lb, ub);
    }

    public static final ColorSliderControl buildColorSliderControl(final Number lb, final Number ub) {
        return new ColorSliderControl(lb, ub);
    }

    public static final ColorPickControl buildColorControl(String name) {
        return new ColorPickControl(name);
    }

    public static final ImagePreviewControl buildImageViewToggle(String name, final Collection<TextureImage> imgs) {
        return new ImagePreviewControl(name, imgs);
    }
    /*==========================================================================
     List like Items
     ==========================================================================*/

    public static final ComboBoxControl buildCullFaceControl() {
        return new ComboBoxControl("Cull Face: ", Arrays.asList(CullFace.values()), false);
    }

    public static final ComboBoxControl<DrawMode> buildDrawModeControl() {
        return new ComboBoxControl<>("Draw Mode: ", Arrays.asList(DrawMode.values()), false);
    }

    public static final TextureTypeControl buildTextureTypeControl() {
        return new TextureTypeControl("Texture Type:", Arrays.asList(TextureType.values()));
    }

    public static final ComboBoxControl buildPatternChooser() {
        return new ComboBoxControl("Carbon Patterns: ", Arrays.asList(CarbonPatterns.values()), false);
    }

    public static final ComboBoxControl<SectionType> buildSectionTypeControl() {
        return new ComboBoxControl<>("Section Type", Arrays.asList(SectionType.values()), false);
    }

    public static final ScriptFunction3DControl buildScriptFunction3DControl() {
        return new ScriptFunction3DControl(Arrays.asList("Math.sin(p.x)", "p.y", "p.z", "p.x + p.y", "p.x + p.z", "p.f", "p.magnitude()"), false);
    }

    public static final ScriptFunction2DControl buildScriptFunction2DControl() {
        return new ScriptFunction2DControl(Arrays.asList("Math.sin(p.magnitude())/p.magnitude()", "p.x", "p.y", "p.x*3+p.y*p.y", "p.magnitude()"), false);
    }

    public static final ScriptFunction1DControl buildScriptFunction1DControl() {
        return new ScriptFunction1DControl(Arrays.asList("Math.sin(x)", "x*x", "x+3", "Math.pow(Math.abs(x),1/2.5)"), false);
    }
    /*==========================================================================
     Standard Controls for MeshView
     ==========================================================================*/
    /* 
     builds the complete ControlCategory, shared by all MeshViews
     DrawMode, CullFace, DiffuseColor, SpecularColor
     */

    public static ControlCategory buildMeshViewCategory() {
        ControlCategory mvc = new ControlCategory("Standard MeshView Properties");
        mvc.addControls(
                buildDrawModeControl(),
                buildCullFaceControl()
        );
        return mvc;
    }

    /*
    
     */
    public static ControlCategory buildTextureMeshCategory() {
        final TextureTypeControl texType = buildTextureTypeControl();
        final ControlCategory mvc = new ControlCategory("TexturedMesh Properties");
        mvc.addControls(
                texType
        );
        return mvc;
    }
    /**
     *
     * @return
     */
    public static ControlCategory buildSceneAndLightCategory() {
        final LightingControls lighting1 = new LightingControls();
        final LightingControls lighting2 = new LightingControls();
        final ControlCategory mvc = new ControlCategory("Scene Lighting");
        mvc.addControls(
                lighting1,
                lighting2,
                new CheckBoxControl());

        return mvc;
    }

    /*
     Build a Category for the four Image maps available to PhongMaterial
     */
}
