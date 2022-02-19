package model.game;

import java.util.stream.Stream;

import exception.WrongInputException;
import io.Input;
import io.Output;
import model.Ladder;
import model.User;
import repository.UserRepository;

public class LadderGame {

    UserRepository userRepository = UserRepository.getInstance();

    public void run() {
        inputValue();
    }

    private void inputValue() {
        while (true) {
            Input input = new Input();
            Output output = new Output();
            try {
                String username = output.printInputUsernameBar(input);
                int ladderMaxHigh = output.printLadderMaxHighBar(input);
                init(username, ladderMaxHigh);
                input.close();
                break;
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void init(String username, int ladderMaxHigh) {
        int userCount = getUserCount(username);
        validateUserCount(userCount);
        createUser(username);
        createLadder(userCount, ladderMaxHigh);
    }

    private int getUserCount(String username) {
        return (int) Stream.of(username.split(",")).count();
    }

    public void createLadder(int userCount, int ladderHeight) {
        Ladder ladder = new Ladder(userCount, ladderHeight);
        ladder.createLadder();
        ladder.printLadder();
    }

    public void createUser(String username) {
        Stream.of(username.split(","))
                .forEach(s -> {
                    User user = new User(s);
                    userRepository.save(user);
                });
        userRepository.printUser();
    }

    public void validateUserCount(int userCount) {
        if (userCount < 2) {
            throw new WrongInputException("유저의 수가 너무 적습니다.");
        }
    }
}
