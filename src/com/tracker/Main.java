package com.tracker;

import com.tracker.classes.Management;

public class Main {
	public static void main(String[] args) {
		
		Management m = new Management();
		
		do {
			m.input();
		} while(!m.isExit());
		
	}
}
