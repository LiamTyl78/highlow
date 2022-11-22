//Liam Tyler
public class RandomInteger {
    private int min;
    private int max;
    private int range;

    public RandomInteger() {
        this.min = 0;
        this.max = 100;
        range = max - min + 1;
    }

    public RandomInteger(int min, int max) {
        this.min = min;
        this.max = max;
        range = max - min + 1;
    }

    public void SetMax(int max) {
        this.max = max;
        range = max - min +  1;
    }

    public void SetMin(int min) {
        this.min = min;
        range = max - min +  1;
    }

    public int GetMin() {
        return min;
    }

    public int GetMax() {
        return max;
    }
    int Generate(){
        return (int)(range *  Math.random()) + min;
    }
}
