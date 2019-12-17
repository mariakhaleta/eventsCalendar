package sample.factory;

public class EventsFactory {

  public EventsInterface getEvents(EventsType eventsType) {
    EventsInterface toReturn = null;
    switch (eventsType) {
      case RECCURING:
        toReturn = new ReccuringEvents();
        break;
      case RESULT:
        toReturn = new ResultEvents();
        break;
      case SINGLE:
        toReturn = new SingleEvent();
        break;
      default:
        throw new IllegalArgumentException("Wrong event type:" + eventsType);
    }
    return toReturn;
  }
}
