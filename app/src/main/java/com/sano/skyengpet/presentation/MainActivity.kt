package com.sano.skyengpet.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.commit
import com.sano.skyengpet.R

//TODO access qualificators
//TODO DSL
//TODO SOLID
//TODO MVI
//TODO ViewBinding/Synthetic
//TODO Koin
internal class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)

        supportFragmentManager.commit {
            add(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
        }
    }
}