package ie.gmit.sw;

/**
 * Metric handles the Positional Stability equation. 
 * There is a constructor that manages the in and out degrees and the class name. 
 * The implement methods for these variables are here.
 */

public class Metric {
    private int outDegree;
    private int inDegree;
    private String clsName;

    public Metric(int out, int in, String clsName){
        this.outDegree = out;
        this.inDegree = in;
        this.clsName = clsName;
    }//- End of MeasureCoupling()

    public Metric(){
        super();
    }//- End of MeasureCoupling()
    
    public int getOutDegree() {
        return outDegree;
    }//- End of getEfferentCoupling

    public void setOutDegree(int out) {
        this.outDegree = out;
    }//- End of setEfferentCoupling

    public int getInDegree() {
        return inDegree;
    }//- End of getAfferentCoupling

    public void setInDegree(int in) {
        this.inDegree = in;
    }//- End of setAfferentCoupling

    public String getClsName() {
        return clsName;
    }//- End of getClassName

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }//- End of setClassName

    public double getStability(){
        double positionalStability = 0.00;

        if(getOutDegree() > 0){
        	//- InDegree = Afferent Coupling (Ca), OutDegree = Efferent Coupling (Ce).
        	//- Positional Stability (I) is measured by: I = (Ce / (Ca + Ce))
            positionalStability = ((double)getOutDegree() / ((double)getInDegree() + (double)getOutDegree()));
        } else {
        	positionalStability = 0.00;
        }//- End of if/else

        return positionalStability;
    }//- End of getStability
}//- End of MesureCoupling