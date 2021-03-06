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

package com.thedaviddelta.crash.model

import android.net.Uri
import com.google.gson.annotations.SerializedName

/**
 * Model for [Mastodon's Account entity](https://docs.joinmastodon.org/entities/account/)
 *
 * @property domain Instance location
 */
class MastodonUser(
    @SerializedName("id") override val id: Long,
    @SerializedName("username") override val username: String,
    @SerializedName("display_name") override val fullName: String,
    @SerializedName("avatar_static") override val avatarUrl: String,
    @SerializedName("header_static") override val bannerUrl: String?,
    @SerializedName("url") private val url: String
) : User {
    val domain
        get() = Uri.parse(url).host!!

    override lateinit var crush: CrushType

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other !is MastodonUser)
            return false
        return this.id == other.id
            && this.domain == other.domain
    }

    override fun hashCode(): Int = 31 * id.hashCode() + domain.hashCode()
}
