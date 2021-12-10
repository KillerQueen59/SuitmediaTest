package com.ojanbelajar.suitmediatest.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.ojanbelajar.suitmediatest.adapter.EventAdapter
import com.ojanbelajar.suitmediatest.data.remote.Event
import com.ojanbelajar.suitmediatest.databinding.FragmentEventMapBinding

import android.graphics.Bitmap
import android.graphics.Canvas

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable
import android.os.Handler
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.Marker
import com.ojanbelajar.suitmediatest.R
import com.ojanbelajar.suitmediatest.adapter.SwipeEventAdapter
import org.jetbrains.anko.support.v4.toast


class EventMapFragment: Fragment() , OnMapReadyCallback {

    var pick: String = "Iconfest"
    private lateinit var binding : FragmentEventMapBinding
    lateinit var adapter: SwipeEventAdapter
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventMapBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync{
            map = it
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(40.712776,-74.005974),15f))
            addMarker()
        }
        adapter = SwipeEventAdapter(requireActivity(),setDummyData())
        binding.vpEvent.adapter = adapter
        binding.vpEvent.offscreenPageLimit = 1
        binding.vpEvent.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val data = setDummyData()
                pick = data[position].name
                val latlng = LatLng(data[position].lat,data[position].long)
                binding.progress.isVisible = true
                Handler().postDelayed({
                    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15f))
                    binding.progress.isVisible = false
                }, 2000)
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    fun addMarker(){
        val data = setDummyData()

        for(d in data){
            val place = LatLng(d.lat,d.long)
            map!!.addMarker(
                MarkerOptions()
                    .position(place)
                    .title("Marker in ${d.name}"))
        }

//        when(pick){
//            "Iconfest" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(iconfest));
//            }
//            "ITToday" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ittoday));
//            }
//            "Paradigma" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(paradigma));
//            }
//            "Vision" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(vision));
//            }
//            "SynchroFEST" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(synchro));
//            }
//            "EduFest" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(edufest));
//            }
//            "Youtube Rewind" -> {
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(youtube));
//            }
//        }
    }


    override fun onResume() {
        super.onResume()
        binding.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView?.onSaveInstanceState(outState)
    }

    fun setDummyData(): ArrayList<Event>{
        val listEvent = ArrayList<Event>()
        listEvent.add(Event(R.drawable.logo1,"Iconfest","28-01-2021",40.712776,-74.005974))
        listEvent.add(Event(R.drawable.logo2,"ITToday","15-03-2020",51.507351,-0.127758))
        listEvent.add(Event(R.drawable.logo3,"Paradigma","07-11-2021",-33.924870,18.424055))
        listEvent.add(Event(R.drawable.logo4,"Vision","03-05-2019",25.043551135505613, 46.56966750407985))
        listEvent.add(Event(R.drawable.logo5,"SynchroFEST","02-01-2018",36.43960404550113, 128.36466720896559))
        listEvent.add(Event(R.drawable.logo6,"EduFest","17-04-2021",-7.183385234975524, 109.38568029078947))
        listEvent.add(Event(R.drawable.logo7,"Youtube Rewind","21-08-2021",-34.73770627205564, 144.36614771527533))
        return listEvent
    }

    override fun onMapReady(googleMap: GoogleMap) {

    }



}