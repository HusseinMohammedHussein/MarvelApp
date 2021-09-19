package com.husseinmohammed.marvelapp.ui

import android.content.Intent
import com.daimajia.androidanimations.library.Techniques
import com.husseinmohammed.marvelapp.R
import com.husseinmohammed.marvelapp.ui.activity.MainActivity
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash


// Created by Hussein Mohammed on 9/19/2021.
class Splash : AwesomeSplash() {
    override fun initSplash(configSplash: ConfigSplash?) {

        configSplash?.let {
            //Customize Circular Reveal
            it.backgroundColor = R.color.black //any color you want form colors.xml
            it.animCircularRevealDuration = 2000 //int ms
            it.revealFlagX = Flags.REVEAL_LEFT  //or Flags.REVEAL_LEFT
            it.revealFlagY = Flags.REVEAL_BOTTOM //or Flags.REVEAL_TOP

            //Customize Logo
            it.logoSplash = R.mipmap.ic_app_logo //or any other drawable
            it.animLogoSplashDuration = 2000 //int ms
            it.animLogoSplashTechnique =
                Techniques.DropOut

            //Customize Title
            it.titleSplash = getString(R.string.splash_title)
            it.titleTextColor = R.color.white;
            it.titleTextSize = 30f; //float value
            it.animTitleDuration = 2000;
            it.animTitleTechnique = Techniques.FadeIn;
        }
    }

    override fun animationsFinished() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}