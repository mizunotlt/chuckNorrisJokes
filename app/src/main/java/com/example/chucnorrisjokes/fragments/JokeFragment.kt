package com.example.chucnorrisjokes.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucnorrisjokes.R
import com.example.chucnorrisjokes.data.JokesData
import com.example.chucnorrisjokes.models.JokesModel
import com.example.chucnorrisjokes.utils.Adapter
import java.lang.NumberFormatException


class JokeFragment : Fragment() {


    companion object {
        fun newInstance() = JokeFragment()
    }

    private val jokesViewModel by lazy { ViewModelProviders.of(this).get(JokesModel::class.java) }
    private lateinit var textEdit: EditText
    private lateinit var listJokes: RecyclerView
    private lateinit var adapterJokes: Adapter
    private lateinit var buttonReload: Button
    lateinit var viewJokes: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        viewJokes = inflater.inflate(R.layout.joke_fragment, container, false)
        textEdit = viewJokes.findViewById(R.id.countJokes)
        listJokes = viewJokes.findViewById(R.id.listJokes)
        buttonReload = viewJokes.findViewById(R.id.buttonReload)
        buttonReload.setOnClickListener {
            try{
                jokesViewModel.getJokes(textEdit.text.toString().toInt())
            }
            catch (e: NumberFormatException){
                Log.e("Error", "Error with input number")
            }
        }
        listJokes.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        adapterJokes = Adapter(arrayListOf())
        listJokes.adapter = adapterJokes
        return viewJokes
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        jokesViewModel.jokesLiveData.observe(viewLifecycleOwner, Observer<ArrayList<JokesData>> {
            adapterJokes.update(jokesViewModel.jokesLiveData.value!!)
        })

    }

}
