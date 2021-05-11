package com.example.TheEconomist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {
    var link:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        if (intent!=null){
             tvTitle.text= intent.getStringExtra("title").toString()
             tvTextDes.text= intent.getStringExtra("description").toString()
            Glide.with(ivImage).load(intent.getStringExtra("pic")).into(ivImage)

        }


    }
}