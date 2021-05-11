package com.example.TheEconomist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_today_description.*

class TodayDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_description)
        if (intent !=null){
            Glide.with(item_img_today_des).load(intent.getStringExtra("pic")).into(item_img_today_des)
            tv_author_today_des.text=intent.getStringExtra("author")
            tvTitle_today_des.text=intent.getStringExtra("title")
            tv_Description_today_des.text=intent.getStringExtra("description")
            today_brief_des.text=intent.getStringExtra("briefDescription")
        }
    }
}