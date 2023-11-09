package com.example.travelproject.form

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelproject.R
import java.text.DateFormatSymbols
import java.util.Calendar
import java.util.Date
import com.example.travelproject.cityguide.Homescreen

@SuppressLint("StringFormatInvalid")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormScreen(navController: NavController){
    var numberOfPeople by rememberSaveable { mutableIntStateOf(1) }
    var numberOfAdults by rememberSaveable { mutableIntStateOf(1) }
    var numberOfChildren by rememberSaveable { mutableIntStateOf(1) }
    var numberOfRooms by rememberSaveable { mutableIntStateOf(1) }
    var endDate by rememberSaveable { mutableLongStateOf(Date().time) }
    var startDate by rememberSaveable { mutableLongStateOf(Date().time) }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = stringResource(id = R.string.add_form),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        
        Spacer(modifier = Modifier.padding(8.dp))
        
        Text(
            text = stringResource(id = R.string.people)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numberOfPeople.toString(),
            onValueChange = { numberOfPeople = it.toIntOrNull() ?: 0 },
            placeholder = { Text(text = "e.g. 4") }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = stringResource(id = R.string.adults)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numberOfAdults.toString(),
            onValueChange = { numberOfAdults = it.toIntOrNull() ?: 0 },
            placeholder = { Text(text = "e.g. 2") }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = stringResource(id = R.string.children)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numberOfChildren.toString(),
            onValueChange = { numberOfChildren = it.toIntOrNull() ?: 0 },
            placeholder = { Text(text = "12 years or younger") }
        )

        Spacer(modifier = Modifier.padding(4.dp))


/*
        var isMaxFormError by rememberSaveable { mutableStateOf(false) }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            val maxForm=3
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text= stringResource(id = R.string.form),
                    style = MaterialTheme.typography.bodyLarge
                )
                TextField(value = numberOfAdults, onValueChange = {
                    if (it.length<maxForm){
                        isMaxFormError=false
                        numberOfAdults=it
                    } else{
                        isMaxFormError=true
                    }
                },

                    )
            }


        } */

        Text(
            text = stringResource(id = R.string.rooms)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numberOfRooms.toString(),
            onValueChange = { numberOfRooms = it.toIntOrNull() ?: 0 },
            placeholder = { Text(text = "Maximum 3 per room") }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        startDateTextField{startDate = it}
        Spacer(modifier = Modifier.padding(4.dp))

        endDateTextField{endDate = it}
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick ={ validateForm(
                people=numberOfPeople,
                adults=numberOfAdults,
                children= numberOfChildren,
                rooms= numberOfRooms,
                endDate = endDate,
                onInvalidate = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.value_is_empty,context.getString(it)),
                        Toast.LENGTH_LONG
                    ).show()
                },
                onValidate = {
                    navController.navigate("Homescreen")
                }
            ) },
            shape=MaterialTheme.shapes.extraLarge
        ){
            Text(text= stringResource(id = R.string.save),
                style =MaterialTheme.typography.bodyLarge)
        }


    }
}
fun validateForm(
    people: Int,
    adults: Int,
    children: Int,
    rooms: Int,
    endDate: Long,
    onInvalidate: (Int)-> Unit,
    onValidate: ()-> Unit
){
    if(people<1){
        onInvalidate(R.string.people)
        return
    }
    if(adults<1){
        onInvalidate(R.string.adults)
        return
    }
    if(children<1){
        onInvalidate(R.string.children)
        return
    }
    if(rooms<1){
        onInvalidate(R.string.rooms)
        return
    }
    if(endDate<1){
        onInvalidate(R.string.endDate)
        return
    }
    onValidate()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun endDateTextField(endDate: (Long)-> Unit){
    Text(
        text = stringResource(id = R.string.endDate),
        style = MaterialTheme.typography.bodyLarge
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val currentDate = Date().toFormattedString()
    var selectedData by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time= Date()

    val datePickerDialog=
        DatePickerDialog(context,{ _: DatePicker, year: Int, month: Int,dayOfMonth: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year,month,dayOfMonth)
            selectedData ="${month.toMonthName()} $dayOfMonth, $year"
            endDate(newDate.timeInMillis)
        }, year,month,day)
    TextField(
        modifier= Modifier.fillMaxWidth() ,
        readOnly = true,
        value = selectedData,
        onValueChange ={},
        trailingIcon={ Icons.Default.DateRange},
        interactionSource=interactionSource
    )
    if (isPressed){
        datePickerDialog.show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun startDateTextField(startDate: (Long)-> Unit){
    Text(
        text = stringResource(id = R.string.startDate),
        style = MaterialTheme.typography.bodyLarge
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val currentDate = Date().toFormattedString()
    var selectedData by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time= Date()

    val datePickerDialog=
        DatePickerDialog(context,{ _: DatePicker, year: Int, month: Int,dayOfMonth: Int ->
            val newDate = Calendar.getInstance()
            newDate.set(year,month,dayOfMonth)
            selectedData ="${month.toMonthName()} $dayOfMonth, $year"
            startDate(newDate.timeInMillis)
        }, year,month,day)
    TextField(
        modifier= Modifier.fillMaxWidth() ,
        readOnly = true,
        value = selectedData,
        onValueChange ={},
        trailingIcon={ Icons.Default.DateRange},
        interactionSource=interactionSource
    )
    if (isPressed){
        datePickerDialog.show()
    }
}
fun Int.toMonthName(): String {
    return DateFormatSymbols().months[this]
}

