package com.sano.skyengpet.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import com.sano.skyengpet.R
import com.sano.skyengpet.databinding.ActivityMainBinding

//TODO Koin
internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        supportFragmentManager.commit {
            add(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
        }
    }
}