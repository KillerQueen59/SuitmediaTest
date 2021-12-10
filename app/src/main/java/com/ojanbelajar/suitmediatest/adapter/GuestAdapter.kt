package com.ojanbelajar.suitmediatest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.suitmediatest.R
import com.ojanbelajar.suitmediatest.data.remote.Event
import com.ojanbelajar.suitmediatest.data.remote.Guest
import com.ojanbelajar.suitmediatest.databinding.ItemEventBinding
import com.ojanbelajar.suitmediatest.databinding.ItemGuestBinding
import com.ojanbelajar.suitmediatest.ui.EventGuestActivity
import com.ojanbelajar.suitmediatest.utils.SessionManagement
import org.jetbrains.anko.alert
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GuestAdapter(private val context: Context) : RecyclerView.Adapter<GuestAdapter.GuestAdapterViewHolder>() {

    private var listData = ArrayList<Guest>()


    fun setData(newListData: List<Guest>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAdapterViewHolder =
        GuestAdapterViewHolder(ItemGuestBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: GuestAdapterViewHolder, position: Int) {
        val guest = listData[position]
        val date =   SimpleDateFormat("yyyy-MM-dd").parse(guest.birthdate)
        val monthFormat = SimpleDateFormat("MM")
        val month = monthFormat.format(date)
        val session = SessionManagement(context)
        holder.bind(guest,context)
        holder.itemView.setOnClickListener {
            context.alert("Bulan lahir ke ${checkPrime(month.toInt())}") {
                yesButton {
                    when {
                        (month.toInt()%2==0 && month.toInt()%3==0) -> context.toast("iOS")
                        month.toInt()%3==0 -> context.toast("android")
                        month.toInt()%2==0 -> context.toast("blackberry")
                        else -> context.toast("phone")
                    }
                    session.updateGuest(guest.name)
                    val intent = Intent(context, EventGuestActivity::class.java)
                    intent.clearTask().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    context.startActivity(intent)
                }
            }.show()
        }
    }


    private fun checkPrime(month: Int): String{
        var primeString = ""
        var prime = false
        for (i in 2..month / 2) {
            if (month % i == 0) {
                prime = true
                break
            }
        }
        primeString = if (!prime) "$month merupakan prime"
        else "$month bukan prime"
        return primeString
    }

    override fun getItemCount(): Int = listData.size

    class GuestAdapterViewHolder(private val binding: ItemGuestBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(guest: Guest,context: Context){
            binding.tvName.text = guest.name
            binding.tvDate.text = guest.birthdate
            when(guest.id){
                1 -> {
                    Glide.with(context).load(R.drawable.picture1).into(binding.ivGuest)
                }
                2 -> {
                    Glide.with(context).load(R.drawable.picture8).into(binding.ivGuest)
                }
                3 -> {
                    Glide.with(context).load(R.drawable.picture3).into(binding.ivGuest)
                }
                4 -> {
                    Glide.with(context).load(R.drawable.picture4).into(binding.ivGuest)
                }
                5 -> {
                    Glide.with(context).load(R.drawable.picture7).into(binding.ivGuest)
                }
            }
        }
    }
}