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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.dmReader.Data
import com.zm.dmReader.R
import kotlinx.android.synthetic.main.activity_messages.*

class MessagesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        val selectedConversation = intent.getIntExtra("selectedConversation", 0)
        val selectedUser = intent.getStringExtra("selectedUser")
        val messages = Data.jsonObj[selectedConversation].conversation
        messages.reverse()
        MessageUSER.text = selectedUser
        messageRecycle.layoutManager = LinearLayoutManager(this)
        messageRecycle.adapter =
            MessageAdapter(messages)
    }
}
