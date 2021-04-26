package by.academy.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {
    private static final long serialVersionUID = -2833778161156302358L;
    private int id;
    private String title;
    private String brief;
    private String content;
    private String date;

    public News() {
        super();
    }


    public News(int id, String title, String brief, String content, String date) {
        super();
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brief, title, content, date);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        News news = (News) obj;
        return (id == news.id)
                && (title != null && title.equals(news.title))
                && (brief != null && brief.equals(news.brief))
                && (content != null && content.equals(news.content))
                && (date != null && date.equals(news.date));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ").append(id).append(" Title: ").append(title).append(" Brief: ").append(brief).
                append(" Content: ").append(content).append(" Date: ").append(date);
        return stringBuilder.toString();
    }
}
