package com.example.impl.utils.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.R as coreR

@Composable
internal fun AuthField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    label: String,
    isError: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = colorResource(id = coreR.color.white)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = colorResource(id = coreR.color.white)),
            placeholder = {
                Text(
                    text = placeholder,
                    color = colorResource(id = coreR.color.placeholder_color)
                )
            },
            label = {
                Text(
                    text = label,
                    color = colorResource(id = coreR.color.white)
                )
            },
            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = coreR.color.field_background),
                unfocusedContainerColor = colorResource(id = coreR.color.field_background),
                disabledContainerColor = colorResource(id = coreR.color.field_background),
                focusedTextColor = colorResource(id = coreR.color.white),
                unfocusedTextColor = colorResource(id = coreR.color.white),
                disabledTextColor = colorResource(id = coreR.color.white).copy(alpha = 0.6f),
                cursorColor = colorResource(id = coreR.color.green),
                focusedBorderColor = colorResource(id = coreR.color.green),
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                focusedLabelColor = colorResource(id = coreR.color.white),
                unfocusedLabelColor = colorResource(id = coreR.color.white).copy(alpha = 0.7f),
                focusedPlaceholderColor = colorResource(id = coreR.color.placeholder_color).copy(alpha = 0.7f),
                unfocusedPlaceholderColor = colorResource(id = coreR.color.placeholder_color),
                disabledPlaceholderColor = colorResource(id = coreR.color.placeholder_color).copy(alpha = 0.7f)
            ),
            isError = isError
        )
    }
}