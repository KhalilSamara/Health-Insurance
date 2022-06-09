package com.example.project

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class SecondActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id"
    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        createNoticationChannel()

        var bVisited: Boolean = false
        var visit: RadioGroup = findViewById(R.id.visitGrp)
        visit.setOnCheckedChangeListener { group, checkedId ->
            bVisited = checkedId == R.id.visitYes
        }

        var bInsured: Boolean = false
        var insurance: RadioGroup = findViewById(R.id.insuranceGrp)
        insurance.setOnCheckedChangeListener { group, checkedId ->
            bInsured = checkedId == R.id.insuranceYes
        }



        var x: String = "9 - 10"
        var time: Spinner = findViewById(R.id.spTime)
        var options =
            arrayOf("9 - 10", "10 - 11", "11 - 12", "12 - 1", "1 - 2", "2 - 3", "3 - 4", "4 - 5")
        time.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)

        time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                x = options.get(p2) //p2 is the index of selected item
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        var y: String = "Check Up"
        var reason: Spinner = findViewById(R.id.spReason)
        var options1 = arrayOf("Consultation", "Check Up", "Follow Up")
        reason.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options1)

        reason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                y = options1.get(p2) //p2 is the index of selected item
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        var done: Button = findViewById(R.id.btnConfirm2)
        var back: Button = findViewById(R.id.btnBack)

        done.setOnClickListener(){
            startService(Intent(this, EndService::class.java))
            sendNotification()
        }

        back.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun createNoticationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "SuccessNotification"
            val descriptiontxt = "Success"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptiontxt
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Reservation Succesful")
            .setContentText("You have been registered succesfully. All my your information \\n\" +\n" +
                    "                    \"will be saved in the hospital system for future appointments.\\n\" +\n" +
                    "                    \"Please note that this information protected and can only \\n\" +\n" +
                    "                    \"be accesed by your doctors.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)){
                notify(notificationId,builder.build())
            }
    }

}