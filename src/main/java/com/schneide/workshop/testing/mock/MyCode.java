package com.schneide.workshop.testing.mock;

public class MyCode {

	private Ref_t ref;

	public MyCode(Ref_t ref) {
		super();
		this.ref = ref;
	}
	
	public boolean isValid() {
		return this.ref.isNotNull();
	}
}
