/** @file ImportParser.java
 *
 * @author marco corvi
 * @date mar 2015
 *
 * @brief TopoDroid import parser
 *
 * --------------------------------------------------------
 *  Copyright This sowftare is distributed under GPL-3.0 or later
 *  See the file COPYING.
 * ----------------------------------------------------------
 */
package com.topodroid.DistoX;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

// import android.util.Log;

public class ImportParser
{
  final static String EMPTY = "";
  public String mName = null;  // survey name
  public String mDate = null;  // survey date
  public String mTeam = "";
  public String mTitle = "";
  public float  mDeclination = 0.0f; // one-survey declination
  protected boolean mApplyDeclination = false;

  protected ArrayList< ParserShot > shots;   // centerline shots
  protected ArrayList< ParserShot > splays;  // splay shots

  public int getShotNumber()    { return shots.size(); }
  public int getSplayNumber()   { return splays.size(); }

  public ArrayList< ParserShot > getShots() { return shots; }
  public ArrayList< ParserShot > getSplays() { return splays; }

  public String initStation() 
  {
    for ( ParserShot sh : shots ) {
      if ( sh.from != null && sh.from.length() > 0 ) return sh.from;
    }
    return "0";
  }

  protected int mLineCnt;  // line counter

  Pattern pattern = Pattern.compile( "\\s+" );

  public ImportParser( boolean apply_declination ) throws ParserException
  {
    mDate = TopoDroidUtil.currentDate();
    shots  = new ArrayList< ParserShot >();
    splays = new ArrayList< ParserShot >();
    mApplyDeclination = apply_declination;
  }
  
  protected String nextLine( BufferedReader br ) throws IOException
  {
    String line = br.readLine();
    if ( line == null ) return null; // EOF
    line = line.trim();
    ++mLineCnt;
    StringBuilder ret = new StringBuilder();
    while ( line != null && line.endsWith( "\\" ) ) {
      ret.append( line.replace( '\\', ' ' ) ); // FIXME
      line = br.readLine();
      ++mLineCnt;
    }
    if ( line != null ) ret.append( line );
    return ret.toString();
  }

  protected String[] splitLine( String line )
  {
     return pattern.split(line); // line.split( "\\s+" );
  }
   
  /** read input file
   * @param filename name of the file to parse
   */
  protected void readFile( String filename ) throws ParserException
  {
    try {
      FileReader fr = new FileReader( filename );
      BufferedReader br = new BufferedReader( fr );
      readFile( br );
    } catch ( IOException e ) {
      TDLog.Error( "ERROR I/O " + e.toString() );
      throw new ParserException();
    }
    TDLog.Log( TDLog.LOG_THERION, "ImportParser shots "+ shots.size() +" splays "+ splays.size()  );
  }

  void readFile( BufferedReader br ) throws ParserException
  {
  }


}
