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

    final private String querySelect = "SELECT osisRef,verseID,book,chapter,verseNum,verseText,people,places FROM verses";
    final private String queryPeople = "SELECT personLookup,displayTitle,gender,birthYear,deathYear,birthPlace,dictText,verses FROM people";
    final private String queryPlace = "SELECT placeLookup,displayTitle,verses,dictText,peopleBorn,peopleDied,hasBeenHere,latitude,longitude FROM places";
    // query baru - top verse people
    final private String queryPeopleTop = "SELECT personLookup,displayTitle,gender,birthYear,deathYear,birthPlace,dictText,verses,verseCount FROM people ORDER BY verseCount DESC LIMIT 7";
    final private String queryLimitOffsetPeople = "SELECT personLookup,displayTitle,gender,birthYear,deathYear,birthPlace,dictText,verses,verseCount FROM people ORDER BY verseCount DESC LIMIT 7 OFFSET ";
    // OFFSET dari baris... Limit sampai baris ke...

    // query baru - top verse place
    final private String queryPlaceTop = "SELECT placeLookup,displayTitle,verses,dictText,peopleBorn,peopleDied,hasBeenHere,latitude,longitude,verseCount FROM places ORDER BY verseCount DESC LIMIT 7";
    final private String queryLimitOffsetPlace = "SELECT placeLookup,displayTitle,verses,dictText,peopleBorn,peopleDied,hasBeenHere,latitude,longitude,verseCount FROM places ORDER BY verseCount DESC LIMIT 7 OFFSET ";

    // ... sesuai pembagian WBS
    private ObservableList<Verse> verses = FXCollections.observableArrayList();
    private ObservableList<People> peoples = FXCollections.observableArrayList();
    private ObservableList<Places> places = FXCollections.observableArrayList();

    // ... top 7 verse
    private ObservableList<People> peoplesTop = FXCollections.observableArrayList();
    private ObservableList<Places> placesTop = FXCollections.observableArrayList();

    // ... top verse from ... until....
    private ObservableList<People> peoplesTopStep = FXCollections.observableArrayList();
    private ObservableList<Places> placesTopStep = FXCollections.observableArrayList();
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
        verses.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(querySelect);
            while (result.next()) {
                Verse verse = new Verse();

                verse.setOsisRef(result.getString("osisRef"));
                verse.setBook(result.getString("book"));
                verse.setChapter(result.getString("chapter"));
                verse.setVerseNum(result.getString("verseNum"));

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
        peoples.clear();
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
        places.clear();
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

    // top 7 verseCount People
    public ObservableList<People> getAllPeopleSevenVerseCount() {
        peoplesTop.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryPeopleTop);
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
                people.setVerseCount(result.getString("verseCount"));

                peoplesTop.add(people);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return peoplesTop;
    }

    // return top ... verse sesuai keinginan PEOPLE // rancangan
    public ObservableList<People> getTopVerseDetailPeople(String poss) {
        peoplesTopStep.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryLimitOffsetPeople + poss);
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
                people.setVerseCount(result.getString("verseCount"));

                peoplesTopStep.add(people);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return peoplesTopStep;
    }

    public ObservableList<Places> getTopSevenPlace() {
        placesTop.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryPlaceTop);
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
                place.setVerseCount(result.getString("verseCount"));

                placesTop.add(place);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return placesTop;
    }

    public ObservableList<Places> getTopVerseDetailPlace(String poss) {
        placesTopStep.clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(queryLimitOffsetPlace + poss);
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
                place.setVerseCount(result.getString("verseCount"));

                placesTopStep.add(place);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return placesTopStep;
    }
}
