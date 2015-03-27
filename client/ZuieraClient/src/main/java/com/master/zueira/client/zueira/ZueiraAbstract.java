package com.master.zueira.client.zueira;

public abstract class ZueiraAbstract implements Zueira {

	public int[] parse(final String value, final int defs[]) {
		if (value != null) {
			final String values[] = value.split(" ");
			if (values.length == defs.length) {
				final int newDefs[] = new int[defs.length];
				for (int i = 0; i < values.length; i++) {
					try {
						newDefs[i] = Integer.valueOf(values[i].trim());
					} catch (final Exception e) {
						return defs;
					}
				}
				return newDefs;
			}
		}
		return defs;
	}

}
