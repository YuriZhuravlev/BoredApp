package com.example.boredapp.ui.objects

import android.view.View
import com.example.boredapp.MainActivity
import com.example.boredapp.R
import com.example.boredapp.ui.fragments.AboutFragment
import com.example.boredapp.ui.fragments.ActivityFragment
import com.example.boredapp.ui.fragments.notes.NotesFragment
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawer() {
    private var mDrawer: Drawer

    init {
        mDrawer = DrawerBuilder()
                .withActivity(MainActivity.getActivity())
                .withToolbar(MainActivity.getActivity().toolBar)
                .withSelectedItem(-1)
                .addDrawerItems(
                        ProfileDrawerItem().withName(R.string.app_name)
                                .withIcon(R.drawable.ic_app)
                                .withSelectable(false),
                        DividerDrawerItem(),
                        PrimaryDrawerItem().withIdentifier(101)
                                .withIconTintingEnabled(true)
                                .withName(R.string.find_activity)
                                .withIcon(R.drawable.ic_app)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(102)
                                .withIconTintingEnabled(true)
                                .withName(R.string.my_notes)
                                .withIcon(R.drawable.ic_notes)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(103)
                                .withIconTintingEnabled(true)
                                .withName(R.string.about)
                                .withIcon(R.drawable.ic_about)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(104)
                                .withIconTintingEnabled(true)
                                .withName(R.string.change_theme)
                                .withIcon(R.drawable.ic_dark_theme)
                                .withSelectable(false),
                )
                .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(
                            view: View?,
                            position: Int,
                            drawerItem: IDrawerItem<*>
                    ): Boolean {
                        clickToItem(position)
                        return false
                    }
                })
                .build()
    }

    private fun clickToItem(position: Int) {
        when (position) {
            2 -> {
                MainActivity.getActivity().replaceFragment(ActivityFragment())
            }
            3 -> {
                MainActivity.getActivity().replaceFragment(NotesFragment())
            }
            4 -> {
                MainActivity.getActivity().replaceFragment(AboutFragment())
            }
            5 -> {
                MainActivity.getActivity().changeTheme()
            }
        }
    }
}