/*
* This file is part of DM-Reader.
*
* DM-Reader is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* DM-Reader is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package com.zm.dmReader.peopleList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.zm.dmReader.messages.MessagesActivity
import com.zm.dmReader.R

class DMListAdapter(private var usr:ArrayList<String>,private var index:ArrayList<Int>):RecyclerView.Adapter<CustomView>() {

    override fun getItemCount(): Int {
        return usr.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInf = LayoutInflater.from(parent.context)
        val customViewInflater = layoutInf.inflate(R.layout.direct_list_row,parent,false)
        return CustomView(customViewInflater)
    }
    override fun onBindViewHolder(holder: CustomView, position: Int) {
        holder.bind(usr[position],index[position])
    }
}
class CustomView(private val v:View): RecyclerView.ViewHolder(v){
    private val username = v.findViewById(R.id.usernameHolder) as Button
    fun bind(user:String,p:Int) {
        username.text = user
        username.setOnClickListener {
            val intent = Intent(v.context, MessagesActivity::class.java)
            intent.putExtra("selectedConversation",p)
            intent.putExtra("selectedUser",user)
            v.context.startActivity(intent)
        }
    }
}
