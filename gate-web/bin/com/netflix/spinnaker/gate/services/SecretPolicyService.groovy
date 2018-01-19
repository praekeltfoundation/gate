package com.netflix.spinnaker.gate.services

import com.netflix.spinnaker.gate.services.internal.VaultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SecretPolicyService {

  @Autowired
  GatekeeperService gatekeeperService

  void reloadPolicies() {
    gatekeeperService.reloadPolicies()
  }

}