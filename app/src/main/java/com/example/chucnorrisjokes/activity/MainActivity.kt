package com.example.chucnorrisjokes.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.chucnorrisjokes.fragments.BrowserFragment
import com.example.chucnorrisjokes.fragments.JokeFragment
import com.example.chucnorrisjokes.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var  bottomNavigation: BottomNavigationView
    private  var jokesFragment: JokeFragment? = null
    private  var browserFragment: BrowserFragment? = null
    private val fragmentManager by lazy { supportFragmentManager }
    private val jokesTAG = "jokesFragment"
    private val webTAG = "webFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        browserFragment = fragmentManager.findFragmentByTag(webTAG) as? BrowserFragment
        jokesFragment = fragmentManager.findFragmentByTag(jokesTAG) as? JokeFragment
        bottomNavigation = findViewById(R.id.navigationMenu)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_jokes ->{
                    if (jokesFragment == null) {
                        jokesFragment = JokeFragment()
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.controlFrameLayout, jokesFragment!!,  jokesTAG)
                        fragmentTransaction.addToBackStack(jokesTAG)
                        fragmentTransaction.commit()

                    }
                    else{
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.controlFrameLayout, jokesFragment!!,  jokesTAG)
                        fragmentTransaction.addToBackStack(jokesTAG)
                        fragmentTransaction.commit()
                    }
                }
                R.id.navigation_web -> {
                    if (browserFragment == null) {
                        browserFragment = BrowserFragment()
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.controlFrameLayout, browserFragment!!,  webTAG)
                        fragmentTransaction.addToBackStack(webTAG)
                        fragmentTransaction.commit()
                    }
                    else{
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.controlFrameLayout, browserFragment!!,  webTAG)
                        fragmentTransaction.addToBackStack(webTAG)
                        fragmentTransaction.commit()
                    }
                }
            }
            false
        }
    }


    private fun createFragment(fragment: Fragment, tag: String){
        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.replace(R.id.controlFrameLayout, fragment,  jokesTAG)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment, tag: String){

    }



    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        for (f in fragments) {
            if (f is BrowserFragment)
                f.onBackPressed()
        }
        super.onBackPressed()
    }

}