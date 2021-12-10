package com.ojanbelajar.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ojanbelajar.suitmediatest.databinding.ActivityMainBinding
import com.ojanbelajar.suitmediatest.utils.SessionManagement
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var ready = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val session = SessionManagement(this)
        val name = binding.etName.text


        binding.btnNext.setOnClickListener {
            if (binding.etName.text.isNullOrEmpty()){
                binding.etName.error = "Tidak Boleh Kosong"
                ready = false
            }else {
                ready = true
            }
            if (ready) {
                alert("Nama merupakan kata ${checkPalindrome(name.toString())}") {
                    yesButton {
                        session.updateName(name.toString())
                        session.clearSession()
                        sendName()
                    }
                }.show()
            }
        }
    }



    private fun sendName(){
        val intent = Intent(this@MainActivity, EventGuestActivity::class.java)
        startActivity(intent)
    }
    
    private fun checkPalindrome(name: String): String{
        var message = "Not Palindrome"
        val reverseName = name.reversed()
        if ((reverseName == name)) message = "IsPalindrome"
        return message
    }
}