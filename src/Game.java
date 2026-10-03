public class Game {

    private Integer id;
    private String name;
    private String style;
    private Double hoursPlayed;
    private String status;

    public Game(){
    }

    public Game(Integer id, String name, String style, Double hoursPlayed, String status) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.hoursPlayed = hoursPlayed;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(Double hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", hoursPlayed=" + hoursPlayed +
                ", status='" + status + '\'' +
                '}';
    }
}
