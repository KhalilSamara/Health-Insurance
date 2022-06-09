package com.example.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.EXTRA_NOTIFICATION_ID
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.PatientProvider

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x: String = "Surgery"
        var flag: String
        var flag1: String
        var flag2: String
        var flagGender: String


        //3 lines added NOT SURE
        val P_NAME: EditText = findViewById(R.id.editName)
        val P_SSNO: EditText = findViewById(R.id.editIdNum)
        val D_BIRTH : EditText = findViewById(R.id.editTextDate2)

        val spinnerDept: Spinner = findViewById(R.id.spChooseDept)
        var Dept = arrayOf("Surgery" /*1200*/, "Orthopaedics"/*250*/, "Neurology", "Plastic Surgery",
            "Orthopaedic", "Cardiology")
        spinnerDept.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Dept)
        spinnerDept.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = Dept.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val spinnerDr: Spinner = findViewById(R.id.spChooseDr)
        var Dr = arrayOf("Dr. Mike Ross", "Dr. Emily Watson", "Dr. Harvey Specter", "Dr. Dona King",
            "Dr. Omar Ahmad", "Dr. Max Goodwin ", "Dr. Iggy Frome", "Dr.Cassian Shin",
            "Dr. Yousef Rashid", "Dr. Khaled Ahmad", "Dr. Rachel Zane", "Dr. Dana Soctt", "Dr. Katrina Bennet"
            , "Dr. Zoe Lawford", "Dr. ", "Michael Gray", "Dr. Sara Kharim", "Dr. Joud Massoud",
            "Dr. Tareq Saif")
        spinnerDr.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Dr)
        spinnerDr.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag1 = Dr.get(p2)
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }
        val spinnerGender: Spinner = findViewById(R.id.spGender)
        var Gender = arrayOf("Male", "Female")
        spinnerGender.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Gender)
        spinnerGender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flagGender = Dept.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Empty
            }
        }

        val confirm: Button = findViewById(R.id.btnConfirm)
        confirm.setOnClickListener(){


        val intent = Intent(this@MainActivity,SecondActivity::class.java)
        startActivity(intent)
        }

    }

    fun onClickAddName(view: View?) {
// Add a new patient record
        val values = ContentValues()
        values.put(
            PatientProvider.NAME,
            (findViewById<View>(R.id.editName) as EditText).text.toString()
        )
        values.put(
            PatientProvider.ADDRESS,
            (findViewById<View>(R.id.editAddress) as EditText).text.toString()
        )
        val uri = contentResolver.insert(
            PatientProvider.CONTENT_URI, values
        )
        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()
    }

    fun onClickRetrievePatients(view: View?) {
        // Retrieve patient records
        val URL = "content://com.example.MyApplication.PatientProvider"
        val patients = Uri.parse(URL)
        //\  val c = contentResolver!!.query(patients,null,null,null,"name"
        var c = contentResolver.query(patients, null, null, null, null)
        //val //c = managedQuery(patients, null, null, null, "name")
        if (c != null) {
            if (c?.moveToFirst()) {
                do {

                    Toast.makeText(this,
                        c.getString(c.getColumnIndex(PatientProvider._ID)) + ", " + c.getString(c.getColumnIndex(
                            PatientProvider.NAME)) + ", " + c.getString(c.getColumnIndex(
                            PatientProvider.ADDRESS)),
                        Toast.LENGTH_SHORT).show()
                } while (c.moveToNext())
            }
        }
    }

}