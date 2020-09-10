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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.dmReader.R
import com.zm.dmReader.Data
import kotlinx.android.synthetic.main.activity_dmlist.*
import java.util.*
import kotlin.collections.ArrayList

class DMListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dmlist)
        user_username.text = Data.getCurrentUser()
        val users = Data.filteredNames()
        val userIndex = ArrayList<Int>()
        for(i in users.indices)
            userIndex.add(i)
        recycleID.layoutManager = LinearLayoutManager(this)
        recycleID.adapter =
            DMListAdapter(users, userIndex)
        search_box.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchIndex = ArrayList<Int>()
                var searchedUsers = ArrayList<String>()
                if(s.isNullOrEmpty()) {
                    searchedUsers = users
                    for(i in users.indices)
                        searchIndex.add(i)
                }
                else
                    for(i in users.indices)
                        if(users[i].toLowerCase(Locale.US).contains(s.toString().toLowerCase(Locale.US))) {
                            searchedUsers.add(users[i])
                            searchIndex.add(i)
                        }
                recycleID.adapter = DMListAdapter(
                    searchedUsers,
                    searchIndex
                )
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}
