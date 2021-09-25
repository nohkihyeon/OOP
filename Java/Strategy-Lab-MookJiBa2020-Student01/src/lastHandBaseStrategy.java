/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습

 * @version 2021.07
 * lastHandBaseStrategy.java : 지난번에제시한손과다른손중하나를낼확률이높다고가정하여만든전략
 * 구체적으로 20%확률로 지난번에 낸 손과 같은 손을 낸다고 가정하고,
 * 80%확률로 지난번에낸손과 다른 손을 낸다고 가정하며, 둘 중에 어느 것을 낼지는 확률이 같다고 가정
 * 확률의 비를 1:2:2로 나누어서 구현했다 ranNum이 0~4까지 생성해서 1:2:2 사용 
 * @author 노기현 
 */

import java.util.concurrent.ThreadLocalRandom;

public class lastHandBaseStrategy implements PlayingStrategy {

	@Override
	public HandType computeNextHand(HandType lastUserHand, boolean isUserAttack) {
		// 첫 경기이거나 사용자의 공격이 아닐때
		if(lastUserHand == null || (!isUserAttack) ) {
			return HandType.valueOf(ThreadLocalRandom.current().nextInt(3));
		}
		// 사용자가 Mook을 내고 컴퓨터가 이겼을 때
		if(lastUserHand == HandType.valueOf(0) && (isUserAttack)) {
			int ranNum = ThreadLocalRandom.current().nextInt(5);
			if (ranNum ==0)
				return HandType.valueOf(0);
			else if (ranNum ==2 ||ranNum ==3)
				return HandType.valueOf(1);
			else
				return HandType.valueOf(2);
		}
		// 사용자가 Ji를 내고 컴퓨터가 이겼을 
		if(lastUserHand == HandType.valueOf(1) && (isUserAttack)) {
			int ranNum = ThreadLocalRandom.current().nextInt(5);
			if (ranNum ==0)
				return HandType.valueOf(1);
			else if (ranNum ==2 ||ranNum ==3)
				return HandType.valueOf(0);
			else
				return HandType.valueOf(2);
		}
		// 사용자가 Ba를 내고 컴퓨터가 이겼을 
		if(lastUserHand == HandType.valueOf(2) && (isUserAttack)) {
			int ranNum = ThreadLocalRandom.current().nextInt(5);
			if (ranNum ==0)
				return HandType.valueOf(2);
			else if (ranNum ==2 ||ranNum ==3)
				return HandType.valueOf(0);
			else
				return HandType.valueOf(1);
		}
		return null;
	}

}
