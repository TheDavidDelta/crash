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

package com.thedaviddelta.crash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thedaviddelta.crash.R
import com.thedaviddelta.crash.model.User
import kotlinx.android.synthetic.main.listitem_main.view.*

class UserAdapter(
    private var list: List<User> = listOf(),
    private val listener: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var backup: List<User> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.listitem_main, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setItems(items: List<User>) {
        list = items
        backup = items
        notifyDataSetChanged()
    }

    fun filter(text: CharSequence?) {
        list = if (text.isNullOrBlank())
            backup
        else
            backup.filter {
                it.fullName.contains(text, true)
                || it.username.contains(text, true)
            }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: User) = with(view) {
            textview_listitem_main_fullname.text = item.fullName
            textview_listitem_main_username.text = item.username

            setOnClickListener { listener(item) }
        }
    }
}
