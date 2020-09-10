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

import android.os.Bundle
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.popup.*

class PopupActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup)
         val layout = findViewById<ConstraintLayout>(R.id.popup)
        layout.outlineProvider = ViewOutlineProvider.BACKGROUND
        layout.clipToOutline = true
        popclose.setOnClickListener { finish() }
        myEmail.setOnClickListener{Data.open(this,"mailto:${getString(R.string.email)}")}
        myNumber.setOnClickListener {Data.open(this,"tel:${getString(R.string.devNum)}") }
        myTwitter.setOnClickListener {Data.open(this, getString(R.string.twitter)) }
        myInstagram.setOnClickListener {Data.open(this, getString(R.string.Instagram)) }
        open_source_license.setOnClickListener { Data.open(this, getString(R.string.open_source_licenses_link)) }
        source_code.setOnClickListener { Data.open(this,getString(R.string.source_code)) }
    }
}