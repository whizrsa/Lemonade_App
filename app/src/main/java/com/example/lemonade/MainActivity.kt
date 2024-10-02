package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonImagesWithButton(modifier: Modifier = Modifier)
{
    var currentStep by remember { mutableStateOf(1) }
    var tappingTreeCount  by remember { mutableStateOf(0) }
    var neededTaps by remember { mutableStateOf((2..4).random()) }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep){
            1 -> LemonTextAndImages(
                text = stringResource(R.string.tap_tree),
                imageRes = R.drawable.lemon_tree, //image is an integer parameter
                contentDescription = stringResource(R.string.tap_tree),
                onImageClick = { currentStep = 2 },
                modifier = modifier
            )
            2 -> LemonTextAndImages(
                text = stringResource(R.string.keep_tapping),
                imageRes = R.drawable.lemon_squeeze,
                contentDescription = stringResource(R.string.keep_tapping),
                onImageClick = {
                    tappingTreeCount++
                    if(tappingTreeCount == neededTaps)
                    {
                        currentStep = 3
                        tappingTreeCount = 0
                        neededTaps = (2..4).random()
                    }
                },
                modifier = modifier
            )
            3 -> LemonTextAndImages(
                text = stringResource(R.string.tap_lemonade),
                imageRes = R.drawable.lemon_drink,
                contentDescription = stringResource(R.string.glass_lemonade),
                onImageClick = { currentStep = 4 },
                modifier = modifier
            )
            else -> LemonTextAndImages(
                text = stringResource(R.string.tap_empty_glass),
                imageRes = R.drawable.lemon_restart,
                contentDescription = stringResource(R.string.empty_glass),
                onImageClick = { currentStep = 1 },
                modifier = modifier
            )
        }
    }


}

@Composable
fun LemonTextAndImages(
    text: String,
    imageRes: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(imageRes),
            contentDescription = contentDescription,
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
        )
    }
}

@Preview
@Composable
fun LemonadeApp()
{
    LemonImagesWithButton(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}