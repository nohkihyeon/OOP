import java.util.concurrent.ThreadLocalRandom;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021.07
 * HowToWinStrategy.java : 승유패변’(win-stay, lose-shift) 법칙을 통해 자신이 이길 수 있는 걸 내는 경우가 많았다는
 * 연구결과를 바탕으로 만든 전략 
 * 이겼을 때 
 * 확률의 비를 1:2:2로 나누어서 구현했다 ranNum이 0~4까지 생성해서 1:2:2 사용 
 * @author 노기현 
 */
public class HowToWinStrategy implements PlayingStrategy {

	@Override
	public HandType computeNextHand(HandType lastUserHand, boolean isUserAttack) {
		if(lastUserHand == null || (!isUserAttack)) {
			return HandType.valueOf(ThreadLocalRandom.current().nextInt(3));
		}
		// 사용자가 이겼을  때 
		if(isUserAttack) {
			return lastUserHand.winValueOf();
		}
		return null;
	}	

}

/* 연구를 이끈 벤자민 다이슨 박사(영국 서식스대 심리학 강사)는 “사람들은 한 번의 패배에 따른 걱정으로 더 불합리한 결정을 내리는 경향이 있다”면서
사람들은 무의미하게 바위를 지나치게 선택하는 경향이 있었다고 한다. 참가자들은 세 번에 걸친 각 시험에서 75판씩 총 225판의 가위바위보 게임을 했다.
이때 컴퓨터는 시험마다 무작위 순서로 가위바위보를 각각 25번씩 선택했다고 한다.
참가자들은 각 시험 동안 준비 소리를 들을 뒤 자신이 낼 수를 선택하고 버튼을 눌렀고 이에 맞춰 컴퓨터도 임의로 선택한 수를 냈다.
이에 대해 연구팀은 “가위바위보 게임에서 가장 합리적인 선택은 각 수를 무작위로 내지만 같은 확률로 선택하는 것인데 다른 접근 방식은 비합리적인 것”이라면서 
“이는 컴퓨터가 사용하게 프로그래밍한 방법”이라고 설명했다. 이번 연구는 참가자들이 한 라운드에 승리했을 때 자신이 이긴 수를 계속 선택하는 것을 밝혀냈다.
이는 심리학에서 ‘승유패변’(win-stay, lose-shift) 법칙이라고 부르는 데, 즉 이들은 자신이 질 때까지 자신의 선택을 바꾸지 않는 경향이 있었다는 것이다.
또한 게임에서 이긴경우 다음 게임에서도 똑같은 것을 내는 경우가 많았고 패배한 경우에는 자신이 내서 패배한 것을 이길 수 있는 걸 내는 경우가 많았다. 
그리고 비겼을 때는 자신이 이길 수 있는 걸 내는 경우가 많았다.
출처 : https://www.youtube.com/watch?v=oORlgTtoSwc
*/
