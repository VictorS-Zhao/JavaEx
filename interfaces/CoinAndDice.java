import java.nio.*;
import java.util.*;

interface Game {
  void play();
}

interface GameFactory {
  Game getGame();
}

class CoinTossing implements Game {
  @Override
  public void play() {
    System.out.println("Toss a coin: ");
    Random rand = new Random();
    int i = rand.nextInt(2);
    if (i == 0) {
      System.out.println("It is head of coin!");
    } else {
      System.out.println("It is tail of coin!");
    }
  }
}

class DicePlaying implements Game {
  @Override
  public void play() {
    System.out.println("Play dice: ");
    Random rand = new Random();
    int count = rand.nextInt(6) + 1;
    System.out.println("The count is "+count);
  }
}

class CoinFactory implements GameFactory {
  @Override
  public Game getGame() {
    return new CoinTossing();
  }
}

class DiceFactory implements GameFactory {
  @Override
  public Game getGame() {
    return new DicePlaying();
  }
}

public class CoinAndDice {
  public static void playGame(GameFactory factory) {
    Game s = factory.getGame();
    s.play();
  }

  public static void main(String[] args) {
    playGame(new CoinFactory());
    playGame(new DiceFactory());
  }
}

