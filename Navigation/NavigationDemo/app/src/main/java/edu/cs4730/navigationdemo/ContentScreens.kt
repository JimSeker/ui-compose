package edu.cs4730.navigationdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun FirstScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "First Screen",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Button(onClick = { navController.navigate("two") }) {
            Text("Screen Two")
        }
        Button(onClick = { navController.navigate("three") }) {
            Text("Screen Three")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen(navController= rememberNavController())
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Second Screen",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        //to go back one level, popBackStack can be called.  The back button does the same.
        Button(onClick = { navController.popBackStack() }) {
            Text("go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(navController= rememberNavController())
}

@Composable
fun ThirdScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Third Screen",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        //to go back one level, popBackStack can be called.  The back button does the same.
        Button(onClick = { navController.popBackStack() }) {
            Text("go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    ThirdScreen(navController= rememberNavController())
}