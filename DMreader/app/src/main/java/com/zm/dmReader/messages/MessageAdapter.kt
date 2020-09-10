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

package com.zm.dmReader.messages

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zm.dmReader.Data
import com.zm.dmReader.R
import kotlin.collections.ArrayList

class MessageAdapter(private val conversation: ArrayList<Data.SingleMessage>): RecyclerView.Adapter<MessageView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageView {
        val layoutInf = LayoutInflater.from(parent.context)
        val customViewInflater = layoutInf.inflate(R.layout.message_row,parent,false)
        return MessageView(customViewInflater)
    }

    override fun getItemCount(): Int {
        return conversation.size
    }

    override fun onBindViewHolder(holder: MessageView, position: Int) {
        holder.bind(conversation[position].text,conversation[position].sender)
    }
}
class MessageView(mv: View): RecyclerView.ViewHolder(mv){
    private val messageView = mv.findViewById(R.id.mytext_row) as TextView
    fun bind(M:String?,Sender:String) {
        var message=M
        if(message.isNullOrEmpty())
            message="Post Or Message UnAvailable"
        if(Sender!= Data.getCurrentUser()) {
            messageView.setBackgroundResource(R.drawable.my_message_bubble)
            (messageView.layoutParams as LinearLayout.LayoutParams).gravity = Gravity.START
        }
            else{
            messageView.setBackgroundResource(R.drawable.other_message_bubble)
            (messageView.layoutParams as LinearLayout.LayoutParams).gravity = Gravity.END
        }
        messageView.text = message
    }
}
