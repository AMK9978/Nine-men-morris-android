package com.amk.morris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class Util extends AppCompatActivity {

    public static Typeface iranSans;
    public static Typeface iranSans_bold;
    public static Typeface iranSans_normal;
    public static Typeface iranSans_medium;
    public static Typeface iranSans_fanum;
    public static Typeface iranSans_light;

    public static SharedPreferences sharedPreferences;
    public static Typeface iranSans_ultra_light;
    public static Typeface typeface1;
    public static Typeface typeface2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        typeface1 = getFont(getAssets(), "fonts/AGhasem.ttf");
        iranSans = getFont(getAssets(), "fonts/iransans.ttf");
        iranSans_fanum = getFont(getAssets(), "fonts/iran_sans_fanum.ttf");
        typeface2 = getFont(getAssets(), "fonts/iran_sans_fanum.ttf");
        iranSans_normal = getFont(getAssets(), "fonts/iran_sans_normal.ttf");
        iranSans_medium = getFont(getAssets(), "fonts/iran_sans_medium.ttf");
        iranSans_light = getFont(getAssets(), "fonts/iran_sans_light.ttf");
        iranSans_ultra_light = getFont(getAssets(), "fonts/iran_sans_ultra_light.ttf");
        iranSans_bold = getFont(getAssets(), "fonts/iran_sans_bold.ttf");
        FirebaseApp.initializeApp(this);
        Intent intent;
        if (sharedPreferences.getString("token", "nothing").equals("nothing")) {
            intent = new Intent(this, Login.class);
        }else{
            intent = new Intent(this, ProfileActivity.class);
        }
        startActivity(intent);
        finish();
    }

    public Typeface getFont(AssetManager assetManager, String path) {
        return Typeface.createFromAsset(assetManager, path);
    }
}
