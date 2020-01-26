package com.example.chucnorrisjokes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucnorrisjokes.utils.Adapter
import com.example.chucnorrisjokes.utils.GetJokes
import com.example.chucnorrisjokes.R
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity() {

    private lateinit var textEdit: EditText
    private lateinit var listJokes: RecyclerView
    private var jokesArray :ArrayList<String> = ArrayList()
    private lateinit var jokes: GetJokes
    private lateinit var adapterJokes: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textEdit = findViewById(R.id.countJokes)
        listJokes = findViewById(R.id.listJokes)
        listJokes.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        initScreen()
        resetScreen(savedInstanceState)
    }

    fun clickOnWebButton(view: View) {
        val intent = Intent(this, BrowserActivity::class.java)
        startActivity(intent)
    }

    fun clickReload(view: View) {
        try{
            jokes = GetJokes(this, textEdit.text.toString().toInt())
        }
        catch (e: NumberFormatException){
            Log.e("Error", "Error with input number")

        }
        jokes = GetJokes(this, textEdit.text.toString().toInt())
        jokes.requestToAPI()
        jokes.queue.requestQueue.addRequestFinishedListener<String> {
            jokesArray.clear()
            jokes.makeJokeArrayList()
            jokesArray.addAll(jokes.jokesArrayList)
            adapterJokes = Adapter(jokesArray)
            listJokes.adapter = adapterJokes
        }
    }

    override fun onSaveInstanceState(saveBundle: Bundle) {
        super.onSaveInstanceState(saveBundle)
        saveBundle.putStringArrayList("jokes", jokesArray)
    }

    private fun resetScreen(saveBundle: Bundle?){
        if(saveBundle != null){
            jokesArray.clear()
            saveBundle.getStringArrayList("jokes")?.let { jokesArray.addAll(it) }
        }

    }

    private fun initScreen(){
        adapterJokes = Adapter(jokesArray)
        listJokes.adapter = adapterJokes
    }
}
