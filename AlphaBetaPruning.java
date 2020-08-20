package project1;

import java.util.List;

class AlphaBetaPruning {
	private int bestMove;
	private double calValue;
	private int visitNode;
    private int maxDepth;
    private int setDepth;

    AlphaBetaPruning() {
        this.maxDepth = 0;
        this.setDepth = 0;
        this.visitNode = 0;
    }
    
    void startGame(GameState state, int depth) {
    	int trunNum = state.TurnsNum();
        this.setDepth = depth;
        this.calValue = alphabeta(state, 0, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,state.isMaxPlayer(trunNum));
    }
    
     
    double alphabeta(GameState state, int depth, double alpha, double beta, boolean isMaxPlayer) {
   
    	this.visitNode++;
        this.maxDepth = depth;
        
        List<GameState> stoneList = state.getUpdateStone();
        
        if (stoneList.size() == 0 || depth == this.setDepth) 
            return state.evaluation(!isMaxPlayer);
        
        
        if (isMaxPlayer) {
            double maxValue = alpha;
            for (GameState stateNow : stoneList) {
                double value = this.alphabeta(stateNow, depth + 1, alpha, beta, false);
                if (value > maxValue) {
                    maxValue = value;
                    this.bestMove = depth == 0 ? stateNow.getLastStone() : this.bestMove;
                }
                alpha = Math.max(maxValue, alpha);
                if (alpha >= beta) {
                    break;
                }
            }
            return maxValue;
        }
        else if(!isMaxPlayer) {
            double minValue = beta;
            for (GameState stateNow : stoneList) {
                double value = this.alphabeta(stateNow, depth + 1, alpha, beta, true);
                if (value < minValue) {
                    minValue = value;
                    this.bestMove = depth == 0 ? stateNow.getLastStone() : this.bestMove;
                }
                beta = Math.min(minValue, beta);
                if (beta <= alpha) {
                    break;
                }
            }
            return minValue;
        }
        else {
        	return 0;
        }
    }

    void printResult() {
        System.out.println("Output:\nBest Move : " + this.bestMove);
        System.out.println("Calculated Value: " + this.calValue);
        System.out.println("Number of Visited Nodes : " + this.visitNode);
        System.out.println("Max Depth : " + this.maxDepth);
    }

}