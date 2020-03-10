package states;

import ui.CSPNotification;

public class LockedState extends State {

  LockedState(CSPNotification CSPNotification) {
    super(CSPNotification);
    CSPNotification.setPlaying(false);
  }

  @Override
  public String onLock() {

   return CSPNotification.changeState(new AddState(CSPNotification));

  }

  @Override
  public String onPlay() {
    CSPNotification.changeState(new AddState(CSPNotification));
    return "Ready";
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
