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

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.zm.dmReader.peopleList.DMListActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {

    private val requestSelectFile = 120
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(
                Intent.createChooser(intent, "Select a File"),
                requestSelectFile
            )
        }
        about.setOnClickListener {
            val intent = Intent(this, PopupActivity::class.java)
            startActivity(intent)
        }
        howtouse.setOnClickListener {
            Data.open(
                this,
                getString(R.string.howTo)
            )
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestSelectFile && resultCode == RESULT_OK && data != null) {
            val filename = data.data
            if (filename != null)
                readFromIntent(filename)
        }
    }
    private fun readFromIntent(name: Uri) {
        val input = contentResolver.openInputStream(name)
        if (input != null) {
            val buff = input.readBytes().toString(Charsets.UTF_8)
            val jsonConverter = Gson().newBuilder().create()
            try {
                Data.jsonObj = jsonConverter.fromJson(buff, object : Serializable, TypeToken<ArrayList<Data.OneConversation>>() {}.type)
                if(!Data.jsonObj.isNullOrEmpty()) {
                    val intent = Intent(this, DMListActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: JsonSyntaxException) {
                Toast.makeText(this, "Syntax Error: Wrong File $e", Toast.LENGTH_LONG).show()
            }
        }
    }

}