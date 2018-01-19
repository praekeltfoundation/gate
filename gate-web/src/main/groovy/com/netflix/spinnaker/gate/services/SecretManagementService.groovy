
package com.netflix.spinnaker.gate.services

import com.netflix.spinnaker.gate.services.internal.VaultService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SecretManagementService {

  @Autowired
  VaultService vaultService

  @Value('${services.vault.vaultToken}')
  private static String vaultToken

  List getVaultACLs() {
    vaultService.getVaultACLs(vaultToken)
  }

  List getRoles(String backend) {
    vaultService.getRoles(backend, vaultToken)
  }

  void updateGatekeeperPolicies(Map newPolicies) {
    vaultService.updateGatekeeperPolicies(vaultToken, newPolicies)
  }

  Map getGatekeeperPolicies() {
    vaultService.getGatekeeperPolicies(vaultToken)
  }
}
