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
import retrofit.http.LIST
import retrofit.http.PATCH
import retrofit.http.POST
import retrofit.http.PUT
import retrofit.http.Path
import retrofit.http.Query


interface VaultService {

  @Headers("X-Vault-Token: {vaultToken}")
  @LIST("/sys/acl/policies")
  List getVaultACLs(@Header("vaultToken") String vaultToken)

  @Headers("X-Vault-Token: {vaultToken}")
  @LIST("/v1/{backend}/roles")
  List getRoles(@Path("backend") String backend, @Header("vaultToken") String vaultToken)

  @Headers("X-Vault-Token: {vaultToken}")
  @POST("/v1/secret/gatekeeper")
  //Figure out a way to populate the vaultToken parameter from Gate's internal configs
  Response updateGatekeeperPolicies(@Header("vaultToken") String vaultToken, @Body Map newPolicies)

  @Headers("X-Vault-Token: {vaultToken}")
  @GET("/v1/secret/gatekeeper")
  Map getGatekeeperPolicies(@Header("vaultToken") String vaultToken)

}