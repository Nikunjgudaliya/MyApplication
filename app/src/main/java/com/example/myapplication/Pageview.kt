package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class Pageview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pageview)

        class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
            override fun getCount(): Int {
                return 3
            }

            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> {
                        return Fragment_1()
                    }

                    1 -> {
                        return Fragment_2()
                    }

                    2 -> {
                        return Fragment_3()
                    }

                    else -> {
                        return Fragment_1()
                    }
                }
            }

            override fun getPageTitle(position: Int): CharSequence? {
                when (position) {
                    0 -> {
                        return "Tab 1"
                    }

                    1 -> {
                        return "Tab 2"
                    }

                    2 -> {
                        return "Tab 3"
                    }
                }
                return super.getPageTitle(position)
            }
        }

        val viewPager = findViewById<ViewPager>(R.id.view)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

//        lateinit var mbinding = ActivityMainBinding
//
//        mbinding.button1.setOnClickListener(btnClickEvents)
//        mbinding.button2.setOnClickListener(btnClickEvents)
//        mbinding.button3.setOnClickListener(btnClickEvents)
//
//        private val btnClickEvents = view.OnClickListener{ view ->
//            when(view.id){
//                R.id.button1 ->
//            }
//        }
//
//        mbinding.btn3.setOnClickListener{
//            replacefragment(fragment_1)
//        }
//
//        private fun replacefragment(fragment: fragment){
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction.replace(R.id.fragements, fragment)
//            fragmentTrasaction.commit()
    }
}
