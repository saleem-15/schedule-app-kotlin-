package com.example.universityorganizer

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*

class schedule : AppCompatActivity() {

    private lateinit var t : TableLayout
    private lateinit var subjectsArray: ArrayList<String>

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)



        val tableData = getSharedPreferences("tableData", MODE_PRIVATE)

        subjectsArray = ArrayList(tableData.getStringSet("subjectsList", setOf())?.toList())

        t = findViewById(R.id.table)
        val addRowBtn = findViewById<Button>(R.id.addRowBtn)
        val deleteBtn = findViewById<Button>(R.id.delete_row)
        val addNewSubject = findViewById<Button>(R.id.add_subject)

        /** when pressing (أضف مادة جديدة)   **/
        addNewSubject.setOnClickListener {
            showAddNewSubjectDialog()
        }


        deleteBtn.setOnClickListener {
            val dialog  = Dialog(this)
            dialog.setContentView(R.layout.delete_dialog)

            val etDeleteRowNum = dialog.findViewById<EditText>(R.id.et_delete)

            dialog.findViewById<Button>(R.id.delete_btn).setOnClickListener {

              //  etDeleteRowNum.text.toString().toInt()
               // editor.remove("")
                Toast.makeText(this,"this functionality is not added yet",Toast.LENGTH_SHORT).show()
            }

            dialog.show()
        }



        val rowsNum: Int = tableData.getInt("number of rows",0)

        for(i in rowsNum..1){

            val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow
            (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
            (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
            (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
            (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
            t.addView(row)
            t.requestLayout()
        }


        addRowBtn.setOnClickListener{
            addNewRowDialog()
        }

    }

    override fun onResume() {
        super.onResume()
        val tableData = getSharedPreferences("tableData", MODE_PRIVATE)
        val editor = tableData.edit()

        val rowsNum: Int = tableData.getInt("number of rows",0)


        //this line was added to remove all the old views ,so the views wont be added multiple times
        t.removeViewsInLayout(1,t.childCount-1)
        for(i in 1..rowsNum){

            val row = LayoutInflater.from(this).inflate(R.layout.table_row, null) as TableRow

            (row.findViewById<TextView>(R.id.Rday)).text = tableData.getString("day $i","1")
            (row.findViewById<TextView>(R.id.Rtime)).text = tableData.getString("time $i","1")
            (row.findViewById<TextView>(R.id.Rplace)).text = tableData.getString("place $i","1")
            (row.findViewById<TextView>(R.id.Rsubject)).text = tableData.getString("subject $i","1")
            t.addView(row)
        }

    }



    private fun addNewRowDialog(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.new_row_dialog_info)
        dialog.setCancelable(false)
        dialog.show()

        val day = dialog.findViewById<AutoCompleteTextView>(R.id.day_dropdown)
        val StartTime = dialog.findViewById<AutoCompleteTextView>(R.id.time_start_dropdown)
        val EndTime = dialog.findViewById<AutoCompleteTextView>(R.id.time_end_dropdown)
        val place = dialog.findViewById<EditText>(R.id.place)
        val subject = dialog.findViewById<AutoCompleteTextView>(R.id.subject_dropdown)

        //getting a StringArray of choices from an xml resource
        val optionsArray = resources.getStringArray(R.array.days_of_the_week_array)
        //making an adapter for the dropDown list
        val dropdownAdapter = ArrayAdapter(this,R.layout.dropdown_row,optionsArray)
        day.setAdapter(dropdownAdapter)

        StartTime.setAdapter(ArrayAdapter(this,R.layout.dropdown_row,resources.getStringArray(R.array.time_array)))
        EndTime.setAdapter(ArrayAdapter(this,R.layout.dropdown_row,resources.getStringArray(R.array.time_array)))

        subject.setAdapter(ArrayAdapter(this,R.layout.dropdown_row,subjectsArray))



        dialog.findViewById<Button>(R.id.cancel_btn).setOnClickListener {
            dialog.dismiss()
            //
        }



        dialog.findViewById<Button>(R.id.add_row_btn).setOnClickListener {

            if(day.text.isEmpty() || StartTime.text.isEmpty() || place.text.isEmpty()  ||subject.text.isEmpty()){
                Toast.makeText(this,"you should fill all the fields", Toast.LENGTH_SHORT).show()
            }else {
                // Inflate your row "template" and fill out the fields.
                val row = LayoutInflater.from(this)
                    .inflate(R.layout.table_row, null) as TableRow

                (row.findViewById<TextView>(R.id.Rday)).text = day.text.toString()
                (row.findViewById<TextView>(R.id.Rtime)).text = "${EndTime.text}-${StartTime.text}"
                (row.findViewById<TextView>(R.id.Rplace)).text = place.text.toString()
                (row.findViewById<TextView>(R.id.Rsubject)).text = subject.text.toString()
                t.addView(row)


                val sharedPreferences = getSharedPreferences("tableData", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val rowNumber =  sharedPreferences.getInt("number of rows",0)
                editor.putString("day ${rowNumber+1}",day.text.toString())
                editor.putString("time ${rowNumber+1}","${EndTime.text}-${StartTime.text}")
                editor.putString("place ${rowNumber+1}",place.text.toString())
                editor.putString("subject ${rowNumber+1}",subject.text.toString())
                editor.putInt("number of rows",rowNumber+1)

                editor.apply()
                dialog.dismiss()
            }
        }


    }


    private fun showAddNewSubjectDialog(){

        val addSubjectDialog = Dialog(this)
        addSubjectDialog.setContentView(R.layout.add_subject_dialog)
        addSubjectDialog.setCancelable(false)
        addSubjectDialog.show()

        val subjectsDropdownList = addSubjectDialog.findViewById<AutoCompleteTextView>(R.id.subjects_list) //show the list of existing subjects
        val etSubject = addSubjectDialog.findViewById<EditText>(R.id.et_new_subject)

        subjectsDropdownList.setAdapter(ArrayAdapter(this,R.layout.dropdown_row,subjectsArray))

        /**   (أضف المادة) click listener */
        addSubjectDialog.findViewById<Button>(R.id.add_subject_to_list).setOnClickListener {

            var isSubjectExist = false
            if(!etSubject.text.isNullOrBlank()){
                    for (i in subjectsArray.indices){

                        if(etSubject.text.toString() == subjectsArray[i]){

                            Toast.makeText(this,"المادة موجودة مسبقا !!",Toast.LENGTH_SHORT).show()
                            isSubjectExist = true
                            break
                        }
                    }

                    if(!isSubjectExist)
                        subjectsArray.add(etSubject.text.toString())

                    etSubject.text.clear()

                    val sharedPreferences =  getSharedPreferences("tableData", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putStringSet("subjectsList",subjectsArray.toSet()).apply()
                }else{
                    Toast.makeText(this,"أكتب المادة التي تريدها أولا!! ",Toast.LENGTH_SHORT).show()
            }


        }

        /**  (الغاء)click listener **/
        addSubjectDialog.findViewById<Button>(R.id.cancel_btn).setOnClickListener {
            addSubjectDialog.dismiss()
        }
    }



}