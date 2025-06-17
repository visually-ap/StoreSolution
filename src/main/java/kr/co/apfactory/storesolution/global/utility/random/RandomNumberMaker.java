package kr.co.apfactory.storesolution.global.utility.random;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberMaker {
    // 비밀번호 찾기 토큰 생성
    public String makePasswordToken() {
        // 생성규칙
        // 랜덤 3자리 숫자 + 현재시간에서 앞 6자리 제거
        return makeRandomNumber(3) + Long.toString(System.currentTimeMillis()).substring(6);
    }

    // length - 자리수
    public String makeRandomNumber(int length) {
        String code = "";
        for (int i = 0; i < length; i++) {
            code += Integer.toString((int) Math.floor(Math.random() * 10));
        }
        return code;
    }

    // 3자리 숫자(1~99) 사이 난수 발생
    public int getRandomNumber() {
        return 1 + new Random().nextInt(99);
    }


    // 자리수 만큼 랜덤 글자 생성
    //length - 자리수
    public String passwordRandom(int length) {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < length; i++) {
            switch (rnd.nextInt(3)) {
                case 0:
                    buf.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    buf.append((rnd.nextInt(10)));
                    break;
                case 2:
                    buf.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
            }
        }
        return buf.toString();
    }

    public int makeRandomSequence(int sq, int rn) {
        return ((sq + 1) * rn - 16) * 3;
    }

    public long makeRandomSequence(long sq, int rn) {
        return ((sq + 1) * rn - 16) * 3;
    }

    public int getOriginalSequence(int sq, int rn) {
        return (((sq / 3) + 16) / rn) - 1;
    }

    public long getOriginalSequence(long sq, int rn) {
        return (((sq / 3) + 16) / rn) - 1;
    }
}
