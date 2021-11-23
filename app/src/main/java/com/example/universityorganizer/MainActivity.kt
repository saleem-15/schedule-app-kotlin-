package com.example.universityorganizer

import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateDailyTable()

        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipe_to_refresh)

        swipeToRefresh.setOnRefreshListener {
            updateDailyTable()
            swipeToRefresh.isRefreshing = false
        }

        val btn = findViewById<Button>(R.id.showFullScheduleBtn)
        btn.setOnClickListener {
            val intent = Intent(this,schedule::class.java)
            startActivity(intent)
        }
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun updateDailyTable(){
        val table = findViewById<TableLayout>(R.id.main_activity_table)
        table.removeViewsInLayout(1,table.childCount-1)
        //table.removeAllViewsInLayout()
        val tableData = getSharedPreferences("tableData", MODE_PRIVATE)
        val rowsNum: Int = tableData.getInt("number of rows",0)

        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){

            Calendar.SATURDAY -> {

                for(i in rowsNum..1){
                    if(tableData.getString("day $i","")!!.contains("سبت")){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }

            Calendar.SUNDAY -> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الأحد"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
            Calendar.MONDAY -> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الاثنين"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
            Calendar.TUESDAY-> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الثلاثاء"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
            Calendar.WEDNESDAY-> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الأربعاء"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
            Calendar.THURSDAY -> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الخميس"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
            Calendar.FRIDAY -> {
                for(i in 1..rowsNum){

                    if(tableData.getString("day $i","")!!.toString() == "الجمعة"){
                        val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
                        (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
                        (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
                        (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
                        (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
                        table.addView(row)
                    }

                }
            }
        }

    }
}