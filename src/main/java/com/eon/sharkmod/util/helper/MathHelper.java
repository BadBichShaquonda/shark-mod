package com.eon.sharkmod.util.helper;

public class MathHelper {
	private static final double FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
	private static final double[] ASINE_TAB = new double[257];
	private static final double[] COS_TAB = new double[257];

	public static double atan2(double dPosZ, double dPosX) {
		double dPosZXSquared = dPosX * dPosX + dPosZ * dPosZ;
		if (Double.isNaN(dPosZXSquared)) {
			return Double.NaN;
		} else {
			boolean flag = dPosZ < 0.0D;
			if (flag) {
				dPosZ = -dPosZ;
			}

			boolean flag1 = dPosX < 0.0D;
			if (flag1) {
				dPosX = -dPosX;
			}

			// dPosZ and dPosX are now positive

			// Switch X and Z IF Z is larger than X
			boolean flag2 = dPosZ > dPosX;
			if (flag2) {
				double d1 = dPosX;
				dPosX = dPosZ;
				dPosZ = d1;
			}

			// X is larger than Z and they're both positive

			double smallerThanOne = fastInvSqrt(dPosZXSquared);
			dPosX = dPosX * smallerThanOne;
			dPosZ = dPosZ * smallerThanOne;
			
			//dPosX = 0.9075
			//dPosZ = 0.4199
			
			double d2 = FRAC_BIAS + dPosZ;
			int i = (int) Double.doubleToRawLongBits(d2);
			double d3 = ASINE_TAB[i];
			double d4 = COS_TAB[i];
			double d5 = d2 - FRAC_BIAS;
			double d6 = dPosZ * d4 - dPosX * d5;
			double d7 = (6.0D + d6 * d6) * d6 * 0.16666666666666666D;
			double d8 = d3 + d7;
			if (flag2) {
				d8 = (Math.PI / 2D) - d8;
			}

			if (flag1) {
				d8 = Math.PI - d8;
			}

			if (flag) {
				d8 = -d8;
			}

			return d8;
		}
	}

	/**
	 * Computes 1/sqrt(n) using
	 * <a href="https://en.wikipedia.org/wiki/Fast_inverse_square_root">the fast
	 * inverse square root</a> with a constant of 0x5FE6EB50C7B537AA.
	 */
	public static double fastInvSqrt(double number) {
		double d0 = 0.5D * number;
		long i = Double.doubleToRawLongBits(number);
		i = 6910469410427058090L - (i >> 1);
		number = Double.longBitsToDouble(i);
		number = number * (1.5D - d0 * number * number);
		return number;
	}
}
