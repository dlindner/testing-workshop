package com.schneide.workshop.testing.mock;

public class Ref_t {
	private int dn;
	private int sn;
	private int versionsn;
	
	public Ref_t(int dn, int sn, int versionsn) {
		super();
		this.dn = dn;
		this.sn = sn;
		this.versionsn = versionsn;
	}
	
	public int getDn() {
		return dn;
	}
	
	public int getSn() {
		return sn;
	}
	
	public int getVersionsn() {
		return versionsn;
	}
	
	public boolean isNull() {
		return (dn == 127 && sn == 0);
	}
	
	public boolean isNotNull() {
		return !isNull();
	}
}
