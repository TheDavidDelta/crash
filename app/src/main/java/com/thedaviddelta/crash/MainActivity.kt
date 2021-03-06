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

package com.thedaviddelta.crash

import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.appbar.MaterialToolbar
import com.thedaviddelta.crash.util.SnackbarBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_user.*

/**
 * The activity of the [Single Activity Application](https://developer.android.com/guide/navigation)
 */
class MainActivity : AppCompatActivity() {

    companion object {
        /** Time in milliseconds between two presses to exit */
        private const val doubleBackTime = 2000
    }

    /** Checks if user have pressed back twice in [doubleBackTime] */
    private var doubleBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        when(val frag = nav_host_fragment.childFragmentManager.fragments.first()) {
            is LoginFragment -> {
                frag.toolbar_login.navigationView?.callOnClick()
            }
            is MainFragment -> {
                if (doubleBack)
                    return finish()
                doubleBack = true

                SnackbarBuilder(frag.requireView())
                    .showing(R.string.main_quit_message)
                    .during(doubleBackTime)
                    .tinted(R.color.red300)
                    .centered()
                    .buildAndShow()

                Handler().postDelayed({
                    doubleBack = false
                }, doubleBackTime.toLong())
            }
            is UserFragment -> {
                frag.toolbar_user.navigationView?.callOnClick()
            }
        }
    }

    /**
     * [ImageButton] of the toolbar [navigation icon][MaterialToolbar.getNavigationIcon], or `null` if not found
     *
     * @receiver [toolbar_login], [toolbar_user]
     */
    private val MaterialToolbar.navigationView: ImageButton?
        get() {
            return children.firstOrNull {
                it is ImageButton && it.drawable == navigationIcon
            } as? ImageButton
        }
}
