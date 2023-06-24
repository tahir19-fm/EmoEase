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

object Graph{
    const val Root = "root_graph"
    const val Authentication = "auth_graph"
    const val Home = "home_graph"
    const val Account = "account_graph"
    const val Profile = "profile_graph"
    const val Bottom = "bottom_graph"
    const val Splash="splash_graph"
}