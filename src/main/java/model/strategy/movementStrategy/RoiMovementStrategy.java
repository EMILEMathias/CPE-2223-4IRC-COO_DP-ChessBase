package model.strategy.movementStrategy;

import shared.ActionType;

public class RoiMovementStrategy implements MovementStrategy{
    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean hasMoved, ActionType actionType) {
        return false;
    }
}