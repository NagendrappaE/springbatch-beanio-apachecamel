/**
 * 
 */
package com.evolvus.fluxclaims.service;

import com.evolvus.fluxclaims.api.CBSInterfaceRequest;
import com.evolvus.fluxclaims.api.CBSInterfaceResponse;

/**
 * @author user
 *
 */
public interface ENBDCbsService {

	CBSInterfaceResponse processPayemntRequest(CBSInterfaceRequest cbsInterfaceRequest);
	
	public String paymentFileName(String ref1,String ref2);

}
