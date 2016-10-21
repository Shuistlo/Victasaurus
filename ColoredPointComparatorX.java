import java.util.Comparator;

public class ColoredPointComparatorX implements Comparator<ColoredPoint>{

	@Override
	public int compare(ColoredPoint p1, ColoredPoint p2) {
		if(p1.getMappedX() < p2.getMappedX()) {
			return -1;
		}
		if(p1.getMappedX() > p2.getMappedX()){
			return 1;
		}
		
		return 0;
	}

}
