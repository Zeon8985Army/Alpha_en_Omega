package id.ac.ukdw.fti.rpl.bermudaTriangle.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.People;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Places;
import id.ac.ukdw.fti.rpl.bermudaTriangle.modal.Verse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    final private String url = "jdbc:sqlite:vizbible.db";

    final private String querySelect = "SELECT verseID,osisRef,verseText,people,places FROM verses";
    final private String queryPeople = "SELECT personLookup,displayTitle,gender,birthYear,deathYear,birthPlace,dictText,verses FROM people";
    final private String queryPlace = "SELECT placeLookup,displayTitle,verses,dictText,peopleBorn,peopleDied,hasBeenHere,latitude,longitude FROM places";
    // query baru
    // ... sesuai pembagian WBS

    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();
    // pembuatan list untuk menyimpan hasil query

    private Connection connection = null;
    public static Database instance = new Database();

    public Database() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection openConnection() {
        return connection;
    }

    public ObservableList<Verse> getAllVerse() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(querySelect);
            while (result.next()) {
                Verse verse = new Verse();
                verse.setVerseId(result.getInt("verseID"));
                verse.setVerse(result.getString("osisRef"));
                verse.setVerseText(result.getString("verseText"));
                verse.setPeople(result.getString("people"));
                verse.setPlaces(result.getString("places"));
                verses.add(verse);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return verses;
    }

    public ObservableList<People> getAllPeople() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryPeople);
            while (result.next()) {
                People people = new People();
                people.setPersonLookup(result.getString("personLookup"));
                people.setDisplayTitle(result.getString("displayTitle"));
                people.setGender(result.getString("gender"));
                people.setBirthPlace(result.getString("birthPlace"));
                people.setDeathYear(result.getString("deathYear"));
                people.setBirthYear(result.getString("birthYear"));
                people.setDictText(result.getString("dictText"));
                people.setVerses(result.getString("verses"));

                peoples.add(people);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return peoples;
    }

    public ObservableList<Places> getAllPlace() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryPlace);
            while (result.next()) {
                Places place = new Places();
                place.setPlaceLookup(result.getString("placeLookup"));
                place.setDisplayTitle(result.getString("displayTitle"));
                place.setVerses(result.getString("verses"));
                place.setDictText(result.getString("dictText"));
                place.setPeopleBorn(result.getString("peopleBorn"));
                place.setPeopleDied(result.getString("peopleDied"));
                place.setHasBeenHere(result.getString("hasBeenHere"));
                place.setLatitude(result.getString("latitude"));
                place.setLongitude(result.getString("longitude"));

                places.add(place);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return places;
    }
}
