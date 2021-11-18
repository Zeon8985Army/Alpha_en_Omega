/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ukdw.fti.rpl.bermudaTriangle.service.elevation;

/**
 * Parties interested in receiving the results from a call to the
 * {@link ElevationService} must implement this interface. The results may be an
 * empty array if the status is anything other than ElevationStatus.OK
 *
 * @author Geoff Capper
 */
public interface ElevationServiceCallback {

    public void elevationsReceived(ElevationResult[] results, ElevationStatus status);

}
