package project1;

public class PickingStones {


	public static void main(String[] args) {
			int size = Integer.parseInt(args[0]); // 돌 개수
			int takeNum = Integer.parseInt(args[1]); // 가져간 돌 개수
			int depth = size+1;	
			
			GameState state = new GameState(size); 
			AlphaBetaPruning findResult = new AlphaBetaPruning();
			
			for (int i = 0; i < takeNum; i++) {
				int stoneNum;
				stoneNum = Integer.parseInt(args[i + 2]);
				state.setStone(stoneNum);	//초기 돌 상태 설정
			}

			findResult.startGame(state, depth);
			
			findResult.printResult();	//결과출력
	}
}