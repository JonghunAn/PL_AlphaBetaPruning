package project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GameState {
    static final double LAST_ODD_ONE = 0.5;
    static final double LAST_EVEN_ONE = -0.5;
    static final double LAST_ODD_PRIME = 0.7;
    static final double LAST_EVEN_PRIME = -0.7;
    static final double LAST_ODD = 0.6;
    static final double LAST_EVEN = -0.6;

    private int size;
    private boolean[] stones;
    private int lastStone; 

    
    GameState(int size) {

        this.size = size;

        this.stones = new boolean[this.size + 1];

        for (int i = 1; i <= this.size; ++i) {
            this.stones[i] = true;
        }
        this.stones[0] = false;

        this.lastStone = 0;
    }

     GameState(GameState state) {
        this.size = state.size;
        this.stones = Arrays.copyOf(state.stones, state.stones.length);
        this.lastStone = state.lastStone;
    }
     
     
     int getLastStone() {
         return this.lastStone;
     }


     void setStone(int index) {
         this.stones[index] = false;
         this.lastStone = index;
     }



    List<Integer> getUseOkStone() {
    	
        List<Integer> stoneList = new ArrayList<>();

        
        if (this.lastStone == 0) {	//�ʱ� ����
        	
            double firstmove = (double) this.size / 2;
            
            for (int i = 1; i <= this.size; i++) {
                if ((i % 2 != 0) && (i < firstmove)) {
                    stoneList.add(i);	//ó�� ������ �� �ִ� ��
                }
            }
        }
        else {
            for (int i = 1; i <= this.size; i++) {
                if (((this.lastStone % i == 0) || (i % this.lastStone == 0)) && this.stones[i]) {
                    stoneList.add(i);	//�������� ����ų� ����� ��
                }
            }
        }
        return stoneList;
    }
    

    boolean isMaxPlayer(int turns) {
        return turns % 2 == 0;
    }

    int TurnsNum() {
    	
        int turns = 0;
        for (int i = 1; i <= this.size; i++) {
        	
            if (this.stones[i] == false) 
                turns += 1;
            
        }
        return turns;
    }

   
    List<GameState> getUpdateStone() {
        return this.getUseOkStone().stream().map(move -> {
            GameState state = new GameState(this);	//���� �� ����
            state.setStone(move);
            return state;
        }).collect(Collectors.toList());	//�� ���� ������Ʈ
    }

    double evaluation(boolean isMaxPlayer) {
        double score = 1.0;
        List<Integer> stoneList = this.getUseOkStone();
        
        if (isMaxPlayer) {
            return score;
        }
        else if(!isMaxPlayer) {
            return score * -1;
        }
 
        else {	//�׹ۿ� ���
           
            if (this.lastStone == 1) {	//1�� ���������
                if (stoneList.size() % 2 == 0) {
                    score = LAST_EVEN_ONE;	//Ȧ��
                } else {
                    score = LAST_ODD_ONE;	//¦��
                }
            }
            else if (isPrime(this.lastStone)) {	//�Ҽ��� ���� �������� ����������
                if (this.lastStone % 2 == 0) {
                    score = LAST_EVEN_PRIME;	//Ȧ���� ���
                } else {
                    score = LAST_ODD_PRIME;		//¦���� ���
                }
            }
            else {	//�Ҽ��� �ƴ� ���� �������� ����������
                if (this.lastStone % 2 == 0) {
                    score = LAST_EVEN;
                } else {
                    score = LAST_ODD;
                }
            }
            return score;
        }
        
    }
    
	public static boolean isPrime(int x) {
		if (x < 2) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(x); i++) {
			if (x % i == 0) {
				return false;
			}
		}

		return true;
	}
	
}
