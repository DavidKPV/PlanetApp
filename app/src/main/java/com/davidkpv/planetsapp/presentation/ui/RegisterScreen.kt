package com.davidkpv.planetsapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.davidkpv.planetsapp.R
import com.davidkpv.planetsapp.data.entities.User
import com.davidkpv.planetsapp.navigation.NavigationScreens
import com.davidkpv.planetsapp.presentation.viewModels.RegisterViewModel
import com.davidkpv.planetsapp.utils.MEDIUM_PADDING
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    appNavController: NavController,
    modifier: Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }

    RegisterScreenUi(
        modifier = modifier,
        nameValueState = name,
        nameValueAction = { name = it },
        lastNameValueState = lastName,
        lastNameValueAction = { lastName = it },
        birthDateValueState = birthDate,
        birthDateValueAction = { birthDate = it },
        countryValueState = country,
        countryValueAction = { country = it },
        buttonAction = {
            if(name.isNotBlank()) {
                viewModel.insertUser(User(name = name, lastName = lastName, birthDate = birthDate, country = country))
                with(appNavController) {
                    popBackStack()
                    navigate(NavigationScreens.CatalogScreen.route)
                }
            }
        },
    )
}

@Composable
fun RegisterScreenUi(
    modifier: Modifier,
    nameValueState: String,
    nameValueAction: (String) -> Unit = {},
    lastNameValueState: String,
    lastNameValueAction: (String) -> Unit = {},
    birthDateValueState: String,
    birthDateValueAction: (String) -> Unit = {},
    countryValueState: String,
    countryValueAction: (String) -> Unit = {},
    buttonAction: () -> Unit,
) {
    Column(
        modifier = modifier.padding(MEDIUM_PADDING).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(value = nameValueState, onValueChange = { nameValueAction(it) }, label = {
            Text(stringResource(R.string.name_label))
        })
        TextField(value = lastNameValueState, onValueChange = { lastNameValueAction(it) }, label = {
            Text(stringResource(R.string.lastname_label))
        })
        TextField(value = birthDateValueState, onValueChange = { birthDateValueAction(it) }, label = {
            Text(stringResource(R.string.birthday_label))
        })
        TextField(value = countryValueState, onValueChange = { countryValueAction(it) }, label = {
            Text(stringResource(R.string.country_label))
        })
        Button(
            onClick = { buttonAction() },
            modifier = Modifier.padding(top = MEDIUM_PADDING),
        ) {
            Text(text = stringResource(R.string.button_register_label))
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RegisterScreenPreview() {
    RegisterScreen(
        rememberNavController(),
        Modifier,
    )
}
