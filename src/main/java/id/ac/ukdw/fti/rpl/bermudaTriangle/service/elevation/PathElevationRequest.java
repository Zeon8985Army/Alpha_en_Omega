/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ukdw.fti.rpl.bermudaTriangle.service.elevation;

import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.JavascriptObject;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.GMapObjectType;
import id.ac.ukdw.fti.rpl.bermudaTriangle.javascript.object.LatLong;

/**
 *
 * @author Geoff Capper
 */
public class PathElevationRequest extends JavascriptObject {

    public PathElevationRequest() {
        super(GMapObjectType.OBJECT);
    }

    public PathElevationRequest(LatLong[] path, int samples) {
        super(GMapObjectType.OBJECT);
        getJSObject().setMember("path", getJSObject().eval("[]"));
        for (int i = 0; i < path.length; i++) {
            getJSObject().eval(getVariableName() + ".path.push(" + path[i].getVariableName() + ")");
        }
        getJSObject().setMember("samples", samples);
    }

}
