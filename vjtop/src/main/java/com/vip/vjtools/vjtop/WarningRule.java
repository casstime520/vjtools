package com.vip.vjtools.vjtop;

public class WarningRule {

	public DoubleWarning cpu = new DoubleWarning(50d, 70d);
	public DoubleWarning syscpu = new DoubleWarning(30d, 50d);
	public LongWarning swap = new LongWarning(1, 1);
	public LongWarning thread = new LongWarning();
	public LongWarning io = new LongWarning(50 * Utils.MB_SIZE, 100 * Utils.MB_SIZE);

	public LongWarning old = new LongWarning();
	public LongWarning codeCache = new LongWarning();
	public LongWarning perm = new LongWarning();

	public LongWarning ygcTime = new LongWarning();
	public LongWarning ygcAvgTime = new LongWarning(100, 200);
	public LongWarning fullgcCount = new LongWarning(1, 2);

	public void updateProcessor(int processors) {
		thread.yellow = processors <= 8 ? processors * 150 : Math.max(8 * 150, processors * 100);
		thread.red = processors <= 8 ? processors * 225 : Math.max(8 * 225, processors * 150);
	}

	public void updateInterval(int interval) {
		ygcTime.yellow = interval * 1000 * 5 / 100;
		ygcTime.red = interval * 1000 * 10 / 100;
	}

	public void updateOld(long max) {
		if (max != -1) {
			old.yellow = max * 85 / 100;
			old.red = max * 95 / 100;
		}
	}

	public void updatePerm(long max) {
		if (max != -1) {
			perm.yellow = max * 85 / 100;
			perm.red = max * 95 / 100;
		}
	}

	public void updateCodeCache(long max) {
		if (max != -1) {
			codeCache.yellow = max * 85 / 100;
			codeCache.red = max * 95 / 100;
		}
	}

	public static class DoubleWarning {
		public double yellow;
		public double red;

		public DoubleWarning() {
			yellow = Double.MAX_VALUE;
			red = Double.MAX_VALUE;
		}

		public DoubleWarning(double yellow, double red) {
			this.yellow = yellow;
			this.red = red;
		}
	}

	public static class LongWarning {
		public long yellow;
		public long red;

		public LongWarning() {
			yellow = Long.MAX_VALUE;
			red = Long.MAX_VALUE;
		}

		public LongWarning(long yellow, long red) {
			this.yellow = yellow;
			this.red = red;
		}
	}
}