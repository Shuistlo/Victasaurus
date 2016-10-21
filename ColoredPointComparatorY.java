import java.util.Comparator;

public class ColoredPointComparatorY implements Comparator<ColoredPoint>{

	@Override
	public int compare(ColoredPoint p1, ColoredPoint p2) {
		if(p1.getMappedY() < p2.getMappedY()) {
			return -1;
		}
		if(p1.getMappedY() > p2.getMappedY()){
			return 1;
		}
		
		return 0;
	}

}
