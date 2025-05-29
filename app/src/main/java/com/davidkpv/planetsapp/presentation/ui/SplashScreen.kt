package com.davidkpv.planetsapp.presentation.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.davidkpv.planetsapp.R
import com.davidkpv.planetsapp.navigation.NavigationScreens
import com.davidkpv.planetsapp.presentation.viewModels.SplashViewModel
import com.davidkpv.planetsapp.utils.DELAY_TIME_SPLASH_SCREEN
import com.davidkpv.planetsapp.utils.LARGE_FONT_SIZE
import com.davidkpv.planetsapp.utils.LARGE_IMAGE
import com.davidkpv.planetsapp.utils.MEDIUM_PADDING
import com.davidkpv.planetsapp.utils.NORMAL_STROKE_WIDTH
import com.davidkpv.planetsapp.utils.SIMPLE_TIME_ANIMATION
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = koinViewModel(),
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotationAnimation =
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(tween(SIMPLE_TIME_ANIMATION, easing = LinearEasing)),
        )
    val user by viewModel.getUser.collectAsState()

    val brush =
        Brush.sweepGradient(
            colors = listOf(Color.Yellow, Color.Blue),
        )

    SplashScreenUi(
        rotationAnimation = rotationAnimation,
        brush = brush,
    )

    LaunchedEffect(Unit) {
        delay(DELAY_TIME_SPLASH_SCREEN)

        val userExists = user?.id == 1
        navController.popBackStack()
        navController.navigate(if (userExists) NavigationScreens.CatalogScreen.route else NavigationScreens.RegisterScreen.route)
    }
}

@Composable
fun SplashScreenUi(
    rotationAnimation: State<Float>,
    brush: Brush,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
            Canvas(
                modifier =
                    Modifier.matchParentSize().drawBehind {
                        rotate(rotationAnimation.value) {
                            drawCircle(
                                brush = brush,
                                radius = size.minDimension / 2,
                                style = Stroke(width = NORMAL_STROKE_WIDTH),
                            )
                        }
                    },
            ) {}
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.splash_icon_description),
                modifier =
                    Modifier
                        .size(
                            width = LARGE_IMAGE,
                            height = LARGE_IMAGE,
                        ).clip(CircleShape),
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = LARGE_FONT_SIZE,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(all = MEDIUM_PADDING),
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreenPreview() {
    SplashScreen(
        navController = rememberNavController(),
    )
}
