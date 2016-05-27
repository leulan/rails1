package calgps;

public class Gauss {
	public double Gaus(Float x, Float e, Float v) {
		double P1;
		P1 = (1 / Math.sqrt(2 * Math.PI * v))
				* Math.exp((-(x - e) * (x - e)) / (2 * v));
	
		return P1;
	
	}
	public double Mean(Float sum, int count) {
		return (sum / count);
	}
	
	public double Varian(float e12, float v12, int count) {
		v12 = (v12 / count);
		return (v12 - e12 * e12);
	}
}
