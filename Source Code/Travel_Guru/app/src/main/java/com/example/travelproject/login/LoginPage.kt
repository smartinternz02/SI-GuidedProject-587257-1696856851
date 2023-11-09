package com.example.travelproject.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.ui.theme.bg
import com.example.travelproject.ui.theme.darkblue
import com.example.travelproject.ui.theme.darkpurple
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavHostController) {

    // now we will create variables for email and password

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // used regular expressions
    val emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com\$"
    var isEmailValid by remember{ mutableStateOf(true) }
    var isPasswordValid by remember{ mutableStateOf(true) }
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@$!%*?&]{8,}"

    var isPasswordOpen by remember { mutableStateOf(false) }

    lateinit var auth: FirebaseAuth
    auth = Firebase.auth
    // first we will create column for layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bg)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.size(100.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .size(200.dp),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(10.dp))
        // now we will create text-field for email and password along with text-label
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = it.matches(emailRegex.toRegex()) || it.isEmpty()
            },
            isError = !isEmailValid,
            label = { Text("Enter Your Mail Id") },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp)
        )
        if (!isEmailValid) {
            Text(text = "Invalid Email Address", color = Color.Red)
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordValid = it.matches(passwordRegex.toRegex()) || it.isEmpty()
            },
            isError = !isPasswordValid,
            label = { Text("Enter Your Password") },
            leadingIcon = {
                Icon(Icons.Default.Info, contentDescription = null)
            },
            visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                    if (!isPasswordOpen) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (!isPasswordValid) {
            Text(
                text = "Password should be 8 characters long and include atleast one lowercase, one uppercase, one special Character, and one digit",
                color = Color.Red
            )
        }
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (isEmailValid && isPasswordValid && password.isNotEmpty() && email.isNotEmpty()) {
                        navController.navigate("AddFormScreen")
                    }
                },
                modifier = Modifier
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = darkblue,
                    contentColor = Color.White
                )
            )
            {
                Text("Login", fontSize = 23.sp)
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            "Forgot Password?",
            modifier = Modifier.clickable {
                navController.navigate("ForgetPswrdPage")
            }, color = Color.Black, fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Text("Don't Have an Account?", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "Sign Up", fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.navigate("SignUpPage")
                },
                color = darkpurple
            )
        }
    }
}