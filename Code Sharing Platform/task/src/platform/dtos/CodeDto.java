package platform.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class CodeDto {
    @Id
    private String id;

    private String code;
    private String date;

    private long time;
    private long views;

    private boolean timeIsLimited;
    private boolean viewsAreLimited;

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CodeDto() {
    }

    public CodeDto(String code, String id, long time, long views) {
        this.code = code;
        this.id = id;
        this.time = time;
        this.views = views;
        this.timeIsLimited = time != 0;
        this.viewsAreLimited = views != 0;

        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public boolean isTimeIsLimited() {
        return timeIsLimited;
    }

    public void setTimeIsLimited(boolean timeIsLimited) {
        this.timeIsLimited = timeIsLimited;
    }

    @JsonIgnore
    public boolean isViewsAreLimited() {
        return viewsAreLimited;
    }

    public void setViewsAreLimited(boolean viewsAreLimited) {
        this.viewsAreLimited = viewsAreLimited;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "CodeDto{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", timeIsLimited=" + timeIsLimited +
                ", viewsAreLimited=" + viewsAreLimited +
                '}';
    }
}
