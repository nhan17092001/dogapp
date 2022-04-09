package com.example.dogapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("")
    public List<DogBreed> getAllContact();

    @Insert
    public void insertAll(DogBreed... contact);

//    @Query("Select * from Contact where name like :name")
//    public List<Contact> getContactByName(String name);

    @Update
    public void update( DogBreed contact);
}
