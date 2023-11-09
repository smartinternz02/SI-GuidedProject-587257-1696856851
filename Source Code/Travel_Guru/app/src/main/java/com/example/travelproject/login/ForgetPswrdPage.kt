package com.example.travelproject.login

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.ui.theme.darkblue
import com.example.travelproject.ui.theme.lightpink
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPswrdPage(navController: NavHostController) {
    var emailAddress by remember { mutableStateOf("") }
    val emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com\$"
    var isEmailValid by remember { mutableStateOf(true) }

    var password by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@$!%*?&]{8,}"

    var confirmPassword by remember { mutableStateOf("") }
    var isConfirmPasswordValid by remember { mutableStateOf(true) }

    var isPasswordOpen1 by remember { mutableStateOf(false) }
    var isPasswordOpen2 by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(id = R.drawable.forgetpswrdbg),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        contentDescription = "Background Image"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(180.dp))
        Card(
            modifier = Modifier
                .height(390.dp)
                .width(1000.dp)
                .padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = lightpink, //Card background color
                contentColor = Color.Black  //Card content color,e.g.text
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    value = emailAddress,
                    onValueChange = {
                        emailAddress = it
                        isEmailValid = it.matches(emailRegex.toRegex()) || it.isEmpty()
                    },
                    isError = !isEmailValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.medium,
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    label = { Text(text = "Email Address") })
                if (!isEmailValid && emailAddress.isNotEmpty()) {
                    Text(text = "Not in email format", color = Color.Red)
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = it.matches(passwordRegex.toRegex()) || it.isEmpty()
                    },
                    isError = !isPasswordValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.medium,
                    leadingIcon = {
                        Icon(Icons.Default.Info, contentDescription = null)
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordOpen1 = !isPasswordOpen1 }) {
                            if (!isPasswordOpen1) {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye_open),
                                    contentDescription = null,
                                    tint = Color.Black, modifier = Modifier.size(20.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye_close),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    },
                    label = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (!isPasswordOpen1) PasswordVisualTransformation() else VisualTransformation.None,
                )
                if (!isPasswordValid && password.isNotEmpty()) {
                    Text(
                        text = "\tPassword should contain atleast one uppercase and lowercase letter, digit and special character and minimum of length 8 ",
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isConfirmPasswordValid = (confirmPassword == password) || it.isEmpty()
                    },
                    isError = !isConfirmPasswordValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.medium,
                    leadingIcon = {
                        Icon(Icons.Default.Info, contentDescription = null)
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordOpen2 = !isPasswordOpen2 }) {
                            if (!isPasswordOpen2) {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye_open),
                                    contentDescription = null,
                                    tint = Color.Black, modifier = Modifier.size(20.dp)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.eye_close),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    },
                    label = { Text(text = "Confirm Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (!isPasswordOpen2) PasswordVisualTransformation() else VisualTransformation.None,
                )
                if (confirmPassword != password && !isConfirmPasswordValid && confirmPassword.isNotEmpty()) {
                    Text(text = "\tConfirm Password must be same as password", color = Color.Red)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        if (emailAddress.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                            navController.navigate("LoginPage")
                        }
                    },
                    modifier = Modifier
                        .width(178.dp)
                        .height(40.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkblue,
                        contentColor = Color.White,
                    )
                )
                {
                    Text("Set Password", fontSize = 20.sp)
                }


            }
        }
    }
}