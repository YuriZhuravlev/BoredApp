package com.example.boredapp.ui.objects

import androidx.drawerlayout.widget.DrawerLayout
import com.example.boredapp.MainActivity
import com.example.boredapp.R
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem

class AppDrawer(activity: MainActivity) {
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem

    init {
        mDrawer = DrawerBuilder()
                .withActivity(activity)
                .withToolbar(activity.toolBar)
                .withSelectedItem(-1)
                .addDrawerItems(
                        ProfileDrawerItem().withName("Bored")
                                .withSelectable(false),
                        DividerDrawerItem(),
                        PrimaryDrawerItem().withIdentifier(101)
                                .withIconTintingEnabled(true)
                                .withName("Find Activity")
                                .withIcon(R.drawable.ic_app)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(102)
                                .withIconTintingEnabled(true)
                                .withName("Notebook")
                                .withIcon(R.drawable.ic_launcher_background)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(103)
                                .withIconTintingEnabled(true)
                                .withName("About")
                                .withIcon(R.drawable.ic_about)
                                .withSelectable(false),
                        PrimaryDrawerItem().withIdentifier(104)
                                .withIconTintingEnabled(true)
                                .withName("Theme")
                                .withIcon(R.drawable.ic_dark_theme)
                                .withSelectable(false),
                )
                .build()
    }


}