package template.models;

public class Coin {

    private final float diameter;
    private final float weight;
    private final float thickness;
    private final float value;

    public Coin(float diameter, float weight, float thickness, float value) {
        this.diameter = diameter;
        this.weight = weight;
        this.thickness = thickness;
        this.value = value;
    }

    public float getDiameter() {
        return diameter;
    }

    public float getWeight() {
        return weight;
    }

    public float getThickness() {
        return thickness;
    }

    public float getValue() {
        return value;
    }
}
