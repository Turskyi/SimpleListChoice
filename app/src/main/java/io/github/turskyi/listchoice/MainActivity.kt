package io.github.turskyi.listchoice

import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val LOG_TAG = "myLogs"
    }

    private lateinit var lvMain: ListView
    private lateinit var names: Array<String>

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvMain = findViewById<View>(R.id.lv_main) as ListView
        /** Setting the mode for selecting list items. */

        /** For single choice. */
//        lvMain.choiceMode = ListView.CHOICE_MODE_SINGLE

        /* Create an adapter using an array from a resource file */
//        val adapter = ArrayAdapter.createFromResource(
//                this, R.array.names,
//                android.R.layout.simple_list_item_single_choice)

        /** For multiple choice. */
        lvMain.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        val adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_multiple_choice)

        lvMain.adapter = adapter
        val btnChecked: Button = findViewById<View>(R.id.btn_checked) as Button
        btnChecked.setOnClickListener(this)

        /** getting array from a resource file */
        names = resources.getStringArray(R.array.names)
    }

   override fun onClick(arg0: View?) {
       /** Writing the selected items to the log. */

       /** For single choice. */
//        Log.d(LOG_TAG, "checked: " + names[lvMain.checkedItemPosition])
//       Toast.makeText(this, "checked: " + names[lvMain.checkedItemPosition], Toast.LENGTH_LONG).show()

       /** For multiple choice. */
       Log.d(LOG_TAG, "checked: ")
       val sbArray: SparseBooleanArray = lvMain.checkedItemPositions
       val checkedItems:MutableList<String> = mutableListOf()
       for (i in 0 until sbArray.size()) {
           val key = sbArray.keyAt(i)
           if (sbArray[key]) {
               Log.d(LOG_TAG, names[key])
               checkedItems.add(names[key])
           }
       }
       Toast.makeText(this, "checked: $checkedItems", Toast.LENGTH_SHORT).show()
    }
}
