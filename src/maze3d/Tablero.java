/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze3d;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leon
 */
public class Tablero {

    public static final int ESTE = 0;
    public static final int OESTE = 1;
    public static final int NORTE = 2;
    public static final int SUR = 3;
    public static final int FONDO = 4;
    public static final int FRENTE = 5;
    
    public static final Integer PARED=1;
    public static final Integer ENTRADA=2;
    public static final Integer SALIDA=3;

    private Integer[][][] tablero; // y, x, z

    private Integer x;
    private Integer y;
    private Integer z;

    private Integer topex;
    private Integer topey;
    private Integer topez;

    /**
     * Hacia donde está viendo el personaje 0 Este, se cambia x ++ 1 Oeste, se
     * cambia x -- 2 Norte, se cambia y -- 3 Sur, se cambia y ++ 4 Fondo, se
     * cambia z ++ 5 Frente, se cambia z --
     *
     */
    private Integer direccion_vista = 0;

    private Integer orientacion = 2;

    public Tablero(Integer largo, Integer ancho, Integer profundo) {
        //this.tablero = new Integer[largo][ancho][profundo];
        inicializaTablero();
        this.x = 0;
        this.y = 0;
        this.z = 0;

        this.topex = ancho;
        this.topey = largo;
        this.topez = profundo;

    }

    private void inicializaTablero() {
        this.tablero = new Integer[][][]{ // Normal:i,j,z || Para imprimir (j,i,z)  ==> x,y,z
            //El primer dos está en 0,0,0, la pared siguiente
            //está en 0,0,1
   /*i0*/{/*j0*/{/*z0*/1, /*z1*/1, /*z2*/1, 0, 0},/*j1*/{1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
   /*i1*/{/*j0*/{1, 1, 1, 0, 1}, /*j1*/{1, 1, 1, 1, 1}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
        {{1, 1, 1, 0, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
        {{1, 1, 1, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}},
        {{1, 1, 1, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 3}}
        };
    }

    public Integer avanzar() throws Exception {
        Integer resultado = 1;
        switch (direccion_vista) {
            case Tablero.ESTE:
                if (this.x + 1 > topex) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x + 1, y, z);
                this.x++;
                break;
            case Tablero.OESTE:
                if (this.x - 1 < 0) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x - 1, y, z);
                this.x--;
                break;
            case Tablero.NORTE:
                if (this.y - 1 < 0) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x, y - 1, z);
                this.y--;
                break;
            case Tablero.SUR:
                if (this.y + 1 > topey) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x, y + 1, z);
                this.y++;
                break;
            case Tablero.FONDO:
                if (this.z + 1 > topez) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x, y, z + 1);
                this.z++;
                break;
            case Tablero.FRENTE:
                if (this.z - 1 < 0) {
                    throw new Exception("Sobrepasa el limite");
                }
                verificaPosicion(x, y, z - 1);
                this.z--;
                break;

        }

        return resultado;
    }

    private Integer verificaPosicion(Integer x, Integer y, Integer z) throws Exception {

        switch (tablero[y][x][z]) {
            case 1:
                throw new Exception("Hay una pared.");
            case 2:
                return 1;
            default:
                return 0;

        }
    }

    private void giraIzquierda() {
        switch (orientacion) {
            case Tablero.NORTE:
                giraIzquierdaNorte();
                break;
            case Tablero.ESTE:
                giraIzquierdaEste();
                break;
            case Tablero.SUR:
                giraIzquierdaSur();
                break;
            case Tablero.OESTE:
                giraIzquierdaOeste();
                break;
            case Tablero.FONDO:
                giraIzquierdaFondo();
                break;
            case Tablero.FRENTE:
                giraIzquierdaFrente();
                break;
        }
    }

    private void giraDerecha() {
        switch (orientacion) {
            case Tablero.NORTE:
                giraDerechaNorte();
                break;
            case Tablero.ESTE:
                giraDerechaEste();
                break;
            case Tablero.SUR:
                giraDerechaSur();
                break;
            case Tablero.OESTE:
                giraDerechaOeste();
                break;
            case Tablero.FONDO:
                giraDerechaFondo();
                break;
            case Tablero.FRENTE:
                giraDerechaFrente();
                break;
        }
    }

    private void giraArriba() {
        switch (orientacion) {
            case Tablero.NORTE:
                giraArribaNorte();
                break;
            case Tablero.ESTE:
                giraArribaEste();
                break;
            case Tablero.SUR:
                giraArribaSur();
                break;
            case Tablero.OESTE:
                giraArribaOeste();
                break;
            case Tablero.FONDO:
                giraArribaFondo();
                break;
            case Tablero.FRENTE:
                giraArribaFrente();
                break;
        }
    }

    private void giraAbajo() {
        switch (orientacion) {
            case Tablero.NORTE:
                giraAbajoNorte();
                break;
            case Tablero.ESTE:
                giraAbajoEste();
                break;
            case Tablero.SUR:
                giraAbajoSur();
                break;
            case Tablero.OESTE:
                giraAbajoOeste();
                break;
            case Tablero.FONDO:
                giraAbajoFondo();
                break;
            case Tablero.FRENTE:
                giraAbajoFrente();
                break;
        }
    }

    public String obtenCoordenadas() {
        return "{x:" + this.x + ", y:" + this.y + ", z:" + this.z + "}";
    }

    private void giraIzquierdaNorte() {
        switch (direccion_vista) {
            case Tablero.ESTE:
                direccion_vista = Tablero.FONDO;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.FRENTE;
                break;
            case Tablero.FRENTE:
                direccion_vista = Tablero.ESTE;
                break;

        }

    }

    private void giraIzquierdaEste() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.FONDO;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.NORTE:
                direccion_vista = Tablero.FRENTE;
                break;

        }
    }

    private void giraIzquierdaSur() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.FRENTE;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.FONDO;
                break;

        }
    }

    private void giraIzquierdaOeste() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.NORTE:
                direccion_vista = Tablero.FONDO;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.FRENTE;
                break;

        }
    }

    private void giraIzquierdaFondo() {
        switch (direccion_vista) {
            case Tablero.NORTE:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.NORTE;
                break;

        }
    }

    private void giraIzquierdaFrente() {
        switch (direccion_vista) {
            case Tablero.NORTE:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.NORTE;
                break;

        }
    }

    //**** DERECHA
    private void giraDerechaNorte() {
        switch (direccion_vista) {
            case Tablero.ESTE:
                direccion_vista = Tablero.FRENTE;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.FONDO;
                break;
            case Tablero.FRENTE:
                direccion_vista = Tablero.OESTE;
                break;

        }

    }

    private void giraDerechaEste() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.FRENTE;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.NORTE:
                direccion_vista = Tablero.FONDO;
                break;

        }
    }

    private void giraDerechaSur() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.FONDO;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.FRENTE;
                break;

        }
    }

    private void giraDerechaOeste() {
        switch (direccion_vista) {
            case Tablero.FRENTE:
                direccion_vista = Tablero.SUR;
                break;
            case Tablero.NORTE:
                direccion_vista = Tablero.FRENTE;
                break;
            case Tablero.FONDO:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.FONDO;
                break;

        }
    }

    private void giraDerechaFondo() {
        switch (direccion_vista) {
            case Tablero.NORTE:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.SUR;
                break;

        }
    }

    private void giraDerechaFrente() {
        switch (direccion_vista) {
            case Tablero.NORTE:
                direccion_vista = Tablero.ESTE;
                break;
            case Tablero.OESTE:
                direccion_vista = Tablero.NORTE;
                break;
            case Tablero.SUR:
                direccion_vista = Tablero.OESTE;
                break;
            case Tablero.ESTE:
                direccion_vista = Tablero.SUR;
                break;

        }
    }

    //*** ARRIBA
    private void giraArribaNorte() {
        direccion_vista = Tablero.NORTE;
    }

    private void giraArribaEste() {
        direccion_vista = Tablero.ESTE;
    }

    private void giraArribaSur() {
        direccion_vista = Tablero.SUR;
    }

    private void giraArribaOeste() {
        direccion_vista = Tablero.OESTE;

    }

    private void giraArribaFondo() {
        direccion_vista = Tablero.FONDO;

    }

    private void giraArribaFrente() {
        direccion_vista = Tablero.FRENTE;

    }

    // **** ABAJO
    private void giraAbajoNorte() {
        direccion_vista = Tablero.SUR;
    }

    private void giraAbajoEste() {
        direccion_vista = Tablero.OESTE;
    }

    private void giraAbajoSur() {
        direccion_vista = Tablero.NORTE;
    }

    private void giraAbajoOeste() {
        direccion_vista = Tablero.ESTE;

    }

    private void giraAbajoFondo() {
        direccion_vista = Tablero.FRENTE;

    }

    private void giraAbajoFrente() {
        direccion_vista = Tablero.FONDO;

    }

    public void muestraTablero(SimpleApplication app) {
        for(int i=0; i< topey;i++){
            for(int j=0; j<topex;j++){
               for(int z=0; z<topez;z++){
                   if (tablero[i][j][z]==Tablero.PARED){
                     System.out.println("Coordenada:{x:" + j + ", y:" + i + ", z:" + z + "}");  
                     app.getRootNode().attachChild(makeCube("a Dragon",app,ColorRGBA.randomColor(), j*2, i*2 , z*2));
                   }
               } 
            }
        }
        
       
       // app.getRootNode().attachChild(makeFloor(app));
    }

    /**
     * A floor to show that the "shot" can go through several objects.
     */
    protected Geometry makeFloor(SimpleApplication app) {
        Box box = new Box(15, .2f, 15);
        Geometry floor = new Geometry("the Floor", box);
        floor.setLocalTranslation(0, -4, -5);
        Material mat1 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Gray);
        floor.setMaterial(mat1);
        return floor;
    }
    
     /** A cube object for target practice */
  protected Geometry makeCube(String name,SimpleApplication app,ColorRGBA color, float x, float y, float z) {
    Box box = new Box(1, 1, 1);
    Geometry cube = new Geometry(name, box);
    cube.setLocalTranslation(x, y, z);
    Material mat1 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", color);
    cube.setMaterial(mat1);
    return cube;
  }

}
