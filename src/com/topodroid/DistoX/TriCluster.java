/* @file TriCluster.java
 *
 * @author marco corvi
 * @date nov 2016
 *
 * @brief TopoDroid centerline computation: cluster of triangles
 * --------------------------------------------------------
 *  Copyright This sowftare is distributed under GPL-3.0 or later
 *  See the file COPYING.
 * --------------------------------------------------------
 */
package com.topodroid.DistoX;

import java.util.ArrayList;

public class TriCluster
{
  ArrayList<TriShot> shots;
  ArrayList<String>  stations;

  TriCluster() 
  {
    shots = new ArrayList<TriShot>();
    stations = new ArrayList<String>();
  }

  int nrShots() { return shots.size(); }
  int nrStations() { return stations.size(); }

  void addTmpShot( TriShot ts )
  {
    shots.add( ts );
    ts.cluster = this;
  }

  void addStation( String st )
  {
    if ( ! containsStation( st ) ) stations.add( st );
  }

  boolean containsStation( String st )
  {
    if ( st == null ) return true;
    for ( String s : stations ) if ( st.equals(s) ) return true;
    return false;
  }
}

