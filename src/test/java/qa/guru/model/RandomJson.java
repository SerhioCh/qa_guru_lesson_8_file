package qa.guru.model;
import java.util.List;

public class RandomJson {
    public String car;
    public int mileage;
    public String color;
    public List<String> construction;
    public Speed speed;

    public static class Speed {
        public int maximum;
        public int rotation;
    }
    }

