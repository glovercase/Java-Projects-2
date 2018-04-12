// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 3
 * Name: Casey Glover
 * Usercode: 
 * ID: 300280613
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** Program to render a molecule on the graphics pane from different possible
 *  perspectives.
 *  A molecule consists of a collection of atoms.
 *  Each atom has a type (eg, Carbon, or Hydrogen, or Oxygen, ..),
 *  and a three dimensional position in the molecule (x, y, z).
 *
 *  Each molecule is described in a file by a list of atoms and their positions.
 *  The molecule is rendered by drawing a colored circle for each atom.
 *  The size and color of each atom is determined by the type of the atom.
 * 
 *  To make sure that the nearest atoms appear in front of the furthest atoms,
 *  the atoms must be rendered in order from the furthest away to the nearest.
 *  Each viewing perspective imposes a different ordering on the atoms.
 *
 *  The description of the size and color for rendering the different types of
 *  atoms is stored in the file "element-table.txt" which should be read and
 *  stored in a Map.  When an atom is rendered, the type should be looked up in
 *  the map to find the size and color to pass to the atom's render() method
 * 
 *  A molecule can be rendered from different perspectives, and the program
 *  provides four buttons to control the perspective of the rendering:
 *   "Front" renders the molecule from the front (perspective = 0 degrees)
 *   "Back" renders the molecule from the back (perspective = 180 degrees)
 *   "Left" renders the molecule from the left (perspective = -90 degrees)
 *   "Right" renders the molecule from the right (perspective = 90 degrees)
 *   "PanLeft" decreases the perspective of the rendering by 5 degrees,
 *   "PanRight" increases the perspective of the rendering by 5 degrees,
 */

public class MoleculeRenderer implements UIButtonListener, UISliderListener {

    // Fields
    // Map containing the size and color of each atom type.
    private Map<String,Element> elementTable = new HashMap<String, Element>(); 

    // The collection of the atoms in the current molecule
    private List<Atom> molecule = new ArrayList<Atom>();  

    private double currentAngle = 0.0;    //current viewing angle (in degrees)
    private double vertAngle = 0.0; //vertical viewing angle

    private double panStep = 5.0;
    private double zoom = 5.0; //initial zoom 

    // Constructor:
    /** Set up the Graphical User Interface and read the file of element data of
     *	each possible type of atom into a Map: where the type is the key
     *  and an ElementInfo object is the value (containing size and color).
     */
    public MoleculeRenderer() {
        UI.addButton("Read", this);
        UI.addButton("FromFront", this);
        UI.addButton("FromBack", this);
        UI.addButton("FromLeft", this);
        UI.addButton("FromRight",this);
        UI.addButton("PanLeft", this);
        UI.addButton("PanRight", this);
        UI.addButton("TiltUp", this);
        UI.addButton("TiltDown", this);
        UI.addSlider("Zoom",1,10, this); //range of slider between 1 and ten
        /*# YOUR CODE HERE */
        readElementTable();    //  Read the element table first
    }

    /** Respond to button presses.
     *  Most of the presses will set the currentAngle and sort the list of molecules
     *  using the appropriate comparator
     */
    public void buttonPerformed(String button) {
        if (button.equals("Read")) {
            currentAngle = 0;
             /*# some reason the compiler has issues with this line if you use the debugger here and step through it will then load the file */
            String file = UIFileChooser.open(); 
            
            readMoleculeFile(file);
            Collections.sort(molecule, new BackToFrontComparator());
        }
        else if (button.equals("FromFront")) {  // set the angle and sort from back to front
            currentAngle = 0;
            Collections.sort(molecule, new BackToFrontComparator());
        }else if (button.equals("FromBack")){
            currentAngle = 180;
            Collections.sort(molecule, new FrontToBackComparator());
        }else if (button.equals("FromLeft")){
            currentAngle = 90;
            Collections.sort(molecule, new LeftToRightComparator());
        }else if (button.equals("FromRight")){
            currentAngle = 270;
            Collections.sort(molecule, new RightToLeftComparator());
        }else if(button.equals("FromTop")){
            currentAngle =0;
            //Collections.sort(molecue
        }else if(button.equals("PanLeft")){
            currentAngle -= 5; //decrements 
            Collections.sort(molecule, new PanComparator());
        }else if(button.equals("PanRight")){
            currentAngle += 5; //increments
            Collections.sort(molecule, new PanComparator());
        }else if (button.equals("TiltUp")){
            vertAngle -= 5;
            Collections.sort(molecule, new TiltComparator());
        }else if (button.equals("TiltDown")){
            vertAngle += 5;
            Collections.sort(molecule, new TiltComparator());
        }
        /*# YOUR CODE HERE */

        // render the molecule according to the current ordering
        render(zoom);
        
    }
    
    public void sliderPerformed(String slider, double z){
        if(slider.equals("Zoom")){
            this.zoom = z; //changes the zoom size 
            render(z); 
        }
        
    }

    /** Reads the molecule data from a file containing one line for each atom in
     *  the molecule.
     *  Each line contains an atom type and the 3D coordinates of the atom.
     *  For each atom, it constructs an Atom object,
     *   and adds it to the List of Atoms in the molecule.
     *  To get the color and the size of each atom, it has to look up the type
     *   of the atom in the Map of elements.
     */
    public void readMoleculeFile(String fname) {
        try {
           
            Scanner scan = new Scanner(new File(fname)); //scans the file
            while(scan.hasNext()){
                String elementType = scan.next();
                int x = scan.nextInt();
                int y = scan.nextInt();
                int z = scan.nextInt();
                Color col = elementTable.get(elementType).color; //grabs the color
                double radius = elementTable.get(elementType).radius;
                Atom atom = new Atom(x,y,z,col, radius); //new atom
                molecule.add(atom); //add it to the molecule list
            }

        }catch(IOException ex) {
            UI.println("Reading molecule file " + fname + " failed");
        }
    }

    /** (Completion) Reads a file containing radius and color information about each type of
     *	atom and stores the info in a Map, using the atom type as a key
     */
    private void readElementTable() {
        UI.println("Reading the element table file ...");
        try {
            /*# YOUR CODE HERE */
            Scanner scan = new Scanner(new File("element-table.txt"));
            while(scan.hasNext()){

                String elementType = scan.next();
                int radius = scan.nextInt();
                int red = scan.nextInt();
                int green = scan.nextInt();
                int blue = scan.nextInt();
                Color col = new Color(red, green, blue);
                Element e = new Element(elementType, radius, col); //new element
                elementTable.put(elementType, e); //adds it to the map

            }

        }
        catch (IOException ex) {
            UI.println("Reading element table file FAILED");
        }
    }

    /** Render the molecule, according the the current ordering of Atoms in the List.
     *  The Atom's render() method needs the current perspective angle 
     */
    public void render(double zoom) {
        UI.clearGraphics();
        for(Atom atom : molecule) {
            atom.render(currentAngle, vertAngle, zoom);
        }
        UI.repaintGraphics();
    }

    // Private comparator classes
    // You will need a comparator class for each different direction
    // used in the buttonPerformed method.
    //
    // Each comparator class should be a Comparator of Atoms, and will define
    // a compare method that compares two atoms.
    // Each comparator should have a compare method.
    // Most of the comparators do not need an explicit constructor and have no fields.
    // However, the comparator for the pan methods may need a field and a constructor

    /** Comparator that will order atoms from back to front */
    private class BackToFrontComparator implements Comparator<Atom> {
        /**
         * Uses the z coordinates of the two atoms
         * larger z means towards the back,
         * smaller z means towards the front
         * Returns
         *  negative if atom1 is more to the back than atom2, (
         *  0 if they are in the same plane,
         *  positive if atom1 is more to the front than atom2.
         */
        /*# YOUR CODE HERE */
        //sorts the atoms from back to front
        public int compare(Atom a, Atom b){
            if(a.getZ() < b.getZ()){
                return -1;
            }else if (a.getZ() > b.getZ()){
                return 1;
            }else{
                return 0;
            }

        }

    }

    private class FrontToBackComparator implements Comparator<Atom>{

        public int compare(Atom a, Atom b){
            if(a.getZ() > b.getZ()){
                return -1;
            }else if(a.getZ() < b.getZ()){
                return 1;
            }else{
                return 0;
            }

        }
    }

    private class LeftToRightComparator implements Comparator<Atom>{
        public int compare(Atom a, Atom b){
            if(a.getX() < b.getX()){
                return -1;
            }else if(a.getX() > b.getX()){
                return 1;
            }else{
                return 0;
            }

        }
    }

    private class RightToLeftComparator implements Comparator<Atom>{
        public int compare(Atom a, Atom b){
            if(a.getX() > b.getX()){
                return -1;
            }else if(a.getX() < b.getX()){
                return 1;
            }else{
                return 0;
            }

        }

    }

    private class PanComparator implements Comparator<Atom>{
        public int compare (Atom a, Atom b){
            return a.further(b, currentAngle);
        }
    }

    private class TiltComparator implements Comparator<Atom>{
        public int compare(Atom a, Atom b){
            return a.tilt(b, currentAngle);
        }
    }

    public static void main(String args[]) {
        new MoleculeRenderer();
    }
}
