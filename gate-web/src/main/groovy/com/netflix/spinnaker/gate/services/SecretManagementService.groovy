
package com.netflix.spinnaker.gate.services

import com.netflix.spinnaker.gate.services.internal.VaultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@CompileStatic
@Component
class SecretManagementService {

  @Autowired
  VaultService vaultService

  Map getVaultACLs(String vaultToken) {
    return vaultService.getVaultACLs(vaultToken)
  }

  Map getRoles(String backend, String vaultToken) {
    return vaultService.getRoles(backend, vaultToken)
  }

  void updateGatekeeperPolicies(String vaultToken, Map newPolicies) {
    vaultService.updateGatekeeperPolicies(vaultToken, newPolicies)
  }

  Map getGatekeeperPolicies(String vaultToken) {
    return vaultService.getGatekeeperPolicies(vaultToken)
  }
}
