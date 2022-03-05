package itmo.gorshkov;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MusicBand {
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private BigDecimal x; //Значение поля должно быть больше -331
    private BigDecimal y; //Значение поля должно быть больше -320
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0
    private Long albumsCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private String labelName; //Поле может быть null

    public Integer getId() {
        return id;
    }

    public MusicBand setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MusicBand setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getX() {
        return x;
    }

    public MusicBand setX(BigDecimal x) {
        this.x = x;
        return this;
    }

    public BigDecimal getY() {
        return y;
    }

    public MusicBand setY(BigDecimal y) {
        this.y = y;
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public MusicBand setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicBand setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
        return this;
    }

    public Long getAlbumsCount() {
        return albumsCount;
    }

    public MusicBand setAlbumsCount(Long albumsCount) {
        this.albumsCount = albumsCount;
        return this;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public MusicBand setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    public String getLabelName() {
        return labelName;
    }

    public MusicBand setLabelName(String labelName) {
        this.labelName = labelName;
        return this;
    }
}
