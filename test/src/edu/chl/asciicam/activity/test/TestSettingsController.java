package edu.chl.asciicam.activity.test;

import android.graphics.Color;
import android.test.AndroidTestCase;
import edu.chl.asciicam.util.SettingsController;

//Copyright 2012 Robin Braaf, Ossian Madisson, Martin Thörnesson, Fredrik Hansson and Jonas Åström.
//
//This file is part of Asciicam.
//
//Asciicam is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//Asciicam is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with Asciicam.  If not, see <http://www.gnu.org/licenses/>.

public class TestSettingsController extends AndroidTestCase {
	
	SettingsController sc;
	
	@Override
	public void setUp() throws Exception{
		super.setUp();
		
		sc = new SettingsController();
	}
	
	/**
	 * Tests if indexToColorConstant works and return the correct color constant
	 */
	public void testIndexToColorConstant(){
		int testIndex = 4; //This is the index for the color RED
		int testColor; //This is used to test the color variable from indexToColorConstant
		sc.setBgColor(testIndex); //This method calls for the private method indexToColorConstant
		testColor = sc.getBgColor();
		assertEquals(testColor, Color.RED);
	}
	
	/**
	 * Tests if indexToFilter works and return the correct filterstring
	 */
	public void testIndexToFilter(){
		int testIndex = 1; //This is the index for the GrayscaleFilter
		String testFilter; //This is used to test the filter returned fom indexToFilter
		sc.setFilter(testIndex); //This method calls for the private method indexToColorFilter
		testFilter = sc.getFilter();
		assertEquals(testFilter, "GrayscaleFilter");
	}
}
