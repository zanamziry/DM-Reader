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
package com.zm.dmReader

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.ArrayList

object Data {

    data class OneConversation(val participants: ArrayList<String>, val conversation: ArrayList<SingleMessage>)
    data class SingleMessage(val sender:String,val created_at:String,val text:String)
    //you can add more data classes, for example a class for media messages: Media_Links,Post_Link
    //check 'messages.json' file for more structures

    private var clearNamesList = ArrayList<String>()
    private var currentUser = ""
    var jsonObj = ArrayList<OneConversation>()

    fun getCurrentUser(): String {
        return if (currentUser.isEmpty()) {
            filteredNames()
            this.currentUser
        } else this.currentUser
    }

    fun filteredNames(): ArrayList<String> {
        if (clearNamesList.isNullOrEmpty()) {
            val users = ArrayList<String>()
            var countGroups = 0
            for (i in jsonObj.indices) {
                if (jsonObj[i].participants.size > 2) {//if it finds a conversation with more than 2 persons, name it group
                    users.add("group$i")
                    countGroups++
                    continue
                }
                for (j in jsonObj[i].participants) {
                    users.add(j)
                }
            }
            for (i in users) { //Find the username of the Current user
                var count = 0
                for (j in users) {
                    if (j == i)
                        count++
                    if (count == jsonObj.size - countGroups)
                        this.currentUser = i
                }
            }
            val removeNames =
                ArrayList<String>()
            //remove all duplicated user names that belong to the user
            for (i in users)
                if (i == this.currentUser)
                    removeNames.add(i)
            users.removeAll(removeNames)
            clearNamesList = users
            return clearNamesList
        } else return clearNamesList
    }
    fun open(context: Context, link:String) {
        val uris = Uri.parse(link)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        context.startActivity(intents)
    }
}