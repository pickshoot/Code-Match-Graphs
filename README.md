# Code-Match-Graphs

This project is no longer in development. 

The code is somewhat scuffed as it was not intended for distribution.

To change which dataset should be use change the `filename` variable used in the code:

```
if __name__ == '__main__':
    correlation = Correlation("score_cm", True)
    # txt file name lists which files should be read
    #filename = "Java TicTacToe.txt"
    filename = "Java Blackjack.txt"
    #filename = "Python TicTacToe.txt"
    #filename = "Python Blackjack.txt"
    filepath = "data/"
    correlation.start(filename, filepath)
```

## Example outputs from `ProximityGraph.py`

### Python Tic-Tac-Toe

![last_Python Tic-Tac-Toe](https://github.com/pickshoot/Code-Match-Graphs/assets/71334710/d3dfafa0-ae3e-4d83-bae5-6a610af3950d)

### Java Tic-Tac-Toe

![last_Java Tic-Tac-Toe](https://github.com/pickshoot/Code-Match-Graphs/assets/71334710/0198bc9c-5cdc-4209-a499-934dd47adaa2)

## Example outputs from `CorrelationMatrix.py`

### Python Tic-Tac-Toe

![heat_Python Tic-Tac-Toe](https://github.com/pickshoot/Code-Match-Graphs/assets/71334710/c3ee72ac-ce45-4a0f-8307-2abcda5de0f7)


### Java Tic-Tac-Toe

![heat_Java Tic-Tac-Toe](https://github.com/pickshoot/Code-Match-Graphs/assets/71334710/4c6e0d76-64ae-4d86-9107-4707fafecb56)

