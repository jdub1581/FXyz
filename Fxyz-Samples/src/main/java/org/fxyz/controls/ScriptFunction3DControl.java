/**
* ScriptFunction3DControl.java
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
import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.fxyz.geometry.Point3D;

/**
 *
 * @author Jason Pollastrini aka jdub1581
 */
public class ScriptFunction3DControl extends ControlBase{

    private ObjectProperty<Function<Point3D,Number>> function = new SimpleObjectProperty<>();
    
    private BooleanProperty change=new SimpleBooleanProperty();
    private BooleanProperty error=new SimpleBooleanProperty();
    
    public ScriptFunction3DControl(final Collection<String> items, boolean subControl) {
        super("/org/fxyz/controls/ScriptFunction3DControl.fxml");
        
        Point3D p=new Point3D(1f,2f,3f);
        res1.setText("p: {"+p.x+","+p.y+","+p.z+"}");
                    
        selection.getItems().setAll(items);
        selection.getItems().add("Enter a valid expression");
        if (subControl) {
           // subControlCache = FXCollections.observableHashMap();
            this.usesSubControls.set(subControl);
        }
        selection.getEditor().setEditable(false);
        selection.getStyleClass().add("noEditable-textField");
        selection.getSelectionModel().selectedIndexProperty().addListener((obs,n,n1)->{
            if(n1!=null){
                selection.getStyleClass().remove("noEditable-textField");
                selection.getEditor().setEditable(n1.intValue()==items.size());
                if(selection.getEditor().isEditable()){
                    selection.getEditor().selectAll();
                    Platform.runLater(()->{
                        selection.getEditor().setText("");
                        selection.getEditor().promptTextProperty().unbind();
                        selection.getEditor().setPromptText("Enter a valid expression");
                    });
                } else {
                    selection.getStyleClass().add("noEditable-textField");
                    change.set(true);
                }
                //controlledProperty.unbind();
                //controlledProperty.bind(function);
            }
        });
        
        selection.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
        if (event.getCode() == KeyCode.ENTER && !selection.getEditor().getText().isEmpty()){
                change.set(true);
            }
        });
        selection.getEditor().focusedProperty().addListener((obs,b,b1)->{
            if(!b1 && !selection.getEditor().getText().isEmpty()){
                change.set(true);
            }
        });
        change.addListener((obs,b,b1)->{
            if(b1){
                change.set(false);
                String text=selection.getValue();
                if(!selection.getEditor().getText().isEmpty()){
                    text=selection.getEditor().getText();
                }
                @SuppressWarnings("unchecked")
                Function<Point3D,Number> f;
                try {
                    f = (Function<Point3D,Number>)engine.eval(
                            String.format("new java.util.function.Function(%s)", "function(p) "+text));
                    // check if f is a valid function
                    try{
                        res2.setText("val: "+String.format("%.3f", f.apply(p)));
                        error.set(false);
                    } catch(Exception e){
                        res2.setText("val: error");
                        error.set(true);
                    }
                    function.set(f);
                } catch (RuntimeException | ScriptException ex) {
                    System.err.println("Script Error "+ex);
                }
            }
        });
        
        selection.getSelectionModel().select(0);
        
        
    }
    
    public Property<Function<Point3D,Number>> functionProperty() { return function; }
            
    @FXML
    private Label res1;
    @FXML
    private Label res2;
    @FXML
    private ComboBox<String> selection;
    private final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    
    @FXML
    protected VBox subControls;

    public ObjectProperty<Function<Point3D, Number>> getFunction() {
        return function;
    }

    public BooleanProperty getChange() {
        return change;
    }

    public Label getRes1() {
        return res1;
    }

    public Label getRes2() {
        return res2;
    }

    public ComboBox<String> getSelection() {
        return selection;
    }

    public ScriptEngine getEngine() {
        return engine;
    }

    public VBox getSubControls() {
        return subControls;
    }

    public BooleanProperty getUsesSubControls() {
        return usesSubControls;
    }

   

    private final BooleanProperty usesSubControls = new SimpleBooleanProperty(this, "usesSubControls", false) {

        @Override
        protected void invalidated() {
            super.invalidated();

        }

    };

    protected boolean useSubControls() {
        return usesSubControls.get();
    }

    public BooleanProperty usingSubControlsProperty() {
        return usesSubControls;
    }
}
