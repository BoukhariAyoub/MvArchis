package com.boukharist.mvarchi.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boukharist.mvarchi.R
import com.boukharist.mvarchi.databinding.FormViewBinding

class FormFragment : Fragment() {

    private lateinit var binding: FormViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val viewModel =
            ViewModelProvider(this, FormViewModelFactory()).get(FormViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.form_view, container, false)
        return binding.apply {
            this.viewModel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}
