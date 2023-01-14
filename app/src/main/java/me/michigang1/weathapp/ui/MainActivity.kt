package me.michigang1.weathapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.michigang1.weathapp.R
import me.michigang1.weathapp.databinding.ActivityMainBinding
import me.michigang1.weathapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        toolbar = binding.toolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        if (savedInstanceState != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commit()
        }
    }
}
