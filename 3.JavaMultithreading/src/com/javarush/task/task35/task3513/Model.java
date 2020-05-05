package com.javarush.task.task35.task3513;

//содержать игровую логику и хранить игровое поле.

import java.util.*;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    public int score; // счет игры
    public int maxTile; // максимальный вес плитки на поле
    private Stack<Tile[][]> previousStates = new Stack();
    private Stack<Integer> previousScores = new Stack();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (getEmptyTiles().size() > 0) {
            return true;
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (i > 0 && gameTiles[i - 1][j].value == gameTiles[i][j].value) {
                    return true;
                }
                if (j > 0 && gameTiles[i][j - 1].value == gameTiles[i][j].value) {
                    return true;
                }
            }
        }
        return false;
    }

    public void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        score = 0;
        maxTile = 0;
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompress = false;
        for (int i = 1; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty() && tiles[i - 1].isEmpty()) {
                isCompress = true;
                int j = i;
                while (j > 0 && tiles[j - 1].isEmpty()) {
                    tiles[j - 1].value = tiles[j].value;
                    tiles[j].value = 0;
                    j--;
                }
            }
        }
        return isCompress;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isMerge = false;
        for (int i = 1; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty() && tiles[i - 1].value == tiles[i].value) {
                int newValue = tiles[i - 1].value * 2;
                tiles[i - 1].value = newValue;
                score += newValue;
                if (newValue > maxTile) {
                    maxTile = newValue;
                }
                tiles[i].value = 0;
                isMerge = true;
                compressTiles(tiles);
            }
        }
        return isMerge;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isFieldChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            isFieldChanged |= compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]);
        }
        if (isFieldChanged) {
            addTile();
        }
        isSaveNeeded = true;
    }


    public void up() {
        saveState(gameTiles);
        round();
        left();
        round();
        round();
        round();
    }

    public void right() {
        saveState(gameTiles);
        round();
        round();
        left();
        round();
        round();
    }

    public void down() {
        saveState(gameTiles);
        round();
        round();
        round();
        left();
        round();
    }

    private void round() {
        Tile[][] result = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                result[FIELD_WIDTH - 1 - j][i] = gameTiles[i][j];
            }
        }
        gameTiles = result;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tilesTmp = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tilesTmp.length; i++) {
            for (int j = 0; j < tilesTmp[i].length; j++) {
                tilesTmp[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(tilesTmp);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.empty() && !previousStates.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private int sumTiles(Tile[][] tiles) {
        int result = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                result += tiles[i][j].value;
            }
        }
        return result;
    }

    public boolean hasBoardChanged() {
        if (sumTiles(gameTiles) != sumTiles(previousStates.peek())) {
            return true;
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        int numberOfEmptyTiles = -1;
        int scoreTmp = 0;
        move.move();
        if (hasBoardChanged()) {
            numberOfEmptyTiles = getEmptyTiles().size();
            scoreTmp = score;
        }
        rollback();
        return new MoveEfficiency(numberOfEmptyTiles, scoreTmp, move);
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.peek().getMove().move();
    }
}
