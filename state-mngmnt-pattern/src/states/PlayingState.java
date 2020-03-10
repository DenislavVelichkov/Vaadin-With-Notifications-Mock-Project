package states;

import ui.CSPNotification;

public class PlayingState extends State {

  PlayingState(CSPNotification CSPNotification) {
    super(CSPNotification);
  }

  @Override
  public String onLock() {
    CSPNotification.changeState(new LockedState(CSPNotification));
    CSPNotification.setCurrentTrackAfterStop();
    return "Stop playing";
  }

  @Override
  public String onPlay() {
    CSPNotification.changeState(new AddState(CSPNotification));
    return "Paused...";
  }

  @Override
  public String onNext() {
    return CSPNotification.nextTrack();
  }

  @Override
  public String onPrevious() {
    return CSPNotification.previousTrack();
  }
}
