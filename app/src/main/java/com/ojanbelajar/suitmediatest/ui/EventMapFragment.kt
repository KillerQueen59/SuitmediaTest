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
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.Marker
import com.ojanbelajar.suitmediatest.R


class EventMapFragment: Fragment() , OnMapReadyCallback {

    private lateinit var binding : FragmentEventMapBinding
    lateinit var adapter: EventAdapter

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
        binding.mapView.getMapAsync(this)
        adapter = EventAdapter(requireActivity())
        adapter.setData(setDummyData())
        setupRV()

    }

    fun setupRV(){
        binding.rvEvent.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvEvent.adapter = adapter
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

        val data = setDummyData()
        for(d in data){
            val place = LatLng(d.lat,d.long)
            googleMap.addMarker(
                MarkerOptions()
                    .position(place)
                    .title("Marker in ${d.name}")
            )
        }
    }



}