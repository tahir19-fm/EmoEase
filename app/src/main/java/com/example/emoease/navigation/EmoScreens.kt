package com.example.emoease.navigation

import java.lang.IllegalArgumentException

enum class EmoScreens {
     SplashScreen,
    LoginScreen;

    companion object {
         fun fromRoute(route: String?): EmoScreens
          = when(route?.substringBefore("/")) {
              SplashScreen.name -> SplashScreen
             LoginScreen.name -> LoginScreen
             else -> throw IllegalArgumentException("Route $route is not recognized")
          }
    }


}