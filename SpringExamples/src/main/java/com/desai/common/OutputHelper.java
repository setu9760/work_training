package com.desai.common;

import com.desai.common.interfaces.IOutputGenerator;

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
