/*
 * cr@sh - Secret crush matcher for social networks
 * Copyright (C) 2020 TheDavidDelta
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.thedaviddelta.crash.api

import com.thedaviddelta.crash.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TwitterApi {
    @FormUrlEncoded
    @POST("/oauth/request_token")
    suspend fun requestToken(
        @Field("oauth_callback") callback: String
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("/oauth/access_token")
    suspend fun accessToken(
        @Field("oauth_token") token: String,
        @Field("oauth_verifier") verifier: String
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("/1.1/users/lookup.json")
    suspend fun getUsers(
        @Field("user_id") userIds: String
    ): Response<List<TwitterUser>>

    @GET("/1.1/{type}/ids.json")
    suspend fun getFollowersFollowing(
        @Path("type") type: TwitterContactType,
        @Query("cursor") cursor: Long
    ): Response<TwitterFollowersFollowing>
}
