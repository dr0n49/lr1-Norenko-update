package com.company;
import static com.company.Main.GetDBHandler;

public class ErrorList
{
    public int ID;
    public String name;
    public String date;
    public String description;
    public int typeID;
    ErrorList(int ID, String name, String date, String description, int typeID)
    {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.description = description;
        this.typeID = typeID;
    }
    @Override
    public String toString()
    {
        String genre = GetDBHandler().findTypeByID(this.typeID);
        return String.format("ID: %s | Название: %s | Дата: %s | Описание: %s | Тип: %s",
                this.ID, this.name, this.date, this.description, genre != null ? genre : "не установлен");
    }
}
