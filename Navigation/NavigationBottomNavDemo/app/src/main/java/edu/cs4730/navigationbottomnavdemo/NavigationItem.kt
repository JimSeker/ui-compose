package edu.cs4730.navigationbottomnavdemo

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object First : NavigationItem("home", R.drawable.ic_action_one, "First")
    object Second : NavigationItem("music", R.drawable.ic_action_second, "Second")
    object Third : NavigationItem("movies", R.drawable.ic_action_third, "Third")
}
