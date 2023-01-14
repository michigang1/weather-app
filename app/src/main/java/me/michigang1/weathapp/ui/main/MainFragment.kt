package me.michigang1.weathapp.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import me.michigang1.weathapp.R
import me.michigang1.weathapp.databinding.FragmentMainBinding
import javax.inject.Inject
import by.kirich1409.viewbindingdelegate.viewBinding
import me.michigang1.weathapp.App

class MainFragment : Fragment(R.layout.fragment_main) {
    
    @Inject
    lateinit var factory: MainViewModelFactory
    
    private val viewModel: MainViewModel by viewModels{ factory }
    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    
    companion object {
        fun newInstance() = MainFragment()
    }
    
    override fun onAttach(context: Context) {
        App.INSTANCE.appComponent.inject(this)
        super.onAttach(context)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                // todo
                return true
            }
            R.id.my_location -> {
                checkLocationPermission()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun checkLocationPermission() {
        when {
            (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.getLocation()
            }
            
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.why_i_need_permission_message),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(
                        getString(R.string.grant)
                    ) {
                        locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }.show()
            }
            
            else -> {
                locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
    
    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                viewModel.getLocation()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.if_has_not_permission_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}