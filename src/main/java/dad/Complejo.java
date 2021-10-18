package dad;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {

    private DoubleProperty real = new SimpleDoubleProperty();
    private DoubleProperty imaginario = new SimpleDoubleProperty();

    
    @Override
    public String toString() {

        return "(" + getReal() + ", " + getImaginario() + ")";
    }

    public DoubleProperty realProperty(){
        return this.real;
    }
    public double getReal(){
        return this.realProperty().get();
    }

    public void setReal(final double real) {
        this.realProperty().set(real);
    }

    public DoubleProperty imaginarioProperty(){
        return this.imaginario;
    }

    public double getImaginario(){
        return this.imaginarioProperty().get();
    }

    public void setImaginario(final double imaginario){
        this.imaginarioProperty().set(imaginario);
    }

    

}
