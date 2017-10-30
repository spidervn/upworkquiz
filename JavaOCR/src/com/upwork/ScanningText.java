package com.upwork;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ScanningText {

	public static void main(String[] args) {
		// Requirements
		// 		1/ Select one/many images which has text on it
		//		2/ Extract text on images
		//		3/ Multi-threading
		
		// System.out.println(Core.NATIVE_LIBRARY_NAME);
		
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
		System.out.println( "mat = " + mat.dump() );
	}
}
