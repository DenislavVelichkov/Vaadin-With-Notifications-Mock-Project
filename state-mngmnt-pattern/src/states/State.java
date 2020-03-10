package states;

import ui.CSPNotification;

public abstract class State {
  CSPNotification CSPNotification;
  /**
   * Context passes itself through the state constructor. This may help a
   * state to fetch some useful context data if needed.
   */
  State(CSPNotification CSPNotification) {
    this.CSPNotification = CSPNotification;
  }

  public abstract String onLock();
  public abstract String onPlay();
  public abstract String onNext();
  public abstract String onPrevious();

}