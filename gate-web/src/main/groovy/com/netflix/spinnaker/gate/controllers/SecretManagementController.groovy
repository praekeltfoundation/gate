/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.gate.controllers

import com.netflix.spinnaker.gate.services.SecretManagementService
import com.netflix.spinnaker.gate.services.SecretPolicyService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Value
import groovy.transform.CompileStatic
import retrofit.RetrofitError
import groovy.util.logging.Slf4j

import static com.netflix.spinnaker.gate.retrofit.UpstreamBadRequest.classifyError

@Slf4j
@CompileStatic
@RestController
@RequestMapping("/secrets")
class SecretManagementController {

  @Autowired
  SecretManagementService secretManagementService

  @Autowired
  SecretPolicyService secretPolicyService

  private final String vaultToken

  SecretManagementController(@Value('${services.vault.vaultToken}') String vaultToken) {
    this.vaultToken = vaultToken
  }

  @ApiOperation(value = "Retrieve a list Vault ACLs for Spinnaker applications")
  @RequestMapping(value = "/vaultpolicies", method = RequestMethod.GET)
  Map getVaultACLs() {
    return secretManagementService.getVaultACLs(vaultToken)
  }

  @ApiOperation(value = "Retrieve a list of roles for a secret backend")
  @RequestMapping(value = "/{backend}/roles", method = RequestMethod.GET)
  Map getRoles(@PathVariable String backend) {
     try {
        return secretManagementService.getRoles(backend, vaultToken)
      } catch (RetrofitError e) {
        throw classifyError(e)
      } 
  }

  @ApiOperation(value = "Retrieve Gatekeeper policies from Vault")
  @RequestMapping(value = "/gatekeeper/policies", method = RequestMethod.GET)
  Map getGatekeeperPolicies() {
    try {
      return secretManagementService.getGatekeeperPolicies(vaultToken)
    } catch (RetrofitError e) {
        throw classifyError(e)
      } 
  }

  @ApiOperation(value = "Update Gatekeeper policies")
  @RequestMapping(value = "/gatekeeper/policies", method = RequestMethod.POST)
  Response updateGatekeeperPolicies(@RequestBody Map newPolicies) {
    try {
      secretManagementService.updateGatekeeperPolicies(vaultToken, newPolicies)
    } catch (RetrofitError e) {
      throw classifyError(e)
    }
  }

  @ApiOperation(value = "Tell Gatekeeper to refresh its policies from Vault")
  @RequestMapping(value = "/gatekeeper/policies/reload", method = RequestMethod.POST)
  Map reloadGatekeeperPolicies() {
    try {
    return secretPolicyService.reloadPolicies()
    } catch (RetrofitError e) {
            throw classifyError(e)
    }
  }

}