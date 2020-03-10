package states;

import ui.CSPNotification;

public class AddState extends State {

  public AddState(CSPNotification CSPNotification) {
    super(CSPNotification);
  }

  @Override
  public String onLock() {
    CSPNotification.changeState(new LockedState(CSPNotification));
    return "Locked...";
  }

  @Override
  public String onPlay() {
    String action = CSPNotification.startPlayback();
    CSPNotification.changeState(new PlayingState(CSPNotification));
    return action;
  }

  @Override
  public String onNext() {
    return "Locked...";
  }

  @Override
  public String onPrevious() {
    return "Locked...";
  }
}
