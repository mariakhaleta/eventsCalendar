package sample;

import java.time.LocalDate;

public class Event {

  String title;
  LocalDate date;
  String place;
  String tag;
  String importance;

  public Event(String title, LocalDate date, String place, String tag, String importance) {
    this.title = title;
    this.date = date;
    this.place = place;
    this.tag = tag;
    this.importance = importance;
  }

  private static Event event;

  public static Event getInstance(String title, LocalDate date, String place, String tag, String importance) {
    if (event == null)
      event = new Event(title, date, place, tag, importance);
    return event;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getImportance() {
    return importance;
  }

  public void setImportance(String importance) {
    this.importance = importance;
  }

  @Override
  public String toString() {
    return "Event{" +
      "title='" + title + '\'' +
      ", date=" + date +
      ", place='" + place + '\'' +
      ", tag='" + tag + '\'' +
      '}';
  }
}
