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

import com.netflix.spinnaker.gate.services.internal.VaultService
import com.netflix.spinnaker.gate.services.internal.ClouddriverServiceSelector
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vault")
class VaultController {

  @Autowired
  VaultService vaultService

  @ApiOperation(value = "Retrieve a list of roles for a secret backend.")
  @RequestMapping(value = "/{backend}/roles", method = RequestMethod.GET)
  List<Map> roles(@PathVariable String backend,
                  @RequestHeader(value = "X-Vault-Token", required = true) String vaultToken) {
    vaultService.getRoles(backend)
  }
}