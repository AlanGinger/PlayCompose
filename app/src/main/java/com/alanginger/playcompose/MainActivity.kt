package com.alanginger.playcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alanginger.playcompose.ui.theme.PlayComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Content()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        Content()
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    PlayComposeTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Content() {
    var count by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable {
                    count++
                },
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Davenport, California",
            style = typography.body2
        )
        Text(
            text = "December ${2018 + count}",
            style = typography.body2
        )
        Divider(modifier = Modifier.padding(0.dp, 10.dp), color = Color.Black)
        ListView()
    }
}

@Composable
fun ListView() {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        val names: List<String> = List(1000) { "Hello Android #$it" }
        items(items = names) { name ->
            ItemView(name)
        }
    }
}

@Composable
fun ItemView(text: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Text(
        text = text,
        style = typography.body1,
        modifier = Modifier
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
    Divider(modifier = Modifier.padding(0.dp, 10.dp), color = Color.Black)
}