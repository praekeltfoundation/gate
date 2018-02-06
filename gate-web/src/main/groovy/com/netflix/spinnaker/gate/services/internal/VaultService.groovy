/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.gate.services.internal

import retrofit.client.Response
import retrofit.http.Body
import retrofit.http.DELETE
import retrofit.http.GET
import retrofit.http.Headers
import retrofit.http.Header
import retrofit.http.PATCH
import retrofit.http.POST
import retrofit.http.PUT
import retrofit.http.Path
import retrofit.http.Query


interface VaultService {

  @GET("/sys/acl/policies")
  Map getVaultACLs(@Header("X-Vault-Token") String vaultToken)

  @GET("/v1/{backend}/roles?list=true")
  Map getRoles(@Path("backend") String backend, @Header("X-Vault-Token") String vaultToken)

  @POST("/v1/secret/gatekeeper")
  void updateGatekeeperPolicies(@Header("X-Vault-Token") String vaultToken, @Body Map newPolicies)

  @GET("/v1/secret/gatekeeper")
  Map getGatekeeperPolicies(@Header("X-Vault-Token") String vaultToken)

}