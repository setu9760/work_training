package com.desai.common.initial;


public class OutputHelper {

	IOutputGenerator iOutputGenerator;

	public OutputHelper(IOutputGenerator iOutputGenerator) {
		this.iOutputGenerator = iOutputGenerator;
	}

	public void setiOutputGenerator(IOutputGenerator iOutputGenerator) {
		this.iOutputGenerator = iOutputGenerator;
	}

	public IOutputGenerator getiOutputGenerator() {
		return iOutputGenerator;
	}
}
