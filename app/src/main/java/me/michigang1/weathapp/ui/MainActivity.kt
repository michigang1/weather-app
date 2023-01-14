package me.michigang1.weathapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.michigang1.weathapp.R
import me.michigang1.weathapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, MainFragment.newInstance())
                .commit()
        }
    }
}
