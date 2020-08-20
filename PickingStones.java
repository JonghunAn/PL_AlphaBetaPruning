package project1;

public class PickingStones {


	public static void main(String[] args) {
			int size = Integer.parseInt(args[0]); // �� ����
			int takeNum = Integer.parseInt(args[1]); // ������ �� ����
			int depth = size+1;	
			
			GameState state = new GameState(size); 
			AlphaBetaPruning findResult = new AlphaBetaPruning();
			
			for (int i = 0; i < takeNum; i++) {
				int stoneNum;
				stoneNum = Integer.parseInt(args[i + 2]);
				state.setStone(stoneNum);	//�ʱ� �� ���� ����
			}

			findResult.startGame(state, depth);
			
			findResult.printResult();	//������
	}
}