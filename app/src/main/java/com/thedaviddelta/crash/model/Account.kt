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

import java.io.Serializable

/**
 * Interface wrapping local Account models ([TwitterAccount], [MastodonAccount])
 *
 * @property id Unique identifier
 * @property username Unique but changeable public alias
 * @property fullName Public name
 * @property avatarUrl Link to the avatar
 * @property bannerUrl Link to the banner
 */
interface Account : Serializable {
    val id: Long
    var username: String
    var fullName: String
    var avatarUrl: String
    var bannerUrl: String?
}
