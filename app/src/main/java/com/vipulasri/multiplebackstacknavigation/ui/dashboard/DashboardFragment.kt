package com.vipulasri.multiplebackstacknavigation.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vipulasri.multiplebackstacknavigation.R
import com.vipulasri.multiplebackstacknavigation.ui.AbstractFragment

class DashboardFragment : AbstractFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView(inflater, container, R.id.action_dashboard_self)
    }
}