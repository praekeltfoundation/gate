package com.netflix.spinnaker.gate.services

import com.netflix.spinnaker.gate.services.internal.GatekeeperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import groovy.transform.CompileStatic

@CompileStatic
@Component
class SecretPolicyService {

  @Autowired
  GatekeeperService gatekeeperService

  void reloadPolicies() {
    gatekeeperService.reloadPolicies()
  }

}