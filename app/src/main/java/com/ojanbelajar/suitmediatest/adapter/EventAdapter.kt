package com.ojanbelajar.suitmediatest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.suitmediatest.R
import com.ojanbelajar.suitmediatest.data.remote.Event
import com.ojanbelajar.suitmediatest.databinding.ItemEventBinding
import com.ojanbelajar.suitmediatest.ui.EventGuestActivity
import com.ojanbelajar.suitmediatest.utils.SessionManagement
import org.jetbrains.anko.clearTask

class EventAdapter(private val context: Context) : RecyclerView.Adapter<EventAdapter.EventAdapterViewHolder>() {

    private var listData = ArrayList<Event>()


    fun setData(newListData: List<Event>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapterViewHolder =
        EventAdapterViewHolder(ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: EventAdapterViewHolder, position: Int) {
        val event = listData[position]
        val session = SessionManagement(context)
        holder.bind(event,context)
        holder.itemView.setOnClickListener {
            session.updateEvent(event.name)
            val intent = Intent(context, EventGuestActivity::class.java)
            intent.clearTask().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listData.size

    class EventAdapterViewHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(event: Event, context: Context){
            binding.tvNama.text = event.name
            binding.tvTanggal.text = event.date
            Glide.with(context).load(event.image).into(binding.ivEvent)
        }
    }
}