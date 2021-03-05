package com.c1ctech.jetpackdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserManager(val dataStore: DataStore<Preferences>) {

    //Create some keys
    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_FIRST_NAME_KEY = stringPreferencesKey("USER_FIRST_NAME")
        val USER_LAST_NAME_KEY = stringPreferencesKey("USER_LAST_NAME")
        val USER_GENDER_KEY = booleanPreferencesKey("USER_GENDER")
    }

    //Store user data
    suspend fun storeUser(age: Int, fname: String, lname: String, isMale: Boolean) {
        dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_FIRST_NAME_KEY] = fname
            it[USER_LAST_NAME_KEY] = lname
            it[USER_GENDER_KEY] = isMale

        }
    }

    //Create an age flow
    val userAgeFlow: Flow<Int?> = dataStore.data.map {
        it[USER_AGE_KEY]
    }

    //Create a fname flow
    val userFirstNameFlow: Flow<String?> = dataStore.data.map {
        it[USER_FIRST_NAME_KEY]
    }

    //Create a lname flow
    val userLastNameFlow: Flow<String?> = dataStore.data.map {
        it[USER_LAST_NAME_KEY]
    }

    //Create a gender flow
    val userGenderFlow: Flow<Boolean?> = dataStore.data.map {
        it[USER_GENDER_KEY]
    }

}
