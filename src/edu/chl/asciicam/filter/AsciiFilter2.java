package edu.chl.asciicam.filter;

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

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Style;

/**
 * A filter which creates a bitmap consisting of ascii symbols from
 * a input bitmap. 
 * @author Braaf
 *
 */
public class AsciiFilter2 implements FilterInterface {
	
	
	
	/**
	 * Default constructor, sets default options.
	 */
	public AsciiFilter2(){
		
	}
	
	public Bitmap convert(Bitmap bm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class Options {
		
		private Options(){
			
		}
	}

}	