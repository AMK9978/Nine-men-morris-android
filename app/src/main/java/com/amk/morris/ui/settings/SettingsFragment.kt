package com.amk.morris.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amk.morris.R
import com.github.angads25.toggle.widget.LabeledSwitch

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val backBtn = root.findViewById<ImageView>(R.id.back_btn)
        backBtn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        settingsViewModel.text.observe(this, Observer {})
        val labledSwitch = root.findViewById<LabeledSwitch>(R.id.switchIcon)
        labledSwitch.setOnToggledListener { toggleableView, isOn ->
            run {
                if (isOn)
                    Toast.makeText(context, "پخش موسیقی از سرگرفته شد", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "پخش موسیقی متوقف شد", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}