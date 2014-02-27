/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze3d;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;

/**
 *
 * @author leon
 */
public class Maze3D extends SimpleApplication {

    private Tablero tablero = new Tablero(5, 5, 5);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Maze3D app = new Maze3D();
            app.start();
    }

    @Override
    public void simpleInitApp() {
       tablero.muestraTablero(this);
    }

}
