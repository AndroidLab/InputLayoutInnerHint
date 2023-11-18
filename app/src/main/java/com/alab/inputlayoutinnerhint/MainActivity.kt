package com.alab.inputlayoutinnerhint

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.alab.input_layout_inner_hint.TextInputLayout
import com.google.android.material.textfield.MaterialAutoCompleteTextView


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextInputLayout>(R.id.simpleTextInputLayout).addEditTextFocusChangeListener { view, b ->
            Log.d("myL",  "Focus $b")
        }

        val items = listOf(
            "item 0",
            "item 1",
            "item 2",
            "item 3",
            "item 4",
            "item 5",
            "item 6",
            "item 7",
            "item 8",
            "item 9",
        )

        val _itemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("myL", items[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        findViewById<MaterialAutoCompleteTextView>(R.id.materialAutoCompleteTextView).apply {
            setAdapter(
                ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, items)
            )
            onItemSelectedListener = _itemSelectedListener
        }

        findViewById<MaterialAutoCompleteTextView>(R.id.materialAutoCompleteTextViewSelector).apply {
            setAdapter(
                ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, items)
            )
            onItemSelectedListener = _itemSelectedListener
        }



    }
}