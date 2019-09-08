package com.amk.morris

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.amk.morris.ui.contact_us.ContactFragment
import com.amk.morris.ui.edit.EditFragment
import com.amk.morris.ui.history.HistoryFragment
import com.amk.morris.ui.home.HomeFragment
import com.amk.morris.ui.rating.RatingFragment
import com.amk.morris.ui.settings.SettingsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.layout_profile.*
import androidx.core.app.ShareCompat
import com.google.firebase.analytics.FirebaseAnalytics
import android.util.StatsLog.logEvent
import android.os.Build
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.File


class ProfileActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var profileImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_profile)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView2: NavigationView = findViewById(R.id.nav_view2)
        replaceFragment(fragment = HomeFragment())
        val view = navView2.getHeaderView(0)
        profileImage = view.findViewById(R.id.profile_image)
        loadProfileImage()
        navView2.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(fragment = HomeFragment())
                }
                R.id.nav_edit -> {
                    replaceFragment(fragment = EditFragment())
                }
                R.id.nav_history -> {
                    replaceFragment(fragment = HistoryFragment())
                }
                R.id.nav_rating -> {
                    replaceFragment(fragment = RatingFragment())
                }
                R.id.nav_contact -> {
                    replaceFragment(fragment = ContactFragment())
                }
                R.id.nav_share -> {
                    val bundle = Bundle()
                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "https://morris_fa.ir/morris.apk")
                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, Build.VERSION.RELEASE)
                    firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SHARE, bundle)

                    ShareCompat.IntentBuilder.from(this)
                            .setType("text/plain")
                            .setChooserTitle("اشتراک گذاری موریس")
                            .setText("https://morris_fa.ir/morris.apk")
                            .startChooser()
                }
                R.id.nav_settings -> {
                    replaceFragment(fragment = SettingsFragment())
                }
            }
            drawer_layout.closeDrawer(GravityCompat.END)
            false
        }
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_rating, R.id.nav_history,
                R.id.nav_edit, R.id.nav_share, R.id.nav_contact), drawerLayout)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.profile, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadProfileImage() {
        val mainFile = File(Environment.getExternalStorageDirectory(), "Morris/profileImage.jpg")
        Picasso.get().load(mainFile).fit().placeholder(R.drawable.ic_person_black_24dp).into(profileImage)
    }
}
