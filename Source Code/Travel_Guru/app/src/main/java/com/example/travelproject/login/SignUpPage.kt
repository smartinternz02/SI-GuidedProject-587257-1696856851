package com.example.travelproject.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.ui.theme.darkblue
import com.example.travelproject.ui.theme.darkpurple
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPage(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    val firstNameRegex = "^[A-Z][a-z]{1,}$"
    var isFirstNameValid by remember { mutableStateOf(true) }

    var lastName by remember { mutableStateOf("") }
    val lastNameRegex = "^[A-Z][a-z]{1,}$"
    var isLastNameValid by remember { mutableStateOf(true) }

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

    lateinit var auth: FirebaseAuth
    auth = Firebase.auth

    Image(
        painter = painterResource(id = R.drawable.signup),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        contentDescription = "Background Image"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(130.dp))
        //firstname
        OutlinedTextField(
            value = firstName,
            onValueChange = {
                firstName = it
                isFirstNameValid = it.matches(firstNameRegex.toRegex()) || it.isEmpty()
            },
            isError = !isFirstNameValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            },
            label = { Text(text = "First Name", textAlign = Center) }
        )
        if (!isFirstNameValid && firstName.isNotEmpty()) {
            Text(
                text = "\t Start with Uppercase letter followed by lowercase",
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        //lastname
        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                isLastNameValid = it.matches(lastNameRegex.toRegex()) || it.isEmpty()
            },
            isError = !isLastNameValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            shape = MaterialTheme.shapes.medium,
            leadingIcon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            },
            label = { Text(text = "Last Name") }
        )
        if (!isLastNameValid && lastName.isNotEmpty()) {
            Text(
                text = "\t Start with Uppercase letter followed by lowercase",
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(3.dp))
        //email address
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

        Spacer(modifier = Modifier.size(3.dp))
        //password
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
            visualTransformation = if (!isPasswordOpen1) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (!isPasswordValid && password.isNotEmpty()) {
            Text(
                text = "\tPassword should contain atleast one uppercase and lowercase letter, digit and special character and minimum of length 8 ",
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.size(2.dp))
        //confirm password
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
        val context = LocalContext.current
        Spacer(modifier = Modifier.size(2.dp))
        //signup button
        Button(
            onClick = {
                if (isFirstNameValid && firstName.isNotEmpty() &&
                    isLastNameValid && lastName.isNotEmpty() &&
                    isEmailValid && emailAddress.isNotEmpty() &&
                    isPasswordValid && password.isNotEmpty() &&
                    isConfirmPasswordValid && password.isNotEmpty()
                ) {
                    auth.createUserWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                navController.navigate("LoginPage")
                            }
                        }
                    navController.navigate("LoginPage")
                }
            },
            modifier = Modifier
                .width(130.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = darkblue,
                contentColor = Color.White,
            )
        )
        {
            Text("Sign Up", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.size(15.dp))
        //sign in prompt
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .wrapContentSize(align = Alignment.BottomCenter)
        ) {
            Text("Already Have an Account?", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.navigate("LoginPage")
                },
                color = darkpurple
            )
        }
    }
}