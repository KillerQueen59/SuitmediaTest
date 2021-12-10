package com.ojanbelajar.suitmediatest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.ojanbelajar.suitmediatest.R
import com.ojanbelajar.suitmediatest.data.remote.Event
import com.ojanbelajar.suitmediatest.databinding.HorizontalItemEventBinding
import com.ojanbelajar.suitmediatest.databinding.ItemEventBinding
import com.ojanbelajar.suitmediatest.ui.EventGuestActivity
import com.ojanbelajar.suitmediatest.utils.SessionManagement
import kotlinx.android.synthetic.main.horizontal_item_event.view.*
import org.jetbrains.anko.clearTask

class SwipeEventAdapter(private val context: Context, private val data: ArrayList<Event>) : PagerAdapter(){

    override fun getCount(): Int = data.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.horizontal_item_event,container,false)
        val session = SessionManagement(context)
        val event = data[position]
        view.tv_name.text = event.name
        Glide.with(context).load(event.image).into(view.iv_event)
        view.setOnClickListener {
            session.updateEvent(event.name)
            val intent = Intent(context, EventGuestActivity::class.java)
            intent.clearTask().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}

