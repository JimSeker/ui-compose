package edu.cs4730.lazylistsdemo3

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.ArrayList

/**
 * this example displays, but the checkbox is updated it won't redraw.  idk why.... painful.
 */

@Composable
fun InterActiveScreen() {
    val state = rememberLazyListState()

    val list = remember { mutableStateListOf<InterActive_DataModel>() }
    list.add(InterActive_DataModel(0,"Linux"))
    list.add(InterActive_DataModel(1,"Windows7"))
    list.add(InterActive_DataModel(2,"Suse"))
    list.add(InterActive_DataModel(3,"Eclipse"))
    list.add(InterActive_DataModel(4,"Ubuntu"))
    list.add(InterActive_DataModel(5,"Solaris"))
    list.add(InterActive_DataModel(6,"Android"))
    list.add(InterActive_DataModel(7,"iPhone"))
    // Initially select one of the items
    list[1].FUSelected(true)

    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
//        items(
//            items = list,
//            key = { entry -> entry.id }
//        ) {entry ->
            itemsIndexed(list) { index, entry ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )

            ) {
                Row {
                    val context = LocalContext.current
                    Checkbox(
                        checked = entry.isSelected,
                        onCheckedChange = { checked ->
                            Toast.makeText(context, "value is $checked", Toast.LENGTH_SHORT).show()
                            if (checked) { //uncheck the rest.
                                for (item in list) {
                                    item.FUSelected(false)
                                }
                            }
                           //list[entry.id].FUSelected(checked)
                            list[index].FUSelected(checked)
                        },
                    )
                    Text(
                        text = entry.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InterActiveScreenPreview() {
    InterActiveScreen()
}