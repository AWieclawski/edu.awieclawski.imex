package edu.awieclawski.cmd;

import edu.awieclawski.services.FileOperator;

public class Main {

	private static FileOperator dtImp = new FileOperator();
	// mark resources directory as 'Use as Source Folder' in Eclipse to make
	// data.txt file available
	private static final String filePath = "data.txt";

	public static void main(String[] args) {
		// simple test of CSV file read & conversion
		dtImp.operateDataFileFromPath(filePath);

	}

}
