package com.boukharist.mvarchi.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.boukharist.mvarchi.R
import kotlinx.android.synthetic.main.form_view.*
import kotlinx.android.synthetic.main.info_view.*

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, InfoViewModelFactory()).get(InfoViewModel::class.java)
        initObservers()
        viewModel.fetchData()
    }

    private fun initObservers() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            showAge(it.age)
            showName(it.fullName)
        })
    }


    private fun showName(fullName: String) {
        nameTextView.text = getString(R.string.full_name_placeholder, fullName)
    }

    private fun showAge(age: Int) {
        ageTextView.text = getString(R.string.age_placeholder, age)
    }

}
