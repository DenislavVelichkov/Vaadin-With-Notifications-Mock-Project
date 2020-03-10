package ui;

import java.util.ArrayList;
import java.util.List;

import states.AddState;
import states.State;

public class CSPNotification {
  private State state;
  private boolean seen = false;
  private List<User> users = new ArrayList<>();

  public CSPNotification() {
    this.state = new AddState(this);
//    User user = new User();  Should figure out how to add the user in state/
    users.add(user);
  }

  public State changeState(State state) {
    return this.state = state;
  }

  public State getState() {
    return this.state;
  }

  public void setPlaying(boolean isSeen) {
    this.seen = isSeen;
  }

  public boolean isSeen() {
    return this.seen;
  }

  public String doSomethingWithUser(User user) {
    return "Playing " + this.users.get;
  }

  public String nextTrack() {
    currentTrack++;
    if (currentTrack > playlist.size() - 1) {
      currentTrack = 0;
    }
    return "Playing " + playlist.get(currentTrack);
  }

  public String previousTrack() {
    currentTrack--;
    if (currentTrack < 0) {
      currentTrack = playlist.size() - 1;
    }
    return "Playing " + playlist.get(currentTrack);
  }

  public void setCurrentTrackAfterStop() {
    this.currentTrack = 0;
  }
}