package id.ac.ukdw.fti.rpl.bermudaTriangle.modal;

public class Verse {
    private String osisRef;
    private String book;
    private String chapter;
    private String verseNum;

    private int verseId;
    private String verse;
    private String verseText;
    private String people;
    private String places;

    public void setVerseId(int verseId) {
        this.verseId = verseId;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public int getVerseId() {
        return this.verseId;
    }

    public String getVerse() {
        return this.verse;
    }

    public String getVerseText() {
        return this.verseText;
    }

    public String getPeople() {
        return this.people;
    }

    public String getPlaces() {
        return this.places;
    }

    public String getOsisRef() {
        return osisRef;
    }

    public void setOsisRef(String osisRef) {
        this.osisRef = osisRef;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getVerseNum() {
        return verseNum;
    }

    public void setVerseNum(String verseNum) {
        this.verseNum = verseNum;
    }
    

    
}