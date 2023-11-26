package edu.cs4730.formexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.cs4730.formexample.ui.theme.FormExampleTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                ),
                                title = {
                                    Text(stringResource(R.string.app_name))
                                }
                            )
                        },

                    ){innerPadding ->
                        FormExampleContent(innerPadding)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormExampleTheme {
        FormExampleContent(PaddingValues() )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormExampleContent( innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        // Label01
        Text(
            text = "Text item, Hello Form",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            // style = MaterialTheme.typography.headlineMedium
        )
        val context = LocalContext.current
        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name:") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text(
            text = "Radio Button Group",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            //style = MaterialTheme.typography.headlineMedium
        )

        // Radio Group
        var selectedOption by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "Information",
                    onClick = { selectedOption = "Information" },
                )
                Text("Information", modifier = Modifier.fillMaxWidth())
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "Confirmation",
                    onClick = { selectedOption = "Confirmation" },
                )
                Text("Confirmation", modifier = Modifier.fillMaxWidth())
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "Warning",
                    onClick = {
                        selectedOption = "Warning"
                        Toast.makeText(context, "Warning!", Toast.LENGTH_LONG).show()
                    },
                )
                Text("Warning", modifier = Modifier.fillMaxWidth())
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Button alert:",
                modifier = Modifier
                    .padding(8.dp),
            )
            // Button
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        Toast.makeText(context, "The TextField has $name", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "The button was pressed", Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                Text("Toast!")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Picture:",
                modifier = Modifier
                    .padding(8.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "Android icon",
                modifier = Modifier
                    .size(75.dp)
                    .padding(8.dp)
            )
        }
    }
}